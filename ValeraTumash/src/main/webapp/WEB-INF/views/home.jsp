<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<table>
		<tr>
			<th>#</th>
			<th>Title</th>
			<th>Quantity</th>
		</tr>
		<c:forEach var="item" items="${items}" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td><a href="/item/show?id=${item.id}">${item.title}</a></td>
				<td>${item.quantity}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
