package mbeans;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Accueil implements Serializable {
	private static final long serialVersionUID = 1L;
	private int page = 0;
	private int pageSize = 25;
	private String locale = "en";
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale){
		this.locale = locale;
		//FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPage() {
		if(this.page > lastPageNum()){
			this.page = lastPageNum();
		}
		if(this.page < 0){
			this.page = 0;
		}
		return page;
	}

	public String nextPage() {
		this.page++;
		if(this.page > lastPageNum()){
			this.page = lastPageNum();
		}
		return "accueil";
	}
	
	public String prevPage() {
		this.page--;
		if(this.page < 0){
			this.page = 0;
		}
		return "accueil";
	}
	
	public String firstPage() {
		this.page = 0;
		return "accueil";
	}
	
	public int lastPageNum(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ListContacts list
		    = (ListContacts) facesContext.getApplication()
		    .getVariableResolver().resolveVariable(facesContext, "listContacts");
		
		int listSize = list.getListContactsSize();
		
		if(listSize % pageSize != 0){
			return (listSize / pageSize);
		} else {
			return (listSize / pageSize) - 1;
		}
	}
	
	public String lastPage() {
		this.page = lastPageNum();
		return "accueil";
	}
	
}
