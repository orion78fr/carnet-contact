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
		
		contact.addProfile(new PhoneNumber("mobile", mobile_phone));
		contact.addProfile(new PhoneNumber("office", office_phone));
		if(home_phone != null){
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
		
		Contact contact = dao.getContact(id);
		
		contact.setVersion(version);
		
		if (siret == null)
			contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		contact.getAdd().setStreet(street);
		contact.getAdd().setCity(city);
		contact.getAdd().setCountry(country);
		contact.getAdd().setZip(zip);
		
		for (PhoneNumber pn : contact.getProfiles()){
			if (pn.getPhoneKind().equals("mobile")){
				pn.setPhoneNumber(mobile_phone);
			} else if (pn.getPhoneKind().equals("home")){
				pn.setPhoneNumber(home_phone);
			} else if (pn.getPhoneKind().equals("office")){
				pn.setPhoneNumber(office_phone);
			}
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
	
	public static List<Contact> getContactByCriterias(String str){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		
		return dao.findContact(str);
	}
	
	public static boolean delContact(long id){
		IDAOContact dao = (IDAOContact) AppContextSingleton.getContext().getBean("DAOC");
		return dao.deleteContact(id);
	}
}
