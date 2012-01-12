<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	БлогХост:  
</h1>
<hr>
<a href="/Blog/spisok">Список блогов</a>
<br>
<a href="/Blog/user">Список пользователей</a>
<br>
<a href="/Blog/reguser?error=0">Зарегистрировать пользователя</a>
<br>
<a href="/Blog/login?error=0">Переименовать блог</a>
<br>
<a href="/Blog/deleteblog?error=0">Удалить блог</a>
<hr>
<P >  The time on the server is ${serverTime}. </P>
</body>
</html>