package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.ServiceContact;
import codel.Contact;

@ManagedBean
@SessionScoped
public class FindContact implements Serializable {
	private static final long serialVersionUID = 1L;
	private String recherche = "";
	private List<Contact> al = new ArrayList<Contact>();
	
	public List<Contact> getList(){
		return al;
	}

	public String getRecherche() {
		return recherche;
	}

	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}
	
	public String refresh(){
		if(recherche != null && !recherche.equals("")){
			al = ServiceContact.getContactByCriterias(recherche);
		}
		return "findContact";
	}
}
