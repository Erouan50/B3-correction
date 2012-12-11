<%@ page import="com.supinfo.sun.supcommerce.bo.SupProduct" %>
<%@ page import="com.supinfo.sun.supcommerce.doa.SupProductDao" %>
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
<% SupProduct product = SupProductDao.findProductById(Long.valueOf(request.getParameter("id")));%>
Id: <%= product.getId()%><br/>
Name: <%= product.getName()%><br/>
Content: <%= product.getContent()%><br/>
Price: <%= product.getPrice()%>
<%@include file="/footer.jsp" %>
</body>
</html>