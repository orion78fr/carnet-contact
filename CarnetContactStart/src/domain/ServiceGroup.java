package domain;

import java.util.List;

public class ServiceGroup {
	private static IDAOContactGroup getDAO(){
		return new DAOContactGroup();
	}
	
	public static void createGroup(String groupName){
		IDAOContactGroup dao = getDAO();
		
		ContactGroup cg = new ContactGroup();
		cg.setGroupName(groupName);
		
		dao.addContactGroup(cg);
	}
	
	public static List<ContactGroup> getAllContactGroups(){
		IDAOContactGroup dao = getDAO();
		return dao.getAllContactGroups();
	}
	
	public static ContactGroup getContactGroupByName(String groupName){
		IDAOContactGroup dao = getDAO();
		return dao.getContactGroupByName(groupName);
	}
	
	public static void addContactToGroup(long cid, String groupName){
		IDAOContactGroup dao = getDAO();
		dao.addContactToGroup(cid, groupName);
	}
}
