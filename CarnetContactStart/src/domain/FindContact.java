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
        // TODO Auto-generated constructor stub
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
		
		IDAOContact dao = new DAOContact();
		
		ArrayList<Contact> al = new ArrayList<Contact>();
		
		if(id != null){
			al.add(dao.getContact(id));
		} else if(firstName != null){
			al.addAll(dao.getContactByFirstName(firstName));
		} else if(lastName != null){
			al.addAll(dao.getContactByLastName(lastName));
		} else {
			al.addAll(dao.getContactByEmail(email));
		}
		
		request.setAttribute("liste", al);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("afficherRecherche.jsp");
		dispatcher.forward(request, response);
	}

}
