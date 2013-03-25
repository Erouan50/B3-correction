<%--
  Created by IntelliJ IDEA.
  User: Antoine Rouaze <antoine.rouaze@zenika.com>
  Date: 12/11/12
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Product</title>
</head>
<body>
<h1>Show product</h1>
<%@ include file="/header.jsp" %>
Id: ${product.id}<br/>
Name: ${product.name}<br/>
Content: ${product.content}<br/>
Price: ${product.price}
Category: ${product.category.name}
<%@include file="/footer.jsp" %>
</body>
</html>