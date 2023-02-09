<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New ToDo</title>
    <link rel="stylesheet" href="<c:url value="/styles/form.css"/>">
</head>
<body>
<div class="flex-container">
    <h1>NEW TODO</h1>
    <form action="new" method="post">
        <label>Comment:<br>
            <input type="text" name="comment" maxlength="100" size="30" required>
        </label>
        <label>Should be done before:<br>
            <input type="date" name="shouldBeDoneBefore" required>
        </label>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
