package dao;

import java.util.List;

import codel.ContactGroup;

public interface IDAOContactGroup {
	public boolean addContactGroup(ContactGroup cg);
	public boolean deleteContactGroup(long id);
	public boolean modifyGroup(ContactGroup cg);
	public ContactGroup getGroup(long id);
	public List<ContactGroup> getAllContactGroups();
	public ContactGroup getContactGroupByName(String groupName);
	public void addContactToGroup(long cid, String groupName);
	public void delContactFromGroup(long cid, String groupName);

	public List<ContactGroup> getAllGroupsAndContacts();
}
