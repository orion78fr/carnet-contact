package domain;

import java.util.List;

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
	
	public static List<ContactGroup> getAllContactGroups(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IDAOContactGroup dao = (IDAOContactGroup) context.getBean("DAOCG");
		context.close();
		return dao.getAllContactGroups();
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
}
