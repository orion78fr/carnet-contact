package domain;

import java.util.List;

public interface IDAOContactGroup {
	public void addContactGroup(ContactGroup cg);
	
	public List<ContactGroup> getAllContactGroups();
	public ContactGroup getContactGroupByName(String groupName);
	public void addContactToGroup(long cid, String groupName);
}
