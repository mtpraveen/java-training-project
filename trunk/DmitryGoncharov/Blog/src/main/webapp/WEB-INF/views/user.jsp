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
<table BORDER="1" CELLPADDING="3" CELLSPACING="1" align="center">
<tr>
	<th>#</th>
	<th>Логин</th>
	<th>Имя</th>
</tr>
<c:forEach var="user" items="${users}">
	<tr>	<td>	${user.id} </td>
				<td>${user.login}</td>
				<td><a href="showuser/${user.id}">${user.name}</a></td>
	</tr>
</c:forEach>
</table>
<br>
<hr>
<a href="/Blog/">Назад</a>
</body>
</html>