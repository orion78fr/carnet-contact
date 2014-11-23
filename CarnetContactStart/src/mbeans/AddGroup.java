package mbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import service.ServiceGroup;

@ManagedBean
public class AddGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private String groupName;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String addGroup(){
		if (ServiceGroup.getContactGroupByName(groupName) == null){
			ServiceGroup.createGroup(groupName);
			return "modifyGroups";
		} else {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce groupe existe déjà.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return "addGroup";
		}
		
	}
}
