package mbeans;

import javax.faces.bean.ManagedBean;

import service.ServiceGroup;

@ManagedBean
public class AddGroup {
	private String groupName;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String addGroup(){
		ServiceGroup.createGroup(groupName);
		return "accueil";
	}
}
