package domain;

import java.util.List;

public class ServiceContact {
	private static IDAOContact getDAO(){
		return new DAOContact();
	}
	
	public static void createContact(String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone){
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
	}
	public static void modifyContact(Long id, String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone){
		IDAOContact dao = getDAO();
		
		Contact contact = dao.getContact(id);
		
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		contact.setAdd(new Address(street, city, zip, country));
		
		contact.getProfiles().clear();
		
		if(!mobile_phone.equals("")){
			contact.addProfile(new PhoneNumber("mobile", mobile_phone));
		}
		if(!office_phone.equals("")){
			contact.addProfile(new PhoneNumber("office", office_phone));
		}
		if(!home_phone.equals("")){
			contact.addProfile(new PhoneNumber("home", home_phone));
		}

		dao.modifyContact(contact);
	}
	
	public static Contact getContact(Long id){
		IDAOContact dao = getDAO();
		return dao.getContact(id);
	}
	
	public static List<Contact> getAllContacts(){
		IDAOContact dao = getDAO();
		return dao.getAllContacts();
	}
	
	public static List<Contact> getContactByFirstName(String firstname){
		IDAOContact dao = getDAO();
		return dao.getContactByFirstName(firstname);
	}
	public static List<Contact> getContactByLastName(String lastname){
		IDAOContact dao = getDAO();
		return dao.getContactByLastName(lastname);
	}
	public static List<Contact> getContactByEmail(String email){
		IDAOContact dao = getDAO();
		return dao.getContactByEmail(email);
	}
	public static List<Contact> getContactByPhoneNumber(String phoneNumber){
		IDAOContact dao = getDAO();
		return dao.getContactByPhoneNumber(phoneNumber);
	}
	public static List<Contact> getContactByStreet(String street){
		IDAOContact dao = getDAO();
		return dao.getContactByStreet(street);
	}
	public static List<Contact> getContactByCity(String city){
		IDAOContact dao = getDAO();
		return dao.getContactByCity(city);
	}
	public static List<Contact> getContactByCountry(String country){
		IDAOContact dao = getDAO();
		return dao.getContactByCountry(country);
	}
	public static List<Contact> getContactByZip(String zip){
		IDAOContact dao = getDAO();
		return dao.getContactByZip(zip);
	}
	public static List<Contact> getContactByCriterias(String firstName, String lastName, String email, String street, String city, String zip, String country, String phone){
		IDAOContact dao = getDAO();
		Contact c = new Contact();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.setAdd(new Address(street, city, zip, country));
		c.addProfile(new PhoneNumber("", phone));
		return dao.getContactUsingExample(c);
	}
}
