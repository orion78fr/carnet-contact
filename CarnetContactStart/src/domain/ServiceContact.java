package domain;

import java.util.List;

public class ServiceContact {
	public static void createContact(String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone, String[] groups){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		
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
	public static void modifyContact(Long id, Integer version, String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone, String[] groups){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		
		Contact contact = new Contact();
		
		contact.setId(id);
		contact.setVersion(version);
		
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
		
		if(groups != null){
			for (String g : groups){
				if (g != null){
					ServiceGroup.addContactToGroup(contact.getId(), g);
				}
			}
		}
		for (ContactGroup cg : contact.getBooks()){
			boolean groupFound = false;
			if (groups != null){
				for (String g : groups){
					if (g != null && cg.getGroupName().equals(g)){
						groupFound = true;
					}
					
				}
			}
			if (groupFound == false)
				ServiceGroup.delContactFromGroup(contact.getId(), cg.getGroupName());
		}
	}
	
	public static Contact getContact(Long id){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		return dao.getContact(id);
	}
	
	public static List<Contact> getAllContacts(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		return dao.getAllContacts();
	}
	
	public static List<Contact> getAllContactsAndGroups(){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		return dao.getAllContactsAndGroups();
	}
	
	public static List<Contact> getContactByCriterias(String firstName, String lastName, String email, String street, String city, String zip, String country, String phone){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		Contact c = new Contact();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(email);
		c.setAdd(new Address(street, city, zip, country));
		c.addProfile(new PhoneNumber("", phone));
		return dao.getContactUsingExample(c);
	}
	
	public static boolean delContact(long id){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		return dao.deleteContact(id);
	}
}
