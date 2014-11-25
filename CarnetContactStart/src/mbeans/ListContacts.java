package mbeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import codel.Contact;
import codel.ContactGroup;
import service.ServiceContact;
import service.ServiceGroup;

@ManagedBean
@RequestScoped
public class ListContacts implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Contact> listContacts = null;
	private List<ContactGroup> listGroups;
	
	private void initListContacts(){
		if(this.listContacts == null){
			this.listContacts = ServiceContact.getAllContacts();
			if (this.listContacts == null){
				this.listContacts =  Collections.emptyList();
			}
		}
	}
	
	public int getListContactsSize(){
		initListContacts();
		return this.listContacts.size();
	}
	
	public List<Contact> getPagedListContacts(int numperpage, int numpage){
		initListContacts();
		
		while((numpage * numperpage) > this.listContacts.size()){
			numpage--;
		}
		int end = ((numpage+1) * numperpage);
		if(end > this.listContacts.size()){
			end = this.listContacts.size();
		}
		return this.listContacts.subList(numpage * numperpage, end);
	}
	
	public List<Contact> getListContacts() {
		initListContacts();
		
		return this.listContacts;
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
