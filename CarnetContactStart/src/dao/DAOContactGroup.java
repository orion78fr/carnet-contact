package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import codel.Contact;
import codel.ContactGroup;

@SuppressWarnings("unchecked")
public class DAOContactGroup extends HibernateDaoSupport implements IDAOContactGroup {
	public boolean addContactGroup(ContactGroup cg){
		try {
			this.getHibernateTemplate().persist(cg);
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean deleteContactGroup(long id){
		ContactGroup cg;
		
		cg = (ContactGroup) this.getHibernateTemplate().get(ContactGroup.class, id);
		
		if(cg == null){
			return false;
		} else {
			for (Contact c : cg.getContacts()){
				c.getBooks().remove(cg);
			}
			this.getHibernateTemplate().delete(cg);
			return true;
		}
	}
	
	public boolean modifyGroup(ContactGroup cg){
		try {
			this.getHibernateTemplate().update(cg);
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	@Transactional
	public ContactGroup getGroup(long id){
		ContactGroup cg = (ContactGroup) this.getHibernateTemplate().get(ContactGroup.class, id);
		this.getHibernateTemplate().initialize(cg.getContacts());
		return cg;
	}
	
	public List<ContactGroup> getAllContactGroups(){
		return (List<ContactGroup>) this.getHibernateTemplate().find("from ContactGroup g order by g.groupName");
	}
	
	@Transactional
	public List<ContactGroup> getAllGroupsAndContacts() {
		List<ContactGroup> l = (List<ContactGroup>) this.getHibernateTemplate().find("from ContactGroup g order by g.groupName");
		for (ContactGroup cg : l){
			this.getHibernateTemplate().initialize(cg.getContacts());
		}
		return l;
	}
	
	public ContactGroup getContactGroupByName(String groupName){
		List<ContactGroup> l = (List<ContactGroup>) this.getHibernateTemplate().find("from ContactGroup where groupName=?", groupName);
		return l.isEmpty() ? null : l.get(0);
	}

	@Transactional
	public void addContactToGroup(long cid, String groupName) {
		ContactGroup cg = (ContactGroup)this.getHibernateTemplate().find("from ContactGroup where groupName=?", groupName).get(0);
		this.getHibernateTemplate().initialize(cg.getContacts());
		cg.addContact((Contact)this.getHibernateTemplate().get(Contact.class, cid));
	}
	
	@Transactional
	public void delContactFromGroup(long cid, String groupName) {
		ContactGroup cg = (ContactGroup)this.getHibernateTemplate().find("from ContactGroup where groupName=?", groupName).get(0);
		this.getHibernateTemplate().initialize(cg.getContacts());
		cg.delContact((Contact)this.getHibernateTemplate().get(Contact.class, cid));
	}
}
