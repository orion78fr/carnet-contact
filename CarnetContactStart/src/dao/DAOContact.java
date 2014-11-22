package dao;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.hibernate.stat.Statistics;
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
		try {
			this.getHibernateTemplate().saveOrUpdate(c);
		} catch (StaleObjectStateException e){
			return false;
		}
		return true;
	}

	public List<Contact> getContactUsingExample(Contact c){
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
		return null;
	}
	
	public Statistics getStatistics(){
		return this.getHibernateTemplate().getSessionFactory().getStatistics();
	}
}
