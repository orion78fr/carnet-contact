package domain;

import java.util.HashSet;
import java.util.Set;

public class Contact {
	private String firstName;
	private String lastName;
	private String email;
	private long id;
	private Address add;
	private Set<PhoneNumber> profiles;
	private Set<ContactGroup> books;
	private int version;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}

	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfile(PhoneNumber num){
		this.profiles.add(num);
		num.setContact(this);
	}

	public Set<ContactGroup> getBooks() {
		return this.books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}
	
	public void addBook(ContactGroup g){
		this.books.add(g);
		if(!g.getContacts().contains(this)){
			g.addContact(this);
		}
	}
	
	public void delBook(ContactGroup g){
		this.books.remove(g);
		if(g.getContacts().contains(this)){
			g.delContact(this);
		}
	}

	public Contact(){
		super();
		this.profiles = new HashSet<PhoneNumber>();
		this.books = new HashSet<ContactGroup>();
	}

	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	
}
