<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.titleManager" /></title>
</head>
<table>
<tr>
	<td><spring:message code="label.language"/></td>
	<td><spring:message code="label.level"/></td>
</tr>
<form:form method="post" action="setAnswers" commandName="testing">

	 <tr>
	 	<td><form:input path="language" /></td>
	 	<td><form:select path="level">
	 		<form:option value="Novice"></form:option>
	 		<form:option value="Intermediate"></form:option>
	 		<form:option value="Advanced"></form:option>
	 		<form:option value="Expert"></form:option>
	 		</form:select>
	 	</td>
	 </tr>
	 <tr>	<td><input type="submit" value="Ok"/></td>
	 </tr>
</form:form>
</table>
<body>
<c:if test="${ empty answersLang }">
<spring:message code="label.emptyList" />
</c:if>
<c:if test="${! empty answersLang}">
	<table>
		<tr>
			<td><spring:message code="answer.user"/></td>
			<td><spring:message code="answer.language"/></td>
			<td><spring:message code="answer.level"/></td>
		</tr>
		<c:forEach items="${answersLang}" var="answer">
			<tr>
				<td>${answer.user}</td>
				<td>${answer.language}</td>
				<td>${answer.level}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>