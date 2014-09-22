<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bonjour</title>
</head>
<body>
    Hello <% if(request.getParameter("name") != null){ %>Mr. <%= request.getParameter("name") %><%} %>!<br />
    <br />
    <a href="addContact.jsp">Créer un contact</a> <br />
    <a href="removeContact.jsp">Supprimer un contact</a> <br />
    <a href="updateContact.jsp">Modifier un contact</a> <br />
    <a href="searchContact.jsp">Rechercher un contact</a>
    
</body>
</html>