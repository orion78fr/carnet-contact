<%@page import="domain.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table>
        <tr>
            <th colspan="4"><h2>Rechercher un contact</h2></th>
        </tr>
        <tr>
            <th><h2>Id</h2></th>
            <th><h2>First Name</h2></th>
            <th><h2>Last Name</h2></th>
            <th><h2>Email</h2></th>
        </tr>
	    <% ArrayList<Contact> al = (ArrayList<Contact>) request.getAttribute("liste");
	    for(Contact c : al){%>
	    	<tr>
	    	    <td><%= c.getId() %></td>
	    	    <td><%= c.getFirstName() %></td>
	    	    <td><%= c.getLastName() %></td>
	    	    <td><%= c.getEmail() %></td>
	    	</tr>
	    <%}%>
    </table>
</body>
</html>