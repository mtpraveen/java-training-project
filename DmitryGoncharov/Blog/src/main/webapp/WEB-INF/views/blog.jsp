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
				<b>${topic.caption}</b><br>
				${topic.text}
				<hr>
	</c:forEach>
<br>
<a href="/Blog/">Назад</a>
</body>
</html>