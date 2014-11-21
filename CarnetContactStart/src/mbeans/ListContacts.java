package mbeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import codel.Contact;
import codel.ContactGroup;
import service.ServiceContact;
import service.ServiceGroup;

@ManagedBean(name="listContacts")
public class ListContacts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Contact> listContacts;
	private List<ContactGroup> listGroups;
	
	@ManagedProperty(value="#{ServiceContact}")
	private ServiceContact sc;
	@ManagedProperty(value="#{ServiceGroup}")
	private ServiceGroup sp;
	
	public ServiceContact getSc() {
		return sc;
	}
	public void setSc(ServiceContact sc) {
		this.sc = sc;
	}
	
	public ServiceGroup getSp() {
		return sp;
	}
	public void setSp(ServiceGroup sp) {
		this.sp = sp;
	}
	
	public List<Contact> getListContacts() {
		this.listContacts = this.sc.getAllContacts();
		if (this.listContacts == null){
			return Collections.emptyList();
		} else {
			return this.listContacts;
		}
	}
	
	public List<ContactGroup> getListGroups() {
		this.listGroups = this.sp.getAllContactGroups();
		if (this.listGroups == null){
			return Collections.emptyList();
		} else {
			return this.listGroups;
		}
	}

}
