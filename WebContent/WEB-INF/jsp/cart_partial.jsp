<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>shopping cart table</title>
</head>
<body>
	<h2>Items in cart :</h2>
	<table>

		<c:forEach var="cartItem" items="${cart.items}">
			<tr>
				<td>${cartItem.product.title}</td>
				<td>${cartItem.quantity}</td>
			</tr>
		</c:forEach>

		<tfoot>
			<tr>
				<th>Total:</th>
				<th>${cart.total}</th>
			</tr>
		</tfoot>
	</table>
</body>
</html>