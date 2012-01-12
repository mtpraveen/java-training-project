<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Регистрация пользователя:  
</h1>
<hr>
${error}
	<form:form method="post" action="create">
		<table>
			<tr>
				<td><form:label path="login">Логин: </form:label></td>
				<td><form:input path="login" /><form:errors path="login" cssClass="errors"/></td>
			</tr>
			<tr>
				<td><form:label path="pass">Пароль: </form:label></td>
				<td><form:input type="password" path="pass" /><form:errors path="pass" cssClass="errors"/></td>
			</tr>	
			<tr>
				<td><form:label path="name">Имя: </form:label></td>
				<td><form:input path="name" /><form:errors path="name" cssClass="errors"/></td>
			</tr>
		</table>
		<input type="submit" value="Создать" />
	</form:form>
<br>
<hr>
<a href="/Blog/">Назад</a>
</body>
</html>