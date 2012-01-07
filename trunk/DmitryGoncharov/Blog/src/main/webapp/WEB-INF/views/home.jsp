<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Блоги:  
</h1>
<a href="/Blog/user">Список пользователей</a>
<br>
<a href="/Blog/showuser/1">Инфо о юзвере</a>
<br>
<a href="/Blog/new">Зарегистрировать пользователя</a>


<table BORDER="1" CELLPADDING="3" CELLSPACING="1" align="center">
<tr>
	<th>#</th>
	<th>Создатель блога</th>
	<th>Список статей</th>
</tr>
	<c:forEach var="blog" items="${blog}" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>
			<a href="blog/${blog.id}">${blog.author.name}</a>
			</td>
			<td>
			<c:forEach var="topic" items="${blog.topics}" varStatus="status2">
			
				${topic.caption}<br>
			
			</c:forEach>
			</td>
		</tr>
	</c:forEach>

</table>
<a href="/Blog/test">Тест</a>
<hr>
<P >  The time on the server is ${serverTime}. </P>
</body>
</html>
