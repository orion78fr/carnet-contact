<%@page import="codel.PhoneNumber"%>
<%@page import="codel.Contact"%>
<%@page import="codel.ContactGroup"%>
<%@page import="service.ServiceContact"%>
<%@page import="service.ServiceGroup"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% ContactGroup cg = ServiceGroup.getGroup(Long.parseLong(request.getParameter("id"))); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier un groupe</title>
</head>
<body>
        <form method="post" action="ModifyGroup">
        <input type="hidden" name="id" value="<%= request.getParameter("id")%>"/>
        <table>
            <tr>
                <th><h2>Modifier un groupe</h2></th>
            </tr>
            <tr>
                <td><i>Group Name: <input type="text" name="groupName" size="25" value="<%= cg.getGroupName()%>"/></i></td>
            </tr>
            <%
		        Set<Contact> l = cg.getContacts();
		        for(Contact c : l){
		        	%>
		        	<tr>
		        	   <td><input type="checkbox" name="contacts" value="<%=c.getId()%>" checked="checked" /> <%=c.getFirstName()%> <%=c.getLastName() %></td>
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