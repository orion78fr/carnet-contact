<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter un group</title>
</head>
<body>
    <form method="post" action="NewGroup">
        <table>
            <tr>
                <th><h2>Ajouter un group</h2></th>
            </tr>
            <tr>
                <td><i>Group Name: <input type="text" name="groupName" size="25"></i></td>
            </tr>
            <tr>
                <td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
            </tr>
        </table>
    </form>
</body>
</html>