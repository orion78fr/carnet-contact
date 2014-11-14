package mbeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import codel.Contact;
import service.ServiceContact;

@ManagedBean(name="listContacts")
public class ListContacts implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Contact> listContacts;
	@ManagedProperty(value="#{ServiceContact}")
	private ServiceContact sc;	
	
	public ServiceContact getSc() {
		return sc;
	}
	public void setSc(ServiceContact sc) {
		this.sc = sc;
	}
	
	public List<Contact> getListContacts() {
		this.listContacts = this.sc.getAllContacts();
		if (this.listContacts == null){
			return Collections.emptyList();
		} else {
			return this.listContacts;
		}
	}

}
