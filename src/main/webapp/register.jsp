<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="styles/form.css">
</head>
<body>
<div class="flex-container">
    <h1>REGISTER</h1>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>
    <form action="register" method="post">
        <label>Login:<br>
            <input type="text" name="login" maxlength="30" size="30" pattern="^[A-Za-z0-9_]+$" required>
        </label>
        <label>Password:<br>
            <input type="password" name="password" maxlength="30" size="30" pattern="^[A-Za-z0-9!@#$%^&*_=+-]+$"
                   required>
        </label>
        <div>
            Already have an account? <a href="login">Log in</a>
        </div>
        <input type="submit" value="Register">
    </form>
</div>
</body>
</html>
