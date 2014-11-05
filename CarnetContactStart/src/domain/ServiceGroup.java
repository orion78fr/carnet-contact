package domain;

import java.util.List;

public class ServiceGroup {
	public static void createGroup(String groupName){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		
		ContactGroup cg = new ContactGroup();
		cg.setGroupName(groupName);
		
		dao.addContactGroup(cg);
	}
	
	public static void modifyGroup(Long id, String groupName, String[] contacts){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		
		ContactGroup cg = dao.getGroup(id);
		
		cg.setGroupName(groupName);

		dao.modifyGroup(cg);
		
		if(contacts != null){
			for (String c : contacts){
				if (c != null){
					ServiceGroup.addContactToGroup(ServiceContact.getContact(Long.parseLong(c)).getId(), cg.getGroupName());
				}
			}
		}
		for (Contact c : cg.getContacts()){
			boolean contactFound = false;
			if (contacts != null){
				for (String co : contacts){
					if (co != null && c.getId() == Long.parseLong(co)){
						contactFound = true;
					}
					
				}
			}
			if (contactFound == false)
				ServiceGroup.delContactFromGroup(c.getId(), cg.getGroupName());
		}
	}
	
	public static ContactGroup getGroup(Long id){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		return dao.getGroup(id);
	}
	
	public static List<ContactGroup> getAllContactGroups(){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		return dao.getAllContactGroups();
	}
	
	public static List<ContactGroup> getAllGroupsAndContacts(){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		return dao.getAllGroupsAndContacts();
	}
	
	public static ContactGroup getContactGroupByName(String groupName){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		return dao.getContactGroupByName(groupName);
	}
	
	public static void addContactToGroup(long cid, String groupName){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		dao.addContactToGroup(cid, groupName);
	}
	
	public static void delContactFromGroup(long cid, String groupName){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		dao.delContactFromGroup(cid, groupName);
	}

	public static void delContactGroup(Long id) {
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		dao.deleteContactGroup(id);
	}
}
