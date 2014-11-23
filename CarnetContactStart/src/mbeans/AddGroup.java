package mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

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
		ServiceGroup.createGroup(groupName);
		return "modifyGroups";
	}
}
