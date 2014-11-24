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
	public List<Contact> findContact(final String str){
		HashSet<Long> ids = new HashSet<Long>();
		ArrayList<Contact> al = new ArrayList<Contact>();
		List<Contact> tmp;
		
		/* Request using Example */
		tmp = (List<Contact>)this.getHibernateTemplate().execute(new HibernateCallback<List<Contact>>() {
			public List<Contact> doInHibernate(Session session) throws HibernateException,
					SQLException {
				ArrayList<Contact> al = new ArrayList<Contact>();
				
				/* On est obligé de faire un example par valeur sinon on ne renvoit que lorsque tout les critères matchent */
				
				Contact c = new Contact();
				c.setFirstName(str);
				Example exContact = Example.create(c).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
				al.addAll(session.createCriteria(Contact.class).add(exContact).list());
				
				c = new Contact();
				c.setLastName(str);
				exContact = Example.create(c).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
				al.addAll(session.createCriteria(Contact.class).add(exContact).list());
				
				c = new Contact();
				c.setEmail(str);
				exContact = Example.create(c).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
				al.addAll(session.createCriteria(Contact.class).add(exContact).list());
				
				return al;
			}
		});
		for(Contact tmpc : tmp){
			if(ids.add(tmpc.getId())){
				al.add(tmpc);
			}
		}
		
		/* Request using HQL */
		tmp = (List<Contact>) this.getHibernateTemplate().execute(new HibernateCallback<List<Contact>>() {
					public List<Contact> doInHibernate(Session session) throws HibernateException,
							SQLException {
						return session.createQuery("from Contact c where (select count(*) from PhoneNumber p where p.contact = c and lower(p.phoneNumber) like lower('%" + str + "%'))>0").list();
					}
				});
		for(Contact tmpc : tmp){
			if(ids.add(tmpc.getId())){
				al.add(tmpc);
			}
		}
		
		/* Request using Criterions */
		tmp = (List<Contact>) this.getHibernateTemplate().execute(new HibernateCallback<List<Contact>>() {
			public List<Contact> doInHibernate(Session session) throws HibernateException,
					SQLException {
				String strbis = '%' + str + '%';
				return session.createCriteria(Contact.class).createCriteria("add")
					.add(Restrictions.or(Restrictions.like("street", strbis).ignoreCase(),
							Restrictions.or(Restrictions.like("city", strbis).ignoreCase(), 
									Restrictions.or(Restrictions.like("zip", strbis).ignoreCase(), Restrictions.like("country", strbis).ignoreCase())))).list();
			}
		});
		for(Contact tmpc : tmp){
			if(ids.add(tmpc.getId())){
				al.add(tmpc);
			}
		}
		
		Collections.sort(al);
		return al;
	}
	
	public Statistics getStatistics(){
		return this.getHibernateTemplate().getSessionFactory().getStatistics();
	}
}
