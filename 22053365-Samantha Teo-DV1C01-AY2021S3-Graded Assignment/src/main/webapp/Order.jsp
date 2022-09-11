<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<h3>Order Here!</h3>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order</title>
</head>
<body>
	<form action="OrderServlet" method="post">
		<br>Main:<select name="order">
			<option>- select here -</option>
			<option>Chicken Cutlet Set</option>
			<option>Chicken Chop</option>
			<option>Fish n Chips</option>
			<option>Fish Burger</option>
			<option>Chicken Burger</option>
			<option>Spaghetti</option>

		</select><br>Preferred Side:<select name="side">
			<option>- select here -</option>
			<option>French Fries</option>
			<option>Cole Slaw</option>
			<option>Mac N Cheese</option>
			<option>Mashed Potatoes</option>
			<option>Onion Rings</option>
			<option>Corn</option>
		</select><br> Drinks:<select name="drink">
			<option>- select here -</option>
			<option>Milo</option>
			<option>Coca Cola</option>
			<option>Tea</option>
			<option>Coffee</option>
			<option>Cold</option>
		</select> <br> Payment:<select name="payment">
			<option>- select here -</option>
			<option>Cash</option>
			<option>Credit/Debit</option>
			<option>Paylah</option>
			<option>Paynow</option>
		</select> <br> OrderID: <input type="text" name="orderid"> </br>
		<br>
		<input type="submit" value="Place Order" /></br>
	</form>

</body>
</html>