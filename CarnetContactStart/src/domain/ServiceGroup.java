package domain;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceGroup {
	public static void createGroup(String groupName){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		
		ContactGroup cg = new ContactGroup();
		cg.setGroupName(groupName);
		
		dao.addContactGroup(cg);
	}
	
	public static void modifyGroup(ApplicationContext context, Long id, String groupName, String[] contacts){
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		
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
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		return dao.getGroup(id);
	}
	
	public static List<ContactGroup> getAllContactGroups(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		return dao.getAllContactGroups();
	}
	
	public static List<ContactGroup> getAllGroupsAndContacts(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		return dao.getAllGroupsAndContacts();
	}
	
	public static ContactGroup getContactGroupByName(String groupName){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		return dao.getContactGroupByName(groupName);
	}
	
	public static void addContactToGroup(long cid, String groupName){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		dao.addContactToGroup(cid, groupName);
	}
	
	public static void delContactFromGroup(long cid, String groupName){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		dao.delContactFromGroup(cid, groupName);
	}
}
