package domain;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class DAOContact implements IDAOContact{
	public void addContact(Contact c){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.persist(c);
		session.getTransaction().commit();
	}

	public boolean deleteContact(long id){
		Contact contact;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		contact = (Contact) session.load(Contact.class, id);
		if(contact == null){
			return false;
		} else {
			session.delete(contact);
			session.getTransaction().commit();
			return true;
		}
	}
	
	public List<Contact> getAllContacts(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("from Contact").list();
		session.getTransaction().commit();
		return l;
	}
	
	public List<Contact> getAllContactsAndGroups(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("from Contact").list();
		for (Contact c : l){
			Hibernate.initialize(c.getBooks());
		}
		session.getTransaction().commit();
		return l;
	}

	public Contact getContact(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contact c = (Contact) session.createCriteria(Contact.class).add(Restrictions.eq("id", id)).uniqueResult();
		Hibernate.initialize(c.getBooks());
		session.getTransaction().commit();
		return c;
	}

	public boolean modifyContact(Contact c){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		return true;
	}

	public List<Contact> getContactByFirstName(String firstname){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createCriteria(Contact.class).add(Restrictions.eq("firstName", firstname)).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByLastName(String lastname){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createCriteria(Contact.class).add(Restrictions.eq("lastName", lastname)).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByEmail(String email){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createCriteria(Contact.class).add(Restrictions.eq("email", email)).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByPhoneNumber(String phoneNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("from Contact c where :tel in (select p.phoneNumber from PhoneNumber p where p.contact = c.id)")
									.setParameter("tel", phoneNumber).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByStreet(String street) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("select c from Contact c where c.add.street = :street").setParameter("street", street).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByCity(String city) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("select c from Contact c where c.add.city = :city").setParameter("city", city).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByCountry(String country) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("select c from Contact c where c.add.country = :country").setParameter("country", country).list();
		session.getTransaction().commit();
		return l;
	}

	public List<Contact> getContactByZip(String zip) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createQuery("select c from Contact c where c.add.zip = :zip").setParameter("zip", zip).list();
		session.getTransaction().commit();
		return l;
	}
	
	public List<Contact> getContactUsingExample(Contact c){
		Example exContact = Example.create(c).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		Example exAdd = Example.create(c.getAdd()).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		Example exProfiles = Example.create(c.getProfiles().iterator().next()).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeZeroes();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> l = session.createCriteria(Contact.class).add(exContact)
									/*.createCriteria("add").add(exAdd)*/
									.createCriteria("profiles").add(exProfiles).list();
		session.getTransaction().commit();
		return l;
	}
}
