<%--
  Created by IntelliJ IDEA.
  User: erouan
  Date: 12/11/12
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="<%= request.getContextPath()%>/listProducts.jsp">List Products</a>
<%
    if (session != null && session.getAttribute("username") != null && !((String) session.getAttribute("username")).isEmpty()) {
%>
<a href="<%= request.getContextPath()%>/auth/addProduct.jsp">Add product</a>
<a href="<%= request.getContextPath()%>/logout">Logout</a>
<%
} else {
%>
<a href="<%= request.getContextPath()%>/login.html">Login</a>
<%
    }
%>
