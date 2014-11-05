package domain;

import java.util.List;

public interface IDAOContact {
	public void addContact(Contact c);
	
	public boolean deleteContact(long id);
	
	public Contact getContact(long id);
	public List<Contact> getAllContacts();
	public List<Contact> getAllContactsAndGroups();
	
	public List<Contact> getContactUsingExample(Contact c);
	
	public boolean modifyContact(Contact c);
}
