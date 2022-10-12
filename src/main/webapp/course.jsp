<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Информация о курсе</h1>
Курс java разработчик с нуля. </br>
Время обучения понедельник, среда в 19:00.</br>
Врямя занятия 3 часа.</br>
Срок обучения 7.5 месяцев</br>
<table border="1" cellpadding="5">
    <caption><h2>Записавшиеся</h2></caption>
<tr>
    <th>Name</th>
    <th>Phone</th>
    <th>Email</th>
    <th>Action</th>
</tr>
    <c:forEach var="user" items="${list}">
        <tr>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.phone}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><form action="/course/edit/${user.name}" method="get">
                <input type="submit" value="Edit"/>
            </form>
                <form action="/course/delete/${user.name}" method="post">
                <input type="submit" value="Delete"/>
            </form></td>
        </tr>
    </c:forEach>
</table>
<h1>Записаться на курс</h1>
<c:if test="${error != null}">
<p><c:out value="${error}"/><p>
    </c:if>
<form action="/course" method="post">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr></table>
    <input type="submit" value="Add" />
</form>
</body>
</html>
