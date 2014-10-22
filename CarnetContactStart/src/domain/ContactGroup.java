package domain;

import java.util.Set;

import org.hibernate.Hibernate;

public class ContactGroup {
	private long id;
	private String groupName;
	private Set<Contact> contacts;
	
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	public void addContact(Contact c){
		this.contacts.add(c);
		if(!c.getBooks().contains(this)){
			c.addBook(this);
		}
	}
	public void delContact(Contact c){
		this.contacts.remove(c);
		if(c.getBooks().contains(this)){
			c.delBook(this);
		}
	}
	public ContactGroup() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public boolean isInGroup(long id){
		for (Contact c : this.contacts){
			if (c.getId() == id) return true;
		}
		return false;
	}
}
