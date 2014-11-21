package mbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.ServiceContact;
import service.ServiceGroup;

@ManagedBean(name="addGroup")
public class AddGroup {
	private String groupName;

	@ManagedProperty(value="#{ServiceGroup}")
	private ServiceGroup sp;	
	
	public ServiceGroup getSp() {
		return sp;
	}
	public void setSp(ServiceGroup sp) {
		this.sp = sp;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String addGroup(){
		this.getSp().createGroup(groupName);
		return "accueil";
	}
}
