<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rechercher un contact</title>
</head>
<body>
    <table>
        <tr>
            <th><h2>Rechercher un contact</h2></th>
        </tr>
        <tr>
            <td>
                <form method="post" action="FindContact">
                    <i>Par Id: <input type="text" name="id" size="25"></i>
                    <input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form method="post" action="FindContact">
                    <i>Par Prénom: <input type="text" name="firstName" size="25"></i>
                    <input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form method="post" action="FindContact">
                    <i>Par Nom: <input type="text" name="lastName" size="25"></i>
                    <input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form method="post" action="FindContact">
                    <i>Par Email: <input type="text" name="email" size="25"></i>
                    <input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>