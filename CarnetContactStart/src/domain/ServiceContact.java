package domain;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceContact {
	public static void createContact(ApplicationContext context, String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone, String[] groups){
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		
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
		
		if(groups != null){
			for (String g : groups){
				if (g != null){
					ServiceGroup.addContactToGroup(contact.getId(), g);
				}
			}
		}
		
	}
	public static void modifyContact(ApplicationContext context, Long id, String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone){
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		
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
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContact(id);
	}
	
	public static List<Contact> getAllContacts(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getAllContacts();
	}
	
	public static List<Contact> getContactByFirstName(String firstname){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByFirstName(firstname);
	}
	public static List<Contact> getContactByLastName(String lastname){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByLastName(lastname);
	}
	public static List<Contact> getContactByEmail(String email){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByEmail(email);
	}
	public static List<Contact> getContactByPhoneNumber(String phoneNumber){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByPhoneNumber(phoneNumber);
	}
	public static List<Contact> getContactByStreet(String street){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByStreet(street);
	}
	public static List<Contact> getContactByCity(String city){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByCity(city);
	}
	public static List<Contact> getContactByCountry(String country){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByCountry(country);
	}
	public static List<Contact> getContactByZip(String zip){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		return dao.getContactByZip(zip);
	}
	public static List<Contact> getContactByCriterias(String firstName, String lastName, String email, String street, String city, String zip, String country, String phone){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContact dao = (IDAOContact) context.getBean("DAOC");
		context.close();
		Contact c = new Contact();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.setAdd(new Address(street, city, zip, country));
		c.addProfile(new PhoneNumber("", phone));
		return dao.getContactUsingExample(c);
	}
}
