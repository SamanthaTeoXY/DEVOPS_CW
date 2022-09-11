<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback</title>
</head>
<body>
<h1>Please provide us feedback!</h1>
	<form action="FeedbackServlet" method="post">
		How was the service?: <input type="text" name="service"> <br>Improvements suggestions: <input
			type="text" name="sugggestions"> <br><input type="submit"
			value="Submit" />
	</form>
</body>
</html>