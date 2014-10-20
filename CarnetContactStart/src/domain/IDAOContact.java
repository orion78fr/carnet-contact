package domain;

import java.util.List;

public interface IDAOContact {
	public void addContact(Contact c);
	
	public boolean deleteContact(long id);
	
	public Contact getContact(long id);
	public List<Contact> getAllContacts();
	
	public List<Contact> getContactByFirstName(String firstname);
	public List<Contact> getContactByLastName(String lastname);
	public List<Contact> getContactByEmail(String email);
	public List<Contact> getContactByPhoneNumber(String phoneNumber);
	public List<Contact> getContactByStreet(String street);
	public List<Contact> getContactByCity(String city);
	public List<Contact> getContactByCountry(String country);
	public List<Contact> getContactByZip(String zip);
	
	public List<Contact> getContactUsingExample(Contact c);
	
	public boolean modifyContact(Contact c);
}
