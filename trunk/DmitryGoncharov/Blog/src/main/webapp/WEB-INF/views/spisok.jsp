<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>

<h1>
	Список блогов:  
</h1>
<hr>
<table BORDER="1" CELLPADDING="3" CELLSPACING="1" align="center">
<tr>
	<th>#</th>
	<th>Название</th>
	<th>Автор</th>
	<th>Список статей</th>
</tr>
	<c:forEach var="blog" items="${blog}" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>
			<a href="blog/${blog.id}">${blog.name}</a>
			</td>
			<td>${blog.user.name}</td>
			<td>
			<c:forEach var="topic" items="${blog.topics}" varStatus="status2">
			
				${topic.caption} -- <a href="deletetopic/${topic.id}">Удалить статью</a><br>
			
			</c:forEach>
			<hr>
			<a href="addtopic/${blog.id}">Добавить статью</a>
			</td>
		</tr>
	</c:forEach>
</table>
<hr>
<a href="/Blog/">Назад</a>
</body>
</html>
