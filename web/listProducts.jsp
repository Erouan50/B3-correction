<%@ page import="com.supinfo.sun.supcommerce.bo.SupProduct" %>
<%@ page import="com.supinfo.sun.supcommerce.doa.SupProductDao" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Antoine Rouaze <antoine.rouaze@zenika.com>
  Date: 12/11/12
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Products</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<h1>List products</h1>

<% List<SupProduct> products = SupProductDao.getAllProducts();
    for (SupProduct product : products) {
%>
<form method="post" action="${pageContext.servletContext.contextPath}/auth/removeProduct">
    <input type="submit" value="Remove"><%= product.getId()%>:<%= product.getName()%><br/><input name="id" id="id"
                                                                                                 type="hidden"
                                                                                                 value="<%= product.getId()%>">
</form>
<%
    }
%>
<%@include file="/footer.jsp" %>
</body>
</html>