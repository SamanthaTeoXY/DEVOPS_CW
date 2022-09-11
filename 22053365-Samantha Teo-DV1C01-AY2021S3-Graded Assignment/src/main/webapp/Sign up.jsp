<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
</head>
<body>
	<h1>Sign up!</h1>
	<form action="RegisterServlet" method="post">
		Name: <input type="text" name="userName"> <br>Password: <input
			type="password" name="password"> <br>Email: <input
			type="text" name="email"> <br>Address: <input
			type="text" name="address"> <input type="submit"
			value="Sign up" />
	</form>

</body>
</html>