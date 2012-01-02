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
<c:forEach var="order" items="${users}">
	<li>${order.login}/${order.name} [${order.id}]</li>
</c:forEach>
<br>
<a href="/Blog/">Назад</a>
</body>
</html>