package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Constraint;

import utils.HibernateUtil;

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
		@SuppressWarnings("unchecked")
		List<Contact> l = session.createQuery("from Contact").list();
		session.getTransaction().commit();
		return l;
	}

	public Contact getContact(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contact c = (Contact) session.createCriteria(Contact.class).add(Restrictions.eq("id", id)).uniqueResult();
		session.getTransaction().commit();
		return c;
	}

	public boolean modifyContact(long id, String firstname, String lastname, String email){
		boolean success = false;
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			String sqlFirstName = "UPDATE contacts SET firstname = "+"'"+firstname+"'"+" WHERE id = "+id ; 
			String sqlLastName = "UPDATE contacts SET lastname = "+"'"+lastname+"'"+" WHERE id = "+id ; 
			String sqlEmail = "UPDATE contacts SET email = "+"'"+email+"'"+" WHERE id = "+id ; 

			if(firstname != "")stmt.executeUpdate(sqlFirstName); 
			if(lastname != "")stmt.executeUpdate(sqlLastName); 
			if(email != "")stmt.executeUpdate(sqlEmail); 

			success = true;
			stmt.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}
		return success;
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
}
