<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 31.05.2017
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="/pages/css/style.css" %>
</style>
<head>
    <title>Title</title>
</head>
<body>
<table class="table">
    <thead>
    <td>Menu</td>
    <td>List user</td>

    </thead>
    <tbody>
    <tr>
        <td>
            <c:import url="/WEB-INF/jsp/menuButton.jsp"></c:import>
        </td>
        <td>
            <table class="table">
                <thead>

                <td>Id</td>
                <td>Firs name</td>
                <td>Last name</td>
                <td>Login</td>
                <td>E-mail</td>
                <td>Number</td>
                <td>Role</td>
                <td>Date</td>
                <td>IsBlocked</td>
                <td>Action</td>
                <td></td>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                <tr>

                        <td>
                                ${user.id}
                        </td>
                        <td>
                                ${user.first_name}
                        </td>
                        <td>
                                ${user.last_name}
                        </td>
                        <td>
                                ${user.login}
                        </td>
                        <td>
                                ${user.e_mail}
                        </td>
                        <td>
                                ${user.phoneNumber}
                        </td>
                        <td>
                                ${user.role}
                        </td>
                        <td>
                                ${user.dateRegestration}
                        </td>
                        <td>
                                ${user.isBlocked}
                        </td>
                        <td>
                            <form id="blocking" action="/controller">
                                <input type="hidden" name="command" value="blocking">
                                <input type="hidden" name="user_id" value="${user.id}">



                                <c:if test="${user.isBlocked}">
                                    <input type="hidden" name="action" value="false">
                                    <button class="buttonBlocking" form="blocking">UnBlock</button>
                                </c:if>
                                <c:if test="${!user.isBlocked}">
                                    <input type="hidden" name="action" value="true">
                                    <button class="buttonBlocking" form="blocking">Block</button>
                                </c:if>
                            </form>
                        </td>


                </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>


    </tr>

    </tbody>

</table>
</body>
</html>
