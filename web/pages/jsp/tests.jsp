<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 25.05.2017
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<section class="seltest">
    <c:import url="/WEB-INF/jsp/category.jsp"></c:import>
    <div class="testlist">
        <div class="testlistname">
            <img src="${requestScope.subject.icon}" alt="icon">
            <h2>${requestScope.subject.name}</h2>
        </div>
        <form id="filtersort">
            <c:set var="id" value="${requestScope.subject.id eq null ? '0' : requestScope.subject.id}"/>


            <input type="hidden" value="${id}" name="subject_id">
            <input type="hidden" value="tests" name="command">
            <p id="firststroka"> Total
                tests<br>${requestScope.count}
            <p>
            <p><i class="fa fa-filter" aria-hidden="true"></i> Filters</p>
            <input type="checkbox" name="type_topic" value="topic" ${requestScope.type_topic}> Topic<br>
            <input type="checkbox" name="type_module" value="module" ${requestScope.type_module}> Module<br>
            <input type="checkbox" name="type_course" value="course" ${requestScope.type_course}> Course<br>
            <p>Sort</p>
            <select>
                <option value="date">By name</option>
                <option value="date">By topic</option>
                <option value="date">By date</option>
            </select>
            <button form="filtersort">Show</button>
        </form>

        <div class="testlistsmall">
            <c:if test="${requestScope.tests != null}">
                <c:forEach var="test" items="${requestScope.tests}">
                    <div class="descrtest">
                        <img src="/pages/img/subjectIcons/java.png">
                        <h4><a href="controller?command=test&test_id=${test.id}">${test.name}</a><br>
                                ${test.typeOfTest}</h4>
                        <p>${test.description}</p>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>


</section>
<c:import url="/WEB-INF/jspf/footer.jspf"></c:import>

</body>
</html>
