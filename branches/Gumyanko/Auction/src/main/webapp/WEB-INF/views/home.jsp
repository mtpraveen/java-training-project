<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title.home" />
</title>
</head>
<body>
	<h5>
		<spring:message code="label.username" />
		${userNow.name}
	</h5>
	<table>
		<sec:authorize ifAllGranted="ROLE_USER">
		<tr>
			<td><a href='<c:url value="/index"/>'> <spring:message
						code="label.title" /> </a>
			</td>
		</tr>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_USER">
		<tr>
			<td><a href='<c:url value="/find"/>'> <spring:message
						code="label.finder" /> </a>
			</td>
		</tr>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_MANAGER">
		<tr>
			<td><a href='<c:url value="/manager"/>'> <spring:message
						code="label.titleManager" /> </a></td>
		</tr>
		</sec:authorize>
		
		
		
		
		<sec:authorize ifAllGranted="ROLE_ADMIN">
		<tr>
			<td><a href='<c:url value="/auc"/>'> <spring:message
						code="label.title.admin" /> </a>
			</td>
		</tr>
		</sec:authorize>
		
		
		
		
		<sec:authorize ifAllGranted="ROLE_MANAGER">
		<tr>
			<td><a href='<c:url value="/allAnswers"/>'> <spring:message
						code="label.allAnswers" /> </a>
			</td>
		</tr>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
		<tr>
			<td><a href="<c:url value="/logout" />"><spring:message
						code="label.logout" />
			</a>
			</td>
		</tr>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
		<tr>
			<td><a href="<c:url value="/login" />"><spring:message
						code="label.register" />
			</a>
			</td>
		</tr>
		</sec:authorize>
	</table>
	
	
	
	
	
	<h2><spring:message code="label.title" /></h2>



<h3><spring:message code="label.lots" /></h3>
<c:if test="${!empty tovarList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.firstname" /></th>
			<th><spring:message code="label.category" /></th>
			<th><spring:message code="label.price" /></th>
			<th><spring:message code="label.user" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${tovarList}" var="tovar">
			<tr>
				<td>${tovar.name}</td>		
				<td>${tovar.category}</td> 	
				<td>${tovar.price}</td>		
				<td>${tovar.user}</td>				
			</tr>
		</c:forEach>
	</table>
</c:if>
	
	
	
	
	
	
</body>
</html>