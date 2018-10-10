<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<title>
    Hello page
</title>
<link href="resources/css/loginStyle.css" rel="stylesheet" type="text/css">
<body>

<div>
    <form action="Controller" method="post">

        <h2>Hello guest!</h2>
        <p>Please login</p>
        <label>
            Login:
            <br>
            <input type="text" name="login">
        </label>
        <br/>
        <label>
            Password:
            <br>
            <input type="text" name="password">
        </label>
        <br/>
        <input hidden value="ClientLogin" name="command">
        <input type="submit" value="login"/>
    </form>
</div>

<%--<footer>Обратная связь: example@gmail.com</footer>--%>
</body>
</html>
