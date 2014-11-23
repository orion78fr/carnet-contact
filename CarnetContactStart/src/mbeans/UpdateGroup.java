package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import codel.Contact;
import codel.ContactGroup;
import service.ServiceGroup;

@ManagedBean(name="updateGroup")
@ViewScoped
public class UpdateGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private String groupName;
	private List<String> checkedContacts;
	private long idGroup;
	//private int version;
	
	public UpdateGroup(){
		// TODO: ajouter une vérification sur l'id
		String idc = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGroup");
		if (idc == null) return;
		this.idGroup = Long.parseLong(idc);
		ContactGroup cg = ServiceGroup.getGroup(this.idGroup);
		if (cg == null) return;
		this.setGroupName(cg.getGroupName());
		this.checkedContacts = new ArrayList<String>();
		for (Contact c : cg.getContacts()){
			this.checkedContacts.add(String.valueOf(c.getId()));
		}
		//this.version = c.getVersion();
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<String> getCheckedContacts() {
		return checkedContacts;
	}
	public void setCheckedContacts(List<String> checkedContacts) {
		this.checkedContacts = checkedContacts;
	}
	public long getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(long idGroup) {
		this.idGroup = idGroup;
	}

	public String updateGroup(){
		boolean r = ServiceGroup.modifyGroup(this.idGroup, groupName, checkedContacts);
		if (!r){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la mise à jour du groupe.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "modifyGroups";
	}
}
