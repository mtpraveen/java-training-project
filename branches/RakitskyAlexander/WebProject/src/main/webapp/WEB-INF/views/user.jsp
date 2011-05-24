<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.allAnswer" /></title>
</head>


<a href="<c:url value="/logout"></c:url>">
<spring:message code="label.logout"></spring:message> </a>

<h2> <spring:message code="label.allAnswer"/></h2>

<form:form method="post" action="addAnswer" commandName="answer">
	<table>
		<tr>
			<td><form:label path="user">
				<spring:message code="label.user" />
			</form:label></td>
			<td><form:input path="user" /></td>
		</tr>
		<tr>
			<td><form:label path="language">
				<spring:message code="label.language" />
			</form:label></td>
			<td><form:input path="language" /></td>
		</tr>
		<tr>
			<td><form:label path="level">
				<spring:message code="label.level" />
			</form:label></td>
			<td><form:select path="level">
			<form:option value=" "></form:option>
			<form:option value="Novice"></form:option>
			<form:option value="Intermediate"></form:option>
			<form:option value="Advanced"></form:option>
			<form:option value="Expert"></form:option>	
			</form:select>
			</td>
		</tr>
	</table>
	<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.add"/>" /></td>
		</tr>
</form:form>

<h3><spring:message code="label.allAnswer" /></h3>
 <c:if test="${!empty listAnswer}">
	<table class="data">
		<tr>
			<th><spring:message code="label.user"/></th>
			<th><spring:message code="label.language"/></th>
			<th><spring:message code="label.level" /></th>
		</tr>
		<c:forEach items="${listAnswer}" var="answer">
			<tr>
				<td>${answer.user}</td>
				<td>${answer.language}</td>
				<td>${answer.level}</td>
			</tr>
		</c:forEach>
	</table>
 </c:if>
 <c:if test="${empty listAnswer}">
 <spring:message> empty list</spring:message>
 </c:if>


</html>
