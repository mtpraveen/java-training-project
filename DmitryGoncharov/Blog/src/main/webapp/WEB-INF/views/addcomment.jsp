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
	Добавление комментария:  
</h1>
<hr>
	<form:form method="post" action="/Blog/createcomment/${topicid}">
		<table>
			<tr>
				<td><form:label path="name">Имя: </form:label></td>
				<td><form:input path="name" /><form:errors path="name" cssClass="errors"/></td>
			</tr>
			<tr>
				<td><form:label path="text">Текст: </form:label></td>
				<td><form:textarea cols="40" rows="3" path="text" /><form:errors path="text" cssClass="errors"/></td>
			</tr>	
		</table>
		<input type="submit" value="Создать комментарий" />
	</form:form>
<br>
<hr>
<a href="/Blog/">Назад</a>
</body>
</html>