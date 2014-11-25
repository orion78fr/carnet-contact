package mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import codel.Contact;
import service.ServiceContact;

@ManagedBean
@ViewScoped
public class DelContact implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	
	private Contact contact;
	
	public DelContact(){
		String idc = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idContact");
		if (idc == null) return;
		this.id = Long.parseLong(idc);
		this.contact = ServiceContact.getContact(id);
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String delContact(){
		ServiceContact.delContact(this.id);
		// TODO: ajouter un hmessage de succes / erreur
		return "accueil";
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
