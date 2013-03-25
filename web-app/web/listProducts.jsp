<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<c:forEach items="${products}" var="product">
    <form method="post" action="${pageContext.servletContext.contextPath}/auth/removeProduct">
        <input type="submit" value="Remove">${product.id}:${product.name}<input name="id" id="id" type="hidden"
                                                                                value="${product.id}"><br/>
    </form>
</c:forEach>

<%@include file="/footer.jsp" %>
</body>
</html>