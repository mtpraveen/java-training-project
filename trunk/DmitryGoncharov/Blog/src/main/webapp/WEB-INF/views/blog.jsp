<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>

<h1>
	Автор: ${user}
</h1>
<hr>
	<c:forEach var="topic" items="${topics}" varStatus="status">
				<b>Заголовок статьи : ${topic.caption}</b><br>
				Текст статьи:<br>
				${topic.text}<br>
				<hr width = "10%" align=left>
				Коментарии:<br>
				<c:forEach var="comment" items="${topic.comments}" varStatus="status2">
					<li>${comment.id} ) ${comment.name} - ${comment.text} -${comment.date}</li>
				</c:forEach>
				<br>
				<a href="/Blog/addcomment/${topic.id}">Добавить коммент</a>
				<hr>
	</c:forEach>
<br>
<a href="/Blog/">Назад</a>
</body>
</html>