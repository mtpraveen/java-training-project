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
<body>
<a href="<c:url value="/home"></c:url>"> <spring:message
		code="label.main"></spring:message> </a> 
&nbsp; <a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>

<h2><spring:message code="label.add" /></h2>
<form:form method="post" action="add" commandName="tovar">

	<table>
		<tr>
			<td><form:label path="name">
				<spring:message code="label.firstname" />
			</form:label></td>
			<td><form:input path="name" /></td> 
			 
		</tr>
	
		<tr>
			<td><form:label path="category">
				<spring:message code="label.category" />
			</form:label></td>
			<td><form:input path="category" /></td> 
		</tr>
		
		<tr>
			<td><form:label path="price">
				<spring:message code="label.price" />
			</form:label></td>
			<td><form:input path="price" /></td> 
			 
		</tr>

		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.addcontact"/>" /></td>
		</tr>
	</table>
</form:form>

<h3><spring:message code="label.lots" /></h3>
<c:if test="${!empty tovarList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.firstname" /></th>
			<th><spring:message code="label.category" /></th>
			<th><spring:message code="label.price" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${tovarList}" var="tovar">
			<tr>
				<td>${tovar.name}</td>		
				<td>${tovar.category}</td> 	
				<td>${tovar.price}</td> 
				<td><a href="delete/${tovar.id}"><spring:message code="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>