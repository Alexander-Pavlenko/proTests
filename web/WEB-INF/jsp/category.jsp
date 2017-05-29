<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 25.05.2017
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="category">
    <h2>Category</h2>
    <ul>
        <form id="category">
            <c:forEach items="${sessionScope.subjects}" var="subject">
                <li>
                    <button name="subject_id" form="category" value="${subject.id}">${subject.name}</button>
                </li>
            </c:forEach>
        </form>
    </ul>
</div>