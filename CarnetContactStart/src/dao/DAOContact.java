package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.stat.CollectionStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import codel.Contact;

@SuppressWarnings("unchecked")
public class DAOContact extends HibernateDaoSupport implements IDAOContact{
	public void addContact(Contact c){
		this.getHibernateTemplate().persist(c);
	}
	
	@Transactional
	public boolean deleteContact(long id){
		Contact c = (Contact) this.getHibernateTemplate().get(Contact.class, id);
		if(c == null){
			return false;
		} else {
			this.getHibernateTemplate().delete(c);
			return true;
		}
	}
	
	public List<Contact> getAllContacts(){
		return (List<Contact>) this.getHibernateTemplate().find("from Contact c order by c.lastName, c.firstName");
	}
	
	@Transactional
	public List<Contact> getAllContactsAndGroups(){
		List<Contact> l = (List<Contact>) this.getHibernateTemplate().find("from Contact c order by c.lastName, c.firstName");
		for (Contact c : l){
			this.getHibernateTemplate().initialize(c.getBooks());
		}
		return l;
	}

	@Transactional
	public Contact getContact(long id){
		Contact c = (Contact) this.getHibernateTemplate().get(Contact.class, id);
		this.getHibernateTemplate().initialize(c.getBooks());
		return c;
	}

	public boolean modifyContact(Contact c){
		// TODO: renvoie:
		// ERROR org.hibernate.event.def.AbstractFlushingEventListener - Could not synchronize database state with session org.hibernate.StaleObjectStateException
		// Trouver un moyen de traiter ce truc
		try {
			this.getHibernateTemplate().saveOrUpdate(c);
		} catch (HibernateOptimisticLockingFailureException e){
			//e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public List<Contact> findContact(String str){
		HashSet<Long> ids = new HashSet<Long>();
		ArrayList<Contact> al = new ArrayList<Contact>();
		List<Contact> tmp;
		
		/* Request using Example */
		Contact c = new Contact();
		c.setFirstName(str);
		c.setLastName(str);
		c.setEmail(str);
		Example exContact = Example.create(c).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		tmp = (List <Contact>)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Contact.class).add(exContact).list();	
		for(Contact tmpc : tmp){
			if(ids.add(tmpc.getId())){
				al.add(tmpc);
			}
		}
		
		/* Request using HQL */
		tmp = (List<Contact>) this.getHibernateTemplate().find("from Contact c where ? in elements(c.profiles).phoneNumber", str);	
		for(Contact tmpc : tmp){
			if(ids.add(tmpc.getId())){
				al.add(tmpc);
			}
		}
		
		/* Request using Criterions */
		tmp = (List<Contact>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Contact.class).createCriteria("add")
				.add(Restrictions.or(Restrictions.like("street", str).ignoreCase(),
						Restrictions.or(Restrictions.like("city", str).ignoreCase(), 
								Restrictions.or(Restrictions.like("zip", str).ignoreCase(), Restrictions.like("country", str).ignoreCase())))).list();
		for(Contact tmpc : tmp){
			if(ids.add(tmpc.getId())){
				al.add(tmpc);
			}
		}
		
		Collections.sort(al);
		return al;
		
		
		/*Example exContact = Example.create(c).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		Example exAdd = Example.create(c.getAdd()).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		Example exProfiles = Example.create(c.getProfiles().iterator().next()).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createCriteria(Contact.class).add(exContact)
									/*.createCriteria("add").add(exAdd)*/
									/*.createCriteria("profiles").add(exProfiles).list();
		session.getTransaction().commit();
		return l;*/
	}
	
	public Statistics getStatistics(){
		return this.getHibernateTemplate().getSessionFactory().getStatistics();
	}
}
