<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Список пользователей:  
</h1>

<hr>

<br>
<table>
<c:forEach var="user" items="${users}">
	<tr>
		<td><li>${user.login}/${user.name} [${user.id}]</li></td>
		<td><a href="showuser/${user.id}">Информация о пользователе</a></td>
	</tr>
</c:forEach>
</table>
<br>
<a href="/Blog/">Назад</a>
</body>
</html>