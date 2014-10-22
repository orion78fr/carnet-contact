package domain;

import java.util.List;

public interface IDAOContactGroup {
	public void addContactGroup(ContactGroup cg);
	public boolean deleteContactGroup(long id);
	public List<ContactGroup> getAllContactGroups();
	public ContactGroup getContactGroupByName(String groupName);
	public void addContactToGroup(long cid, String groupName);
	public void delContactFromGroup(long cid, String groupName);

	public List<ContactGroup> getAllGroupsAndContacts();
}
