<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 27.05.2017
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
</head>
<body>
<p style="text-align: center">
<h1>ERROR 404</h1></p><br>
<form action="/controller" id="back">
    <input type="hidden" value="home" name="command">
</form>
<p><button form="back">Home page</button></p>
</body>
</html>
