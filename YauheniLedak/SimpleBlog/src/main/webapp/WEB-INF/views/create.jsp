<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>

	<form:form method="post" commandName="topic">
		<fieldset>
			<legend>Новая тема</legend>
			<div class="editor-label">
				<td><form:label path="name">Название темы</form:label></td>
				<td><form:input path="name" /></td>

			</div>

			<div class="editor-label">
				<td><form:label path="text">Содержание</form:label></td>
			</div>

			<div class="textarea">
				<form:textarea path="text" />

			</div>

			<p>
				<input type="submit" value="Опубликовать" />
			</p>
			
		</fieldset>
	</form:form>

	<div>
		<p>
			<a href="/SimpleBlog/">Вернуться к списку тем</a>
		</p>
	</div>


</body>
</html>
