package mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import codel.ContactGroup;
import service.ServiceGroup;

@ManagedBean
@ViewScoped
public class DelGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	
	private ContactGroup group;
	
	public DelGroup(){
		String idc = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idGroup");
		if (idc == null) return;
		this.id = Long.parseLong(idc);
		this.group = ServiceGroup.getGroup(id);
	}
	
	public ContactGroup getGroup() {
		return group;
	}

	public void setGroup(ContactGroup group) {
		this.group = group;
	}

	public String delGroup(){
		ServiceGroup.delContactGroup(this.id);
		// TODO: ajouter un hmessage de succes / erreur
		return "modifyGroups";
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
