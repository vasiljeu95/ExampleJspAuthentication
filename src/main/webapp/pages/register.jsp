<%--
  Created by IntelliJ IDEA.
  User: stepanvasilyeu
  Date: 8.05.22
  Time: 01:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form method="post" action="sign-up">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" placeholder="Enter your login">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Enter your name">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password">
        <input type="submit" value="Sign up">
    </form>
    <p>${requestScope.Message}</p>
</body>
</html>
