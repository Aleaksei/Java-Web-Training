<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Rental Place</title>
    <link href="../../resources/css/other.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="AdminNavigationElements.html" %>
<div class="main">
    <form action="Controller" method="post">
        <input hidden name="command" value="forwardPlaceUpdate">
        <input type="button" value="Add New">
    </form>

    <table>
        <c:forEach items="${places}" var="place">
            <tr>
                <td>
                        ${place.getName()}
                </td>
                <td>
                        ${place.getAddress()}
                </td>
                <td>
                    <form action="Controller" method="post">
                        <input hidden type="text" name="id" value="${place.getId()}">
                        <input hidden name="command" value="forwardPlaceUpdate">
                        <input type="submit" value="edit">
                    </form>
                </td>
                <td>
                    <form action="Controller" method="post">
                        <input hidden type="text" name="id" value="${place.getId()}">
                        <input hidden name="command" value="deletePlace">
                        <input type="submit" value="delete">
                    </form>
                </td>

            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
