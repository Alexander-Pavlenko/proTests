<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 26.05.2017
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/pages/css/style.css" %>
</style>
<head>
    <meta charset="utf-8">
    <title>ProTests</title>

</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"></c:import>
<c:set var="test" value="${requestScope.test}"/>
<section class="seltest">
    <c:import url="/WEB-INF/jsp/category.jsp"></c:import>
    <div class="testname">
        <img src="${test.pathToIcon}" alt="logotest">
        <h2>${test.name}<br></h2>
        <h3>${test.typeOfTest}<br></h3><br>
        <h3>${test.timeTest}<br></h3><br>
        <p>${test.description}<p><br>
        <br><br>
        <form id="startTest" action="controller">
            <input type="hidden" name="test_id" value="${test.id}">
            <input type="hidden" name="command" value="start">
            <button form="startTest">Start test</button>
        </form>
    </div>
</section>
<c:import url="/WEB-INF/jspf/footer.jspf"></c:import>
</body>
</html>