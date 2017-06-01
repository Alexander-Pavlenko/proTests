<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="user" action="/controller">
    <input type="hidden" name="command" value="adminOfficeUser">
    <button class="button_menu" form="user">User</button>
</form>
<form id="test" action="/controller">
    <input type="hidden" name="command" value="adminOffice">
    <button class="button_menu" form="test">Test</button><br>
</form>
<label id="massage">${requestScope.massage}</label>