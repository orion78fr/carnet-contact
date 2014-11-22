package mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import service.ServiceContact;

@ManagedBean(name="delContact")
public class DelContact implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	
	public String delContact(long id){
		this.id = id;
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
