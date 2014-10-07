package domain;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import utils.HibernateUtil;

public class ServiceContact {
	private static IDAOContact getDAO(){
		return new DAOContact();
	}
	
	public static void createContact(String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone, String[] groups){
		IDAOContact dao = getDAO();
		
		Contact contact = new Contact();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		contact.setAdd(new Address(street, city, zip, country));
		
		if(!mobile_phone.equals("")){
			contact.addProfile(new PhoneNumber("mobile", mobile_phone));
		}
		if(!office_phone.equals("")){
			contact.addProfile(new PhoneNumber("office", office_phone));
		}
		if(!home_phone.equals("")){
			contact.addProfile(new PhoneNumber("home", home_phone));
		}
		
		dao.addContact(contact);
		
		for (String g : groups){
			if (g != null){
				ServiceGroup.addContactToGroup(contact.getId(), g);
			}
		}
		
	}
	
	public static List<Contact> getAllContacts(){
		IDAOContact dao = getDAO();
		return dao.getAllContacts();
	}
	
	public List<Contact> getContactByFirstName(String firstname){
		IDAOContact dao = getDAO();
		return dao.getContactByFirstName(firstname);
	}
	public List<Contact> getContactByLastName(String lastname){
		IDAOContact dao = getDAO();
		return dao.getContactByLastName(lastname);
	}
	public List<Contact> getContactByEmail(String email){
		IDAOContact dao = getDAO();
		return dao.getContactByEmail(email);
	}
	public List<Contact> getContactByPhoneNumber(String phoneNumber){
		IDAOContact dao = getDAO();
		return dao.getContactByPhoneNumber(phoneNumber);
	}
	public List<Contact> getContactByStreet(String street){
		IDAOContact dao = getDAO();
		return dao.getContactByStreet(street);
	}
	public List<Contact> getContactByCity(String city){
		IDAOContact dao = getDAO();
		return dao.getContactByCity(city);
	}
	public List<Contact> getContactByCountry(String country){
		IDAOContact dao = getDAO();
		return dao.getContactByCountry(country);
	}
	public List<Contact> getContactByZip(String zip){
		IDAOContact dao = getDAO();
		return dao.getContactByZip(zip);
	}
}
