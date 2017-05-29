<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 29.05.2017
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>ProTests</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css">

</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"></c:import>
<section class="seltest">
    <c:import url="/WEB-INF/jsp/category.jsp"></c:import>
    <div class="testresult">
        <h2>Test TestName passed 90%</h2>
        <p>Date of passing the test<br>12.05.2017</p>
        <table>
            <thead>
            <td>Theme</td>
            <td>Result</td>
            </thead>
            <tbody>
            <tr>
                <td>NameTheme</td>
                <td>10%</td>
            </tr>
            <tr>
                <td>NameTheme</td>
                <td>10%</td>
            </tr>
            <tr>
                <td>Namesfg sdf waf weefE F AADF SD FTheme</td>
                <td>10%</td>
            </tr>
            <tr>
                <td>NameTheme</td>
                <td>10%</td>
            </tr>
            <tr>
                <td>NameTheme</td>
                <td>10%</td>
            </tr>
            </tbody>
        </table>
        <button>Show my answers</button>
    </div>
</section>
<c:import url="/WEB-INF/jspf/footer.jspf"></c:import>
</body>
</html>