<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Antoine Rouaze <antoine.rouaze@zenika.com>
  Date: 12/11/12
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<form method="post" action="${pageContext.servletContext.contextPath}/auth/addProduct">
    Name: <input type="text" id="name" name="name"/><br/>
    Content: <input type="text" id="content" name="content"><br/>
    Price: <input type="text" id="price" name="price"><br/>
    Category: <select id="idCategory" name="idCategory">
    <c:forEach items="${categories}" var="category">
        <option value="${category.id}">${category.name}</option>
    </c:forEach>
</select>
    <input type="submit" value="Submit">
</form>
<%@include file="/footer.jsp" %>
</body>
</html>