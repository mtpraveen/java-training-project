<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.finder" />
</title>
</head>
<a href="<c:url value="/home"></c:url>"> <spring:message code="label.main"></spring:message> </a> 
<a href="<c:url value="/logout" />"> <spring:message
		code="label.logout" /> </a>
		
<table>
	<tr>
		<td><spring:message code="label.firstname" />
		</td>
		
	</tr>
	<form:form method="post" action="setTovars" commandName="tovar">

		<tr>
			<td><form:input path="name" />
			</td>
			
			
		</tr>
		<tr>
			<td><input type="submit" value="Find" />
			</td>
		</tr>
	</form:form>
</table>
<body>
	<c:if test="${ empty tovarsName }">
		<spring:message code="label.emptyList" />
	</c:if>
	<c:if test="${! empty tovarsName}">
		<table>
			<tr>
				
						<td><spring:message code="label.firstname" />
				</td>
				<td><spring:message code="label.category" />
				</td>
				<td><spring:message code="label.price" />
				</td>
				<td><spring:message code="label.user" />
				</td>
			</tr>
			<c:forEach items="${tovarsName}" var="tovar">
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