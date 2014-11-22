package mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import codel.Contact;
import codel.ContactGroup;
import codel.PhoneNumber;
import service.ServiceContact;

@ManagedBean(name="updateContact")
@ViewScoped
public class UpdateContact implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String street;
	private String city;
	private String zip;
	private String country;
	private String mobilePhone;
	private String officePhone;
	private String homePhone;
	private List<String> groups;
	private List<String> checkedGroups;
	private long idContact;
	private int version;
	
	public UpdateContact(){
		// TODO: ajouter une vérification sur l'id
		String idc = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idContact");
		if (idc == null) return;
		this.idContact = Long.parseLong(idc);
		Contact c = ServiceContact.getContact(idContact);
		if (c == null) return;
		this.setFirstName(c.getFirstName());
		this.setLastName(c.getLastName());
		this.setEmail(c.getEmail());
		this.setStreet(c.getAdd().getStreet());
		this.setCity(c.getAdd().getCity());
		this.setZip(c.getAdd().getZip());
		this.setCountry(c.getAdd().getCountry());
		for(PhoneNumber num : c.getProfiles()){
     	   if(num.getPhoneKind().equals("office")){
     		   this.setOfficePhone(num.getPhoneNumber());
     	   } else if(num.getPhoneKind().equals("home")){
     		   this.setHomePhone(num.getPhoneNumber());
     	   } else if(num.getPhoneKind().equals("mobile")){
               this.setMobilePhone(num.getPhoneNumber());
           }
        }
		this.checkedGroups = new ArrayList<String>();
		for (ContactGroup cg : c.getBooks()){
			this.checkedGroups.add(cg.getGroupName());
		}
		this.version = c.getVersion();
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}	
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	public long getIdContact() {
		return idContact;
	}
	public void setIdContact(long id) {
		this.idContact = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}	
	public List<String> getCheckedGroups() {
		return checkedGroups;
	}
	public void setCheckedGroups(List<String> checkedGroups) {
		this.checkedGroups = checkedGroups;
	}

	public String updateContact(){
		boolean r = ServiceContact.modifyContact(idContact, version, firstName, lastName, email, street, city, zip, country, mobilePhone, officePhone, homePhone, checkedGroups);
		if (!r){
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la mise à jour du contact.", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "accueil";
	}
}
