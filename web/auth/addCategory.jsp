<%--
  Created by IntelliJ IDEA.
  User: Antoine Rouaze <antoine.rouaze@zenika.com>
  Date: 12/12/12
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add category</title>
</head>
<body>
<h1>Add category</h1>

<form method="post" action="${pageContext.servletContext.contextPath}/auth/addCategory">
    Name: <input type="text" id="name" name="name">
    <input type="submit" value="Submit">
</form>
</body>
</html>