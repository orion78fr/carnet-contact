package mbeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;

import codel.Contact;
import codel.ContactGroup;
import service.ServiceContact;
import service.ServiceGroup;

@ManagedBean
public class ListContacts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Contact> listContacts;
	private List<ContactGroup> listGroups;
	
	public List<Contact> getListContacts() {
		this.listContacts = ServiceContact.getAllContacts();
		if (this.listContacts == null){
			return Collections.emptyList();
		} else {
			return this.listContacts;
		}
	}
	
	public List<ContactGroup> getListGroups() {
		this.listGroups = ServiceGroup.getAllContactGroups();
		if (this.listGroups == null){
			return Collections.emptyList();
		} else {
			return this.listGroups;
		}
	}

}
