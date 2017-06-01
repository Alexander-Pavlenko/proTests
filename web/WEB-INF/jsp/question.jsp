<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 27.05.2017
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>

<style>
    <%@include file="/pages/css/style.css" %>
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('.answerButton').click(function () {
            var checks = "";
            $("input[name='answer']:checked").each(function () {
                checks +=($(this).val()) + ',';
            });




            $.ajax({
                type: 'get',
                url: '/controller',

                data: {
                    questionIndex: $(this).attr("value"),
                    answer: checks,
                    command: 'collect'
                },
                success: function (result) {
                    $('#questionField').html(result);
                }
            });
        });
    });
</script>
<div class="question">
    <c:if test="${requestScope.finished eq 'finished'}">
        <p style="text-align: center">
        <h1>You passed all test!</h1></p>
        <p style="text-align: center">
        <h3>You can press the button "Finish test" and see results</h3></p>
    </c:if>
    <c:if test="${requestScope.finished == null}">

        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <input type="hidden" name="command" value="collect">
        <input type="hidden" name="questionIndex" value="${requestScope.questionIndex}">
        <h3>${requestScope.question.question.question}</h3>
        <textarea disabled>${requestScope.question.question.code}</textarea>
        <!--<input class="questtext" type="text" readonly>-->
        <c:forEach items="${requestScope.question.question.answers}" var="answer">
            <input type="${requestScope.box}" name="answer" value="${answer.id}"
                   id="ch">${answer.answer}<br>
        </c:forEach>


        <button id="answerButton" class="answerButton" value="${requestScope.questionIndex}">Answer</button>
    </c:if>
</div>

