<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != null}">
    <p><c:out value="${error}"/><p>
</c:if>
<form action="/course/edit/${user.name}" method="post">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><input type="text"  value="${user.name}" name="name" style="pointer-events: none; background-color: antiquewhite" /></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" value="${user.phone}" name="phone" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" value="${user.email}" name="email" /></td>
        </tr></table>
    <input type="submit" value="Edit" />
</form>
</body>
</html>
