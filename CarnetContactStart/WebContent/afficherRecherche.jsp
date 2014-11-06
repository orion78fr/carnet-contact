<%@page import="codel.Contact"%>
<%@page import="codel.PhoneNumber"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultat de la recherche</title>
</head>
<body>
    <table>
        <tr>
            <th colspan="5"><h2>Rechercher un contact</h2></th>
        </tr>
        <tr>
            <th><h3>Id</h3></th>
            <th><h3>First Name</h3></th>
            <th><h3>Last Name</h3></th>
            <th><h3>Email</h3></th>
            <th><h3>Phones</h3></th>
        </tr>
	    <%
	    @SuppressWarnings("unchecked")
	    List<Contact> al = (List<Contact>) request.getAttribute("liste");
	    for(Contact c : al){%>
	    	<tr>
	    	    <td><%= c.getFirstName() %></td>
	    	    <td><%= c.getLastName() %></td>
	    	    <td><%= c.getEmail() %></td>
	    	    <td><%= c.getAdd().toString() %></td>
	        	   <td>
	        	       <ul>
	        	       <%
	        	       for(PhoneNumber num : c.getProfiles()){
	        	    	   %><li><%= num.getPhoneKind()%> : <%=num.getPhoneNumber() %></li><%
	        	       }
	        	       %>
	        	       </ul>
	        	   </td>
	    	</tr>
	    <%}%>
    </table>
</body>
</html>