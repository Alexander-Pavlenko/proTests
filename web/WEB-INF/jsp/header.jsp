<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>

<img src="/pages/img/icon/logo.png" alt="logo">
<nav>
    <ul>
        <li><a href="/controller?command=home" title="Home" class="${requestScope.buttonHome}">Home</a></li>
        <li><a href="/controller?command=tests" title="Tests" class="${requestScope.buttonTests}">Tests</a></li>
        <li><a href="/controller?command=profile" title="Profile" class="${requestScope.buttonProfile}">Profile</a></li>
        <li><a href="/controller?command=aboutUs" title="About Us" class="${requestScope.buttonAbout}">About Us</a></li>
    </ul>
</nav>

<c:set var="user" value="${sessionScope.user}"/>
<c:if test="${user != null}">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="logout"/>
        <button id="btnlogin">LOGOUT</button>
    </form>
</c:if>
<c:if test="${user == null}">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <button id="btnlogin">LOGIN</button>
    </form>
</c:if>

<form id="selectLang" action="/controller" method="post">
    <div class="langs">
        <button class="${requestScope.langeng}" id="langeng" name="lang" value="eng" form="selectLang">Eng</button>
        <button class="${requestScope.langrus}" id="langrus" name="lang" value="rus" form="selectLang">Rus</button>
        <button class="${requestScope.langukr}" id="langukr" name="lang" value="ukr" form="selectLang">Ukr</button>
    </div>
    <input type="hidden" name="command" value="language"/>
</form>

<div class="search">
    <form id="search" method="post" action="/controller">
        <input type="text" name="search" size="35" value="${requestScope.search}" placeholder=" Search...">
        <input type="hidden" name="command" value="tests"/>
    </form>
    <button form="search" name="search_button" value="click"><img src="/pages/img/icon/search.png"></button>
</div>


<c:if test="${user != null}">
    <div class="login">
        <img src="${user.photoPath}" alt="avatar">
        <p>${user.first_name} ${user.last_name}</p>
        <br>
        <a href="results.html?command=profile" title="My results">My results</a>
    </div>
</c:if>
</header>