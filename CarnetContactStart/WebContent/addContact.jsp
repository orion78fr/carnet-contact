<%@page import="domain.ServiceGroup"%>
<%@page import="domain.ContactGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter un contact</title>
</head>
<body>
    <form method="post" action="NewContact">
        <table>
            <tr>
                <th><h2>Ajouter un contact</h2></th>
            </tr>
            <tr>
                <td><i>FistName: <input type="text" name="firstName" size="25"></i></td>
            </tr>
            <tr>
                <td><i>LastName: <input type="text" name="lastName" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Email: <input type="text" name="email" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Street: <input type="text" name="street" size="25"></i></td>
            </tr>
            <tr>
                <td><i>City: <input type="text" name="city" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Zip: <input type="text" name="zip" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Country: <input type="text" name="country" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Mobile phone: <input type="text" name="mobile_phone" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Office phone: <input type="text" name="office_phone" size="25"></i></td>
            </tr>
            <tr>
                <td><i>Home phone: <input type="text" name="home_phone" size="25"></i></td>
            </tr>
            <%
		        List<ContactGroup> l = ServiceGroup.getAllContactGroups();
		        for(ContactGroup cg : l){
		        	%>
		        	<tr>
		        	   <td><input type="checkbox" name="groups" value="<%=cg.getGroupName()%>"> <%=cg.getGroupName()%></td>
		        	</tr>
		        	<%
		        }
	        %>
            <tr>
                <td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
            </tr>
        </table>
    </form>
</body>
</html>