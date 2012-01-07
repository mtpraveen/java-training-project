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
			<a href="blog/${blog.id}">${blog.name}</a>
			</td>
		</tr>
	</c:forEach>

</table>
<a href="/Blog/test">Тест</a>
<hr>
<P >  The time on the server is ${serverTime}. </P>
</body>
</html>
