package domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import utils.HibernateUtil;

public class DAOContactGroup implements IDAOContactGroup {
	
	public DAOContactGroup(){
	}
	
	public void addContactGroup(ContactGroup cg){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.persist(cg);
		session.getTransaction().commit();
	}
	
	public List<ContactGroup> getAllContactGroups(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ContactGroup> l = session.createQuery("from ContactGroup").list();
		session.getTransaction().commit();
		return l;
	}
	
	public ContactGroup getContactGroupByName(String groupName){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ContactGroup cg = (ContactGroup)session.createCriteria(ContactGroup.class).add(Restrictions.eq("groupName", groupName)).uniqueResult();
		session.getTransaction().commit();
		return cg;
	}

	public void addContactToGroup(long cid, String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contact c = (Contact)session.load(Contact.class, cid);
		ContactGroup cg = (ContactGroup)session.createCriteria(ContactGroup.class).add(Restrictions.eq("groupName", groupName)).uniqueResult();
		cg.addContact(c);
		session.getTransaction().commit();
	}
}
