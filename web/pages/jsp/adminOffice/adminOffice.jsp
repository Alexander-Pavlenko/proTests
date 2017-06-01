<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 30.05.2017
  Time: 12:54
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
    <td>Create Subject</td>
    <td>Create Test</td>
    <td>Create Create quest</td>
    </thead>
    <tbody>
    <tr>
        <td>
            <c:import url="/WEB-INF/jsp/menuButton.jsp"></c:import>


        </td>
        <td>
            <form action="/controller" id="selectSubject">
                <input type="hidden" name="command" value="???????????????????">
                <p><select class="selectButton" name="subject_id">
                    <option>Chose subject</option>
                    <c:forEach items="${requestScope.subjects}" var="subject">
                        <option value="${subject.id}">${subject.name}</option>
                    </c:forEach>
                </select><br>
                    <button class="buttonSubmit" form="selectSubject">Select</button>
            </form>
            <form action="/controller">
                <input type="hidden" name="command" value="createSubject">

                <input class="field" name="name" type="text" placeholder="Name"/><br>
                <input class="field" name="icon" type="text" placeholder="PathToIcon"/><br>
                <input type="submit" value="Save"/>
            </form>
        </td>


        <td>
            <form action="/controller" id="selectTest">
                <input type="hidden" name="command" value="?????????????????????">
                <p><select class="selectButton" name="test_id">
                    <option>Chose test</option>
                    <c:forEach var="test" items="${requestScope.tests}">
                        <option value="${test.id}">${test.name}</option>
                    </c:forEach>
                </select><br>
                    <button class="buttonSubmit" form="selectTest">Select</button>
            </form>
            <form action="/controller">
                <input type="hidden" name="command" value="createTest">
                <input class="field" name="name" type="text" placeholder="Name"/><br>
                <input class="field" name="icon" type="text" placeholder="PathToIcon"/><br>
                <p><select class="selectButton" name="subject_id">
                    <option>Chose subject</option>
                    <c:forEach items="${requestScope.subjects}" var="subject">
                        <option value="${subject.id}">${subject.name}</option>
                    </c:forEach>
                </select><br>
                <p><select class="selectButton" name="type">
                    <option>Chose type</option>
                    <c:forEach var="type" items="${types}">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select><br>
                    <textarea class="bigField" name="description"
                              placeholder="Description"></textarea><br>
                    <input name="timeForTest" type="text" placeholder="Time for Test"/><br>
                    <input type="submit" value="Save"/>
            </form>
        </td>


        <td>
            <form action="/controller" id="selectQuestion">
                <input type="hidden" name="command" value="?????????????????????">
                <p><select id="selectQuest" class="selectButton" name="question">
                    <option>Chose test</option>
                    <c:forEach var="question" items="${requestScope.questions}">
                        <option value="${question.id}">${question.question}</option>
                    </c:forEach>
                </select><br>
                    <button class="buttonSubmit" form="selectTest">Select</button>
            </form>
            <form action="/controller">
                <input type="hidden" name="command" value="createQuestion">
                <textarea class="littleField" name="question"
                          placeholder="Question?"></textarea><br>
                <p><select class="selectButton" name="test_id">
                    <option>Chose test</option>
                    <c:forEach var="test" items="${requestScope.list}">
                        <option value="${test.id}">${test.name}</option>
                    </c:forEach>
                </select><br>
                    <textarea class="littleField" name="code"
                              placeholder="Code"></textarea><br>
                    <c:forEach begin="0" end="7" var="i">
                    <textarea class="answerField" name="answer${i}"
                              placeholder="Code">${requestScope.question.question.code}</textarea>
                    <input value="${i}" name="truthful" type="checkbox"><br>
                    </c:forEach>

                    <input type="submit" value="Save"/>
            </form>
        </td>

    </tr>

    </tbody>
</table>
</body>
</html>
