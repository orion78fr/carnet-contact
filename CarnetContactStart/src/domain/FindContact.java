package domain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindContact
 */
public class FindContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindContact() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = request.getParameter("id") == null ? null : Long.parseLong(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phoneNumber");
		
		IDAOContact dao = new DAOContact();
		
		ArrayList<Contact> al = new ArrayList<Contact>();
		
		if(id != null){
			al.add(dao.getContact(id));
		} else if(firstName != null){
			al.addAll(dao.getContactByFirstName(firstName));
		} else if(lastName != null){
			al.addAll(dao.getContactByLastName(lastName));
		} else if(email != null){
			al.addAll(dao.getContactByEmail(email));
		} else if(street != null){
			al.addAll(dao.getContactByStreet(street));
		} else if(city != null){
			al.addAll(dao.getContactByCity(city));
		} else if(zip != null){
			al.addAll(dao.getContactByZip(zip));
		} else if(country != null){
			al.addAll(dao.getContactByCountry(country));
		} else if(phoneNumber != null){
			al.addAll(dao.getContactByPhoneNumber(phoneNumber));
		} else {
			// ?
		}
		
		request.setAttribute("liste", al);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("afficherRecherche.jsp");
		dispatcher.forward(request, response);
	}

}
