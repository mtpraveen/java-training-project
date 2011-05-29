<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title.admin" />
</title>
</head>
<body>

	<a href="<c:url value="/"></c:url>"> <spring:message
			code="label.home"></spring:message> </a>
	<a href="<c:url value="/logout" />"> <spring:message
			code="label.logout" /> </a>

	<h2>
		<spring:message code="label.title.admin" />
	</h2>
	<form:form method="post" action="addQuestion" commandName="question">

		<table>
			<tr>
				<td><form:label path="language">
						<spring:message code="label.language" />
					</form:label>
				</td>
				<td><form:input path="language" />
				</td>
			</tr>
		</table>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.add"/>" />
			</td>
		</tr>
	</form:form>
	<h1></h1>
	<c:if test="${!empty questionList}">
		<table class="data">
			<tr>
				<th><spring:message code="label.language" />
				</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${questionList}" var="question">
				<tr>
					<td>${question.language}</td>
					<td><a href="deleteQuestion/${question.id}"><spring:message
								code="label.delete" />
					</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</html>
