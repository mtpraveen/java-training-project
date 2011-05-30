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

<a href="<c:url value="/home"></c:url>"> 
<spring:message code="label.main"></spring:message> </a>
&nbsp; <a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>
  
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
				<td><a href="pay/${tovar.id}"><spring:message code="label.pay" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>