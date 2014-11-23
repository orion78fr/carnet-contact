package service;

import java.util.List;

import utils.AppContextSingleton;
import codel.Address;
import codel.Contact;
import codel.ContactGroup;
import codel.Entreprise;
import codel.PhoneNumber;
import dao.IDAOContact;

public class ServiceContact {
	public static void createContact(String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone, List<String> groups, String siret){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		
		Contact contact;
		if (siret == null){
			contact = new Contact();
		} else {
			contact = new Entreprise();
		}
		if (firstName != null)
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
		if(home_phone != null && !home_phone.equals("")){
			contact.addProfile(new PhoneNumber("home", home_phone));
		}
		
		if (contact instanceof Entreprise){
			((Entreprise) contact).setNumSiret(siret);
		}
		
		dao.addContact(contact);
		
		if(!groups.isEmpty()){
			for (int i=0; i<groups.size(); i++){
				ServiceGroup.addContactToGroup(contact.getId(), groups.get(i));
			}
		}
		
	}
	public static boolean modifyContact(Long id, Integer version, String firstName, String lastName, String email, String street, String city, String zip, String country, String mobile_phone, String office_phone, String home_phone, List<String> groups, String siret){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		
		Contact contact;
		if (siret == null){
			contact = new Contact();
		} else {
			contact = new Entreprise();
		}
		
		contact.setId(id);
		contact.setVersion(version);
		
		if (siret == null)
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
		if(siret != null && home_phone != null && !home_phone.equals("")){
			contact.addProfile(new PhoneNumber("home", home_phone));
		}
		
		if (contact instanceof Entreprise){
			((Entreprise) contact).setNumSiret(siret);
		}

		if (!dao.modifyContact(contact)){
			return false;
		}
		
		if(!groups.isEmpty()){
			for (int i=0; i<groups.size(); i++){
				ServiceGroup.addContactToGroup(contact.getId(), groups.get(i));
			}
		}
		for (ContactGroup cg : contact.getBooks()){
			boolean groupFound = false;
			if(!groups.isEmpty()){
				for (int i=0; i<groups.size(); i++){
					if (cg.getGroupName().equals(groups.get(i))){
						groupFound = true;
					}
				}
			}
			if (groupFound == false)
				ServiceGroup.delContactFromGroup(contact.getId(), cg.getGroupName());
		}
		
		return true;
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
