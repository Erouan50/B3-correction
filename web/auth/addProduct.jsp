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
<form method="post" action="<%= request.getContextPath()%>/auth/addProduct">
    Name: <input type="text" id="name" name="name"/><br/>
    Content: <input type="text" id="content" name="content"><br/>
    Price: <input type="text" id="price" name="price"><br/>
    <input type="submit" value="Submit">
</form>
<%@include file="/footer.jsp" %>
</body>
</html>