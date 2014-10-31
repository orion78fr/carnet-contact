package domain;

import java.util.List;

import org.hibernate.Hibernate;
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
	
	public boolean deleteContactGroup(long id){
		ContactGroup cg;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		cg = (ContactGroup) session.load(ContactGroup.class, id);
		if(cg == null){
			return false;
		} else {
			for (Contact c : cg.getContacts()){
				c.getBooks().remove(cg);
			}
			session.delete(cg);
			session.getTransaction().commit();
			return true;
		}
	}
	
	public boolean modifyGroup(ContactGroup cg){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(cg);
		session.getTransaction().commit();
		return true;
	}
	
	public ContactGroup getGroup(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ContactGroup cg = (ContactGroup) session.createCriteria(ContactGroup.class).add(Restrictions.eq("id", id)).uniqueResult();
		Hibernate.initialize(cg.getContacts());
		session.getTransaction().commit();
		return cg;
	}
	
	public List<ContactGroup> getAllContactGroups(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ContactGroup> l = session.createQuery("from ContactGroup").list();
		session.getTransaction().commit();
		return l;
	}
	
	public List<ContactGroup> getAllGroupsAndContacts() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ContactGroup> l = session.createQuery("from ContactGroup").list();
		for (ContactGroup cg : l){
			Hibernate.initialize(cg.getContacts());
		}
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
	
	public void delContactFromGroup(long cid, String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contact c = (Contact)session.load(Contact.class, cid);
		ContactGroup cg = (ContactGroup)session.createCriteria(ContactGroup.class).add(Restrictions.eq("groupName", groupName)).uniqueResult();
		cg.delContact(c);
		session.getTransaction().commit();
	}
}
