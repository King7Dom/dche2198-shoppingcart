<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All products</title>
</head>
<body>
<table border = "1">
<thead>
<tr>
<th>Title</th><th>Description</th><th>Price</th><th>Actions </th>
</tr>
</thead>
<tbody>

<c:forEach var="product" items= "${products}"
	varStatus="productCount">
            <tr>
            <td>${product.title}</td>
               <td>${product.description}</td>
               <td>${product.price}</td>
                <td>
                <a href="products/edit/${product.productId}">edit</a>
                <a href="products/delete/${product.productId}">delete</a>
                </td>
           </tr>

</c:forEach>

 </tbody>
</table>

<a href="products/new"> Add a new product. </a> <br />

<a href="${pageContext.request.contextPath}"> Back to home</a>
</body>
</html>