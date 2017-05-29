<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<input type="hidden" name="command" value="/home">
<style>
    <%@include file="/pages/css/style.css" %>
</style>
<head>
    <meta charset="utf-8">
    <title>ProTests</title>

</head>
<body>

<c:import url="/WEB-INF/jsp/header.jsp"></c:import>
<section class="welcome">
    <h1>Welcome to ProTests</h1>
    <div class="tests">
        <form action="/controller" id="goTests">
            <input type="hidden" name="command" value="tests">
            <c:forEach items="${requestScope.subjects}" var="subject">
                <button form="goTests" name="subject_id" value="${subject.id}"><img src="${subject.icon}" alt="${subject.name} test"></button>
            </c:forEach>
        </form>

    </div>
</section>
<c:import url="/WEB-INF/jspf/footer.jspf"></c:import>

</body>
</html>