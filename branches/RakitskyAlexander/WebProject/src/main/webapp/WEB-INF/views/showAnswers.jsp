<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.titleShow"/></title>
</head>
<a href="<c:url value="/"></c:url>">
	<spring:message code="label.home"></spring:message> </a>
<a href="<c:url value="/addAnswers"></c:url>">
	<spring:message code="label.add"></spring:message> </a>
<a href='<c:url value="/logout"></c:url>'><spring:message code="label.logout"/></a>

<h2><spring:message code="label.showAnswer"/></h2>
<c:if test="${!empty testList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.language"/></th>
			<th><spring:message code="label.level"/></th>
			<th>&nbsp;</th> 
		</tr>
		<c:forEach items="${testList}" var="test">
			<tr>
				<td>${test.language}</td>
				<td>${test.level}</td> 		
				<td><a href="deleteMyAnswer/${test.id}"><spring:message code="label.delete" /></a></td> 
			</tr>
		</c:forEach>
	</table> 
</c:if> 
<c:if test="${empty testList }">
	<h1><spring:message code="label.emptyList"/></h1>
</c:if>


<body>

</body>
</html>