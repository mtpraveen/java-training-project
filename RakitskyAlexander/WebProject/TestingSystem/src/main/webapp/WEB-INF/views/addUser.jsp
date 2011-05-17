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


<a href="<c:url value="/logout"></c:url>">
<spring:message code="label.logout"></spring:message> </a>

<h2> <spring:message code="label.testingShow"/></h2>
<h1> ${userNow.name}</h1>
<form:form method="post" action="addUser" commandName="user">

	<table>
		<tr>
			<td><form:label path="name">
				<spring:message code="label.tesitngUser" />
			</form:label></td>
			<td><form:input path="name" /></td>
		</tr>
	</table>
	<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.addTest"/>" /></td>
		</tr>
</form:form>

<h3><spring:message code="label.tests" /></h3>
 <c:if test="${!empty testingList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.tesitngUser"/></th>
			<th><spring:message code="label.testingLevel"/></th>
			<th><spring:message code="label.tesitngLanguage" /></th>
			<th>&nbsp;</th> 
		</tr>
		<c:forEach items="${testingList}" var="test">
			<tr>
				<td>${test.user}</td>
				<td>${test.level}</td>
				<td>${test.language}</td>
			 	<td><a href="deleteTest/${test.id}"><spring:message code="label.deleteTest" /></a></td> 
			</tr>
		</c:forEach>
	</table>
 </c:if>
 <c:if test="${empty testingList}">
 <spring:message> empty list</spring:message>
 </c:if>


</html>
