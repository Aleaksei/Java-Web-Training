<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bike</title>
    <link href="../../resources/css/other.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="AdminNavigationElements.html" %>
<div class="main">
    <form action="Controller" method="post">
        <input hidden name="command" value="forwardBikeUpdate">
        <input type="submit" value="Add New">
    </form>


    <table>
        <thead>
        <tr>
            <td>Name</td>
            <td>Type</td>
            <td>Cost</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bikes}" var="bike">
            <tr>
                <td>${bike.getName()}</td>
                <td>
                    <c:forEach items="${types}" var="type">
                        <c:if test="${bike.getBikeTypeId() == type.getId()}">
                            ${type.getTypeName()}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${bike.getCost()}</td>
                <td>
                    <form action="Controller" method="post">
                        <input hidden name="id" value="${bike.getId()}">
                        <input hidden name="command" value="forwardBikeUpdate">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="Controller" method="post">
                        <input hidden type="text" name="id" value="${bike.getId()}">
                        <input hidden name="command" value="deleteBike">
                        <input type="submit" value="delete">
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
