package domain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceContact;

/**
 * Servlet implementation class ModifyContact
 */
public class ModifyContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyContact() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Integer version = Integer.parseInt(request.getParameter("version"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String mobile_phone = request.getParameter("mobile_phone");
		String office_phone = request.getParameter("office_phone");
		String home_phone = request.getParameter("home_phone");
		String[] groups = request.getParameterValues("groups");
		
		ServiceContact.modifyContact(id, version, firstName, lastName, email, street, city, zip, country, mobile_phone, office_phone, home_phone, groups);
		
		response.sendRedirect("accueil.jsp");
	}

}
