<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 26.05.2017
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style>
    <%@include file="/pages/css/style.css" %>
</style>
<head>
    <meta charset="utf-8">
    <title>ProTests</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript">


        $(document).ready(function () {
            $('.anotherQuest').click(function () {
                $.ajax({
                    type: 'POST',
                    url: '/controller',
                    data: {
                        questionIndex: $(this).attr("value"),
                        command: 'collect'
                    },
                    success: function (result) {
                        $('#questionField').html(result);
                    }
                });
            });
        });




    </script>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"></c:import>
<section class="selquest">
    <div class="questlist">
        <h2>Questions</h2>
        <input type="hidden" name="command" value="testing">
        <c:forEach var="question" items="${sessionScope.questionList}">
            <button class="anotherQuest" id="anotherQuest" name="questionIndex"
                    value="${question.index}">${question.index+1}</button>
        </c:forEach>
    </div>
    <div class="testlistname">
        <h2>${sessionScope.test.name}</h2>
        <p>Time left:</p><br>
        <div id="timer">
            <div id="minute">56</div>
            &nbsp;:
            <div id="second">45</div>
        </div>
    </div>
    <form action="/controller" id="finishTest" method="post">
        <input type="hidden" name="command" value="result">
    </form>
    <button form="finishTest">Finish test</button>

        <div name="questionField" id="questionField">
            <c:import url="/WEB-INF/jsp/question.jsp"></c:import>
        </div>




</section>
<c:import url="/WEB-INF/jspf/footer.jspf"></c:import>
</body>
</html>