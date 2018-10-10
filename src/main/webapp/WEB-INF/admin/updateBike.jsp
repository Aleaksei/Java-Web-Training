<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
            <c:when test="${not empty bike}">
                <input type="text" hidden name="id" value="${bike.getId()}">
                <label>Название: <input type="text" name="name" value="${bike.getName()}" required> </label>
                <label>Цена:<input type="text" name="cost" value="${bike.getCost()}" required></label>
                <label>Тип:
                    <select name="bikeTypeName">
                        <c:forEach items="${types}" var="type">
                            <c:if test="${bike.getBikeTypeId() == type.getId()}">
                                <option selected value="${type.getId()}">${type.getTypeName()}</option>
                            </c:if>
                            <c:if test="${bike.getBikeTypeId() != type.getId()}">
                                <option value="${type.getId()}">${type.getTypeName()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </label>
                <label>Точка проката:
                    <select name="rentalPlaceId">
                        <c:forEach items="${places}" var="place">
                            <c:if test="${bike.getRentalPlaceId() == place.getId()}">
                                <option selected value="${place.getId()}">${place.getName()}</option>
                            </c:if>
                            <c:if test="${bike.getRentalPlaceId() != place.getId()}">
                                <option value="${place.getId()}">${place.getName()}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </label>
            </c:when>
            <c:otherwise>
                <input type="text" hidden name="id" value="-1">
                <label>Название: <input type="text" name="name" value="" required> </label>
                <label>Цена:<input type="text" name="cost" value="" required></label>
                <label>Тип:
                    <select name="bikeTypeName">
                        <c:forEach items="${types}" var="type">
                            <option value="${type.getId()}">${type.getTypeName()}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>Точка проката:
                    <select name="rentalPlaceId">
                        <c:forEach items="${places}" var="place">
                            <option value="${place.getId()}">${place.getName()}</option>
                        </c:forEach>
                    </select>
                </label>
            </c:otherwise>
        </c:choose>
        <input hidden type="text" name="command" value="saveBike">
        <input type="submit" value="save">
    </form>
</div>
</body>
</html>
