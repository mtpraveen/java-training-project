<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" contentType="text/html; charset=utf16"  %>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<%-- <table border="1">
		<tr>
			<td>
				<b>Title</b>
			</td>
			<td>
				<b>Content</b>
			</td>
		</tr>
		<c:forEach var="notification" items="${notifications}"
			varStatus="status">
			<tr>
				<td>${notification.value.title}</td>
				<td>${notification.value.content}</td>
			</tr>
		</c:forEach>
	</table> --%>
</body>
</html>