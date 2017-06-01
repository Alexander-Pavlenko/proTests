<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 30.05.2017
  Time: 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/pages/css/style.css" %>
</style>
<head>
    <meta charset="UTF-8">
    <title>ProTests</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"></c:import>
<section class="seltest">
    <c:import url="/WEB-INF/jsp/category.jsp"></c:import>
    <div class="testresult">
        <div class="userinfo">
            <div class="photo">
                <img src="${sessionScope.user.photoPath}" alt="My photo">
                <button>Change photo</button>
            </div>
            <textarea disabled>${sessionScope.user.first_name}</textarea>
            <button class="edit">...</button>
            <textarea disabled>${sessionScope.user.last_name}</textarea>
            <button class="edit">...</button>
            <textarea disabled>${sessionScope.user.login}</textarea>
            <button class="edit">...</button>
            <textarea disabled>${sessionScope.user.photoPath}</textarea>
            <button class="edit">...</button>
            <c:if test="${requestScope.isAdmin}">
                <form id="adminOffice" action="/controller">
                    <input type="hidden" name="command" value="adminOffice">
                </form>
                <button form="adminOffice">Admin Office</button>
            </c:if>
            <button>Save</button>
        </div>
        <table>
            <thead>
            <td>Theme</td>
            <td>Result</td>
            </thead>
            <tbody>
            <c:forEach var="result" items="${requestScope.results}">
                <tr>
                    <td>${result.testDto.name}</td>
                    <td>${result.result}%</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</section>
<c:import url="/WEB-INF/jspf/footer.jspf"></c:import>
</body>
</html>