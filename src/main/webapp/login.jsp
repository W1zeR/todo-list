<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="styles/form.css">
</head>
<body>
<div class="flex-container">
    <h1>LOGIN</h1>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <form action="login" method="post">
        <label>Login:<br>
            <input type="text" name="login" maxlength="30" size="30" pattern="^[A-Za-z0-9_]+$" required>
        </label>
        <label>Password:<br>
            <input type="password" name="password" maxlength="30" size="30" pattern="^[A-Za-z0-9!@#$%^&*_=+-]+$"
                   required>
        </label>
        <div>
            Don't have an account? <a href="register">Register</a>
        </div>
        <input type="submit" value="Log in">
    </form>
</div>
</body>
</html>
