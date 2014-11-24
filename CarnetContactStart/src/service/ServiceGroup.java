package service;

import java.util.List;

import utils.AppContextSingleton;
import codel.Contact;
import codel.ContactGroup;
import dao.IDAOContactGroup;

public class ServiceGroup {
	public static boolean createGroup(String groupName){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		
		ContactGroup cg = new ContactGroup();
		cg.setGroupName(groupName);
		
		return dao.addContactGroup(cg);
	}
	
	public static boolean modifyGroup(Long id, Integer version, String groupName, List<String> contacts){
		IDAOContactGroup dao = (IDAOContactGroup) AppContextSingleton.getContext().getBean("DAOCG");
		
		ContactGroup cg = dao.getGroup(id);
		
		cg.setGroupName(groupName);
		cg.setVersion(version);

		if (!dao.modifyGroup(cg)){
			return false;
		}
		
		if(!contacts.isEmpty()){
			for (int i=0; i<contacts.size(); i++){
				ServiceGroup.addContactToGroup(Long.parseLong(contacts.get(i)), cg.getGroupName());
			}
		}
		for (Contact c : cg.getContacts()){
			boolean contactFound = false;
			if(!contacts.isEmpty()){
				for (int i=0; i<contacts.size(); i++){
					if (c.getId() == Long.parseLong(contacts.get(i))){
						contactFound = true;
					}
				}
			}
			if (contactFound == false)
				ServiceGroup.delContactFromGroup(c.getId(), cg.getGroupName());
		}
		
		// TODO: return false quelque part ?!
		return true;
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
