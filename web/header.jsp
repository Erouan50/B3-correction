<%--
  Created by IntelliJ IDEA.
  User: erouan
  Date: 12/11/12
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="${pageContext.servletContext.contextPath}/listProducts">List Products</a>
<c:choose>
    <c:when test="${!empty sessionScope.username}">
        <a href="${pageContext.servletContext.contextPath}/auth/addProduct">Add product</a>
        <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
    </c:when>
    <c:when test="${empty sessionScope.username}">
        <a href="${pageContext.servletContext.contextPath}/login">Login</a>
    </c:when>
</c:choose>