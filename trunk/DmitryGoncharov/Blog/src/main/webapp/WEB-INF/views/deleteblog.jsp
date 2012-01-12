<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Login:  
</h1>
	<form method="post" action="killblog">
		<table>
			<tr>
				<td>Логин:</td>
				<td><input name=login /></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input TYPE=PASSWORD name=pass /></td>
			</tr>
		</table>
		<input type="submit" value="Удалить блог" />
	</form>
<hr>
<a href="/Blog/">Назад</a>
</body>
</html>