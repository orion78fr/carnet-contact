package mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.ServiceContact;

@ManagedBean(name="addContact")
public class AddContact {
	private String firstName;
	private String lastName;
	private String email;

	@ManagedProperty(value="#{ServiceContact}")
	private ServiceContact sc;	
	
	public ServiceContact getSc() {
		return sc;
	}
	public void setSc(ServiceContact sc) {
		this.sc = sc;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String addContact(){
		this.getSc().createContact(firstName, lastName, email, "", "", "", "", "", "", "", null);
		return "accueil";
	}
}
