<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Регистрация пользователя:  
</h1>

<hr>
	<form:form method="post" action="create">
		<table>
			<tr>
				<td><form:label path="login">User login</form:label></td>
				<td><form:input path="login" /><form:errors path="login" cssClass="errors"/></td>
			</tr>
			<tr>
				<td><form:label path="pass">User pass</form:label></td>
				<td><form:input path="pass" /><form:errors path="name" cssClass="errors"/></td>
			</tr>	
			<tr>
				<td><form:label path="name">User name</form:label></td>
				<td><form:input path="name" /><form:errors path="name" cssClass="errors"/></td>
			</tr>		
		</table>
		<input type="submit" value="Create" />
	</form:form>
<br>
<a href="/Blog/">Назад</a>
</body>
</html>