<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title" /></title>
</head>

<a href="<c:url value="/showAnswers"></c:url>">
<spring:message code="label.show"></spring:message> </a>
<a href="<c:url value="/logout"></c:url>">
<spring:message code="label.logout"></spring:message> </a>

<h5><spring:message code="label.username"/> ${userNow.name}</h5>
<h2><spring:message code="label.add"/></h2>

<table class="data">
<tr>
			<th><spring:message code="label.tesitngLanguage" /></th>
			<th><spring:message code="label.testingLevel"/></th>
			<th>&nbsp;</th> 
</tr>
<c:forEach items="${questions}" var="question">
<c:set var="test1" value="${test}"></c:set>
	<form:form method="post" action="addtest" commandName="testing">
<tr>
			<form:hidden path="language" value="${question.language}"/>
			<td>${question.language}</td>
			<td><form:select path="level">
			<form:option value=" "></form:option>
			<form:option value="Novice"></form:option>
			<form:option value="Intermediate"></form:option>
			<form:option value="Advanced"></form:option>
			<form:option value="Expert"></form:option>	
			</form:select>
			</td>
			
			<td colspan="1"><input type="submit"
				value="<spring:message code="label.addTest"/>" /></td>
		
</tr>
</form:form>
</c:forEach>
<form:form method="post" action="addtest" commandName="testing">
<tr>
			<td><form:input onclick="this.value='' " type="text" path="language" value="Ваш язык"/></td>
			<td><form:select path="level">
			<form:option value=" "></form:option>
			<form:option value="Novice"></form:option>
			<form:option value="Intermediate"></form:option>
			<form:option value="Advanced"></form:option>
			<form:option value="Expert"></form:option>	
			</form:select>
			</td>
			<td colspan="1"><input type="submit"
				value="<spring:message code="label.addTest"/>" /></td>
</tr>
</form:form>
</table>





</html>
