package mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import service.ServiceGroup;

@ManagedBean(name="delGroup")
public class DelGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	
	public String delGroup(long id){
		this.id = id;
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
