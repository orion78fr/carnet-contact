<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier un contact</title>
</head>
<body>
        <form method="post" action="ModifyContact">
        <table>
            <tr>
                <th><h2>Ajouter un contact</h2></th>
            </tr>
            <tr>
                <td><i>Id: <input type="text" name="id" size="25"></i></td>
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
                <td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
            </tr>
        </table>
    </form>
</body>
</html>