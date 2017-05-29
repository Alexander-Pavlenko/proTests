<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 23.05.2017
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"> <!--<![endif]-->
<style>
    <%@include file="/pages/css/loginStyle.css" %>
    <%@include file="/pages/css/demo.css" %>
</style>
<head>
    <meta charset="UTF-8"/>
    <title>Вход</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <header>
        <script type="text/javascript">
            function validate_form() {
                var valid = true;
                var massage = null;
                var text = null;
                var reg = "[$%!#'/\\\"]";
                if ((text = document.getElementById("username").value) != "") {

                    if (text.match(reg) != null) {
                        massage = "Поле 'логин' не должен сожержать слудуещее симолы $%!#'/\\\"";
                        valid = false;
                    }
                }

                else {
                    massage = "Пожалуйста, введите данные в поле 'логин'.";
                    valid = false;
                }
                if ((text = document.getElementById("password").value) != "") {
                    if (text.match(reg) != null) {
                        massage = "Поле 'пароль' не должен сожержать слудуещее симолы $%!#'/\\\"";
                        valid = false;
                    }
                }
                else {
                    massage = "Пожалуйста, введите пароль в поле 'пароль'.";
                    valid = false;
                }

                $("#massage").text(massage);
                return valid;
            }
        </script>
    </header>
    <section>
        <div id="container_demo">
            <div id="wrapper">
                <div id="login">
                    <form action="controller" method="post" onsubmit="return validate_form();">
                        <c:set var="massage" value="${requestScope.massage}"></c:set>
                        <c:set var="login" value="${requestScope.login}"></c:set>
                        <h1>Log in</h1>

                        <p>
                            <label for="username" class="uname"> Ваш email или логин </label>
                            <input id="username" name="login" type="text" value="${login}"
                                   placeholder="myusername or mymail@mail.com"/>
                        </p>
                        <p>
                            <label for="password" class="youpasswd"> Ваш пароль </label>
                            <input id="password" name="password" type="password"
                                   placeholder="eg. X8df!90EO"/>
                        </p>
                        <input type="hidden" name="command" value="login">
                        <label id="massage"  class="uname">${massage}</label>
                        <p class="login button">
                            <input type="submit" value="Login"
                            name="login"/>
                        </p>
                        <p class="change_link">
                            Not a member yet ?
                            <a href="/controller?command=login&goRegistration=true" class="to_register">Join us</a>
                        </p>

                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
