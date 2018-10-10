<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../resources/css/other.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="AdminNavigationElements.html" %>
<div class="main">

    <form action="Controller" method="post">
        <c:choose>
            <c:when test="${not empty place}">
                <input type="text" hidden name="id" value="${place.getId()}">
                <label>Название: <input type="text" name="name" value="${place.getName()}" required> </label>
                <label>Адрес:<input type="text" name="address" value="${place.getAddress()}" required></label>
            </c:when>
            <c:otherwise>
                <input type="text" hidden name="id" value="-1">
                <label>Название: <input type="text" name="name" value="" required></label>
                <label>Адрес:<input type="text" name="address" value="" required></label>
            </c:otherwise>
        </c:choose>
        <input hidden type="text" name="command" value="savePlace">
        <input type="submit" value="save">
    </form>
</div>
</body>
</html>
