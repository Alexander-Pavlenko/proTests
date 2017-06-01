<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
<fmt:setBundle basename="${sessionScope.language}"/>
<img src="/pages/img/icon/logo.png" alt="logo">
<nav>
    <ul>
        <li><a href="/controller?command=home"  class="${requestScope.buttonHome}"><fmt:message key="home"/></a></li>
        <li><a href="/controller?command=tests"  class="${requestScope.buttonTests}"><fmt:message key="tests"/></a></li>
        <li><a href="/controller?command=profile"  class="${requestScope.buttonProfile}"><fmt:message key="profile"/></a></li>
        <li><a href="/controller?command=home"  class="${requestScope.buttonAbout}"><fmt:message key="aboutUs"/></a></li>
    </ul>
</nav>

<c:set var="user" value="${sessionScope.user}"/>
<c:if test="${user != null}">
    <form action="/controller" id="logOut" method="post">
        <input type="hidden" name="command" value="logout"/>
        <button id="btnlogin" form="logOut"><fmt:message key="logout"/></button>
    </form>
</c:if>
<c:if test="${user == null}">
    <form action="/controller" method="post" id="logIn">
        <input type="hidden" name="command" value="login"/>
        <button id="btnlogin" form="logIn"><fmt:message key="login"/></button>
    </form>
</c:if>

<form id="selectLang" action="/controller" method="post">
    <div class="langs">
        <button class="${requestScope.langeng}" id="langeng" name="language" value="language.resources_en" form="selectLang">Eng</button>
        <button class="${requestScope.langrus}" id="langrus" name="language" value="language.resources_ru" form="selectLang">Rus</button>
        <button class="${requestScope.langukr}" id="langukr" name="language" value="language.resources_ru" form="selectLang">Ukr</button>
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
        <a href="/controller?command=profile" title="My results"><fmt:message key="myResults"/></a>
    </div>
</c:if>
</header>