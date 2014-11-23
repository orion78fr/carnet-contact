package dao;

import java.util.List;

import codel.Contact;

public interface IDAOContact {
	public void addContact(Contact c);
	
	public boolean deleteContact(long id);
	
	public Contact getContact(long id);
	public List<Contact> getAllContacts();
	public List<Contact> getAllContactsAndGroups();
	
	public List<Contact> findContact(String str);
	
	public boolean modifyContact(Contact c);
}
