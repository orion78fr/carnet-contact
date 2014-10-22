<%@page import="domain.PhoneNumber"%>
<%@page import="domain.ServiceContact"%>
<%@page import="domain.ServiceGroup"%>
<%@page import="domain.Contact"%>
<%@page import="domain.ContactGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bonjour</title>
<style type="text/css">
    table{
        border: 1px solid black;
        border-collapse: collapse;
    }
    td, th{
        border: 1px solid black;
        padding: 10px;
        vertical-align: middle;
    }
    th{
        text-align: center;
    }
</style>
</head>
<body>
    Hello <% if(request.getParameter("user") != null){ %>Mr. <%= request.getParameter("user") %><%} %>!<br />
    <br />
    <a href="addContact.jsp">Créer un contact</a> <br />
    <a href="addGroup.jsp">Créer un group de contact</a><br />
    <a href="searchContact.jsp">Rechercher un contact</a> <br />
    <br />
    <a href="ContactGenerator?nb=50">Ajouter 50 contacts</a> <br />
    <br />
    <br />
    
    <table>
        <tr>
            <th colspan="3"><h2>Groupes de contacts</h2></th>
        </tr>
        <tr>
            <th><h3>Nom du groupe</h3></th>
            <th><h3>Contacts</h3></th>
        </tr>
        <%
        List<ContactGroup> lg = ServiceGroup.getAllGroupsAndContacts();
        for(ContactGroup cg : lg){
        	%>
        	<tr>
        	   <td><%= cg.getGroupName()%></td>
        	   <td>
        	       <ul>
        	       <%
        	       for(Contact c : cg.getContacts()){
        	    	   %><li><%=c.getFirstName() %> <%=c.getLastName() %></li><%
        	       }
        	       %>
        	       </ul>
        	   </td>
        	   <td>
        	       <form action="DeleteGroup" method="post">
                       <input type="hidden" name="id" value ="<%= cg.getId() %>"/>
                       <input class="button" type="submit" value="Supprimer"/>
                   </form>
        	   </td>
        	</tr>
        	<%
        }
        %>
    </table>
    
    <br />
    <br />
    
    <table>
        <tr>
            <th colspan="7"><h2>Liste des contacts</h2></th>
        </tr>
        <tr>
            <th><h3>First Name</h3></th>
            <th><h3>Last Name</h3></th>
            <th><h3>Email</h3></th>
            <th><h3>Address</h3></th>
            <th><h3>Phones</h3></th>
            <th><h3>Groups</h3></th>
            <th><h3>Options</h3></th>
        </tr>
        <%
        List<Contact> l = ServiceContact.getAllContactsAndGroups();
        for(Contact c : l){
        	%>
        	<tr>
        	   <td><%= c.getFirstName()%></td>
        	   <td><%= c.getLastName()%></td>
        	   <td><%= c.getEmail()%></td>
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
        	   <td>
        	       <ul>
        	       <%
        	       for(ContactGroup cg : c.getBooks()){
        	    	   %><li><%=cg.getGroupName() %></li><%
        	       }
        	       %>
        	       </ul>
        	   </td>
        	   <td>
        	       <form action="updateContact.jsp" method="post">
        	           <input type="hidden" name="id" value ="<%= c.getId() %>"/>
        	           <input class="button" type="submit" value="Modifier"/>
        	       </form>
        	       <form action="DeleteContact" method="post">
                       <input type="hidden" name="id" value ="<%= c.getId() %>"/>
                       <input class="button" type="submit" value="Supprimer"/>
                   </form>
        	   </td>
        	</tr>
        	<%
        }
        %>
    </table>
    
</body>
</html>