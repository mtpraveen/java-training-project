<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Добавление топика:  
</h1>
<hr>
 
	<form method="post" action="/Blog/createtopic/${blogid}">
		<table>
			<tr>
				<td>Логин:</td>
				<td><input name=login /></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input TYPE=PASSWORD name=pass /></td>
			</tr>
			<tr>
				<td>Заголовок топика:</td>
				<td><input name=topic_caption /></td>
			</tr>
			<tr>
				<td>Текст топика:</td>
				<td><input name=topic_text /></td>
			</tr>
		</table>
		<input type="submit" value="Создать топик" />
		</form>
	 
	<!-- 
	<form:form method="post" action="/Blog/createtopic/${blogid}">
		<table>
			<tr>
				<td>Логин:</td>
				<td><input name=login /></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input TYPE=PASSWORD name=pass /></td>
			</tr>

			<tr>
				<td><form:label path="caption">Название </form:label></td>
				<td><form:input path="caption" /><form:errors path="caption" cssClass="errors"/></td>
			</tr>
			<tr>
				<td><form:label path="text">Текст: </form:label></td>
				<td><form:input path="text" /><form:errors path="text" cssClass="errors"/></td>
			</tr>	
		</table>
		<input type="submit" value="Создать topic" />
	</form:form>
	 -->
	
<br>
<hr>
<a href="/Blog/spisok">Назад</a>
</body>
</html>