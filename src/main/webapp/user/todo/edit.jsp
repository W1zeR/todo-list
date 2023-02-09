<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit ToDo</title>
    <link rel="stylesheet" href="<c:url value="/styles/form.css"/>">
</head>
<body>
<div class="flex-container">
    <h1>EDIT TODO</h1>
    <form action="edit" method="post">
        <input type="hidden" name="todoId" value="<c:out value="${todo.getId()}"/>"><br>
        <label>Comment:<br>
            <input type="text" name="comment" maxlength="100" size="30" value="<c:out value="${todo.getComment()}"/>"
                   required>
        </label>
        <label>Created:<br>
            <input type="date" value="<c:out value="${todo.getCreated()}"/>" disabled>
        </label>
        <label>Should be done before:<br>
            <input type="date" name="shouldBeDoneBefore" value="<c:out value="${todo.getShouldBeDoneBefore()}"/>"
                   required>
        </label>
        <label>Status:<br>
            <c:if test="${todo.getStatus().ordinal() eq 0}">
                <input type="radio" name="status" value="Created" checked>Created
                <input type="radio" name="status" value="Overdue" disabled>Overdue
                <input type="radio" name="status" value="Done">Done
            </c:if>
            <c:if test="${todo.getStatus().ordinal() eq 1}">
                <input type="radio" name="status" value="Created" disabled>Created
                <input type="radio" name="status" value="Overdue" checked>Overdue
                <input type="radio" name="status" value="Done">Done
            </c:if>
            <c:if test="${todo.getStatus().ordinal() eq 2}">
                <input type="radio" name="status" value="Created" disabled>Created
                <input type="radio" name="status" value="Overdue" disabled>Overdue
                <input type="radio" name="status" value="Done" checked>Done
            </c:if>
        </label>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
