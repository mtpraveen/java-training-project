<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
	<title>BlogHost</title>
</head>
<body>

<h1>
	Автор: ${user}
</h1>
<hr>
 <!-- 
<jsp:useBean id="now" class="java.util.Date" />
<H1>Examples of Date & Time Formatting</H1>
<HR>
<H2>Default Time Zone</H2>
Default format : <fmt:formatDate value="${now}"/><BR>
A Date only in a Custom dd/MM/yyyy format :
 <fmt:formatDate value="${now}" type="DATE" pattern="dd/MM/yyyy"/><BR>
A Time only in MEDIUM format :
 <fmt:formatDate value="${now}" type="TIME" dateStyle="MEDIUM"/><BR>
A Date and Time in FULL format :
 <fmt:formatDate value="${now}" type="BOTH" dateStyle="FULL" timeStyle="FULL"/><BR>
<HR>
 -->
	<c:forEach var="topic" items="${topics}" varStatus="status">
				<b>Заголовок статьи : ${topic.caption}</b><br>
				Текст статьи:<br>
				${topic.text}<br>
				<hr width = "10%" align=left>
				Коментарии:<br>
				<c:forEach var="comment" items="${topic.comments}" varStatus="status2">
					<li>(<fmt:formatDate value="${comment.date}"/>--<fmt:formatDate value="${comment.date}" type="TIME" dateStyle="MEDIUM"/>) ${comment.id} ) ${comment.name} - ${comment.text} </li>
				</c:forEach>
				<br>
				<a href="/Blog/addcomment/${topic.id}">Добавить коммент</a>
				<hr>
	</c:forEach>
<br>
<a href="/Blog/">Назад</a>
</body>
</html>