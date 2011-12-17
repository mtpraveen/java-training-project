<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	List blogs:  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<table>
<tr>
	<th>#</th>
	<th>Name</th>
	<th></th>
</tr>

	<c:forEach var="blog" items="${blog}" varStatus="status">
		<tr>
			<td>${status.index}</td>
			<td>${blog.author.name}</td>
			<c:forEach var="topic" items="${blog.topics}" varStatus="status">
				<td>${topc.caption}</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>
</body>
</html>
