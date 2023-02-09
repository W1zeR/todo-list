<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ToDo List</title>
    <link rel="stylesheet" href="../styles/todo.css">
</head>
<body>
<div class="logout">
    <a href="logout">Logout</a>
</div>
<h1>Your ToDo List</h1>
<div class="btn-container">
    <a href="todo/new" class="btn">Add new ToDo</a>
</div>
<jsp:useBean id="todoList" scope="request" type="java.util.List"/>
<c:if test="${empty todoList}">
    <div class="empty">It's empty!</div>
</c:if>
<c:if test="${not empty todoList}">
    <table>
        <thead>
        <tr>
            <th>Comment</th>
            <th>Created</th>
            <th>Should be done before</th>
            <th>Status</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todoList}" var="todo">
            <tr>
                <td><c:out value="${todo.getComment()}"/></td>
                <td><c:out value="${todo.getCreated()}"/></td>
                <td><c:out value="${todo.getShouldBeDoneBefore()}"/></td>
                <td><c:out value="${todo.getStatus()}"/></td>
                <td>
                    <form action="todo/edit" method="get">
                        <input type="hidden" name="todoId" value="<c:out value="${todo.getId()}"/>"><br>
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="todo/delete" method="post">
                        <input type="hidden" name="todoId" value="<c:out value="${todo.getId()}"/>"><br>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
