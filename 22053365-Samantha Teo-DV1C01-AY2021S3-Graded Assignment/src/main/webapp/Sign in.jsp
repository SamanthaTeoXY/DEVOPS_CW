<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign in</title>
</head>
<body>
<h1>Sign in</h1>
	<form action="RegisterServlet" method="post">
		Name: <input type="text" name="userName"> <br>Password: <input
			type="password" name="password"> <br><input type="submit"
			value="Sign in" />
	</form>

</body>
</html>