<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Logging page</title>
    <link href="resources/css/loginStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>

    <form action="Controller" method="post">

        <h2>Hello administrator!</h2>
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
        <input hidden value="AdminLogin" name="command">
        <input type="submit" value="login"/>
    </form>
</div>
</body>
</html>
