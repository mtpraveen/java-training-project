<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Style-Type" content="text/css; charset=utf8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title><spring:message code="label.title.home" /></title>
</head>
<body>
	<h1>
		<spring:message code="label.username" />
		${userNow.name}
	</h1>
	<table>
		<sec:authorize ifAllGranted="ROLE_USER">
			<tr>
				<td><a href='<c:url value="/addAnswers"/>'> <spring:message
							code="label.title.add" /> </a></td>
			</tr>
			<tr>
				<td><a href='<c:url value="/showAnswers"/>'> <spring:message
							code="label.showAnswer" /> </a></td>
			</tr>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_MANAGER">
			<tr>
				<td><a href='<c:url value="/manager"/>'> <spring:message
							code="label.titleManager" /> </a>
				</td>
			</tr>
			<tr>
				<td><a href='<c:url value="/allAnswers"/>'> <spring:message
							code="label.allAnswers" /> </a></td>
			</tr>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<tr>
				<td><a href='<c:url value="/admin"/>'> <spring:message
							code="label.title.admin" /> </a></td>
			</tr>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<tr>
				<td><a href="<c:url value="/logout" />"><spring:message
							code="label.logout" /> </a></td>
			</tr>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
			<tr>
				<td><a href="<c:url value="/login" />"><spring:message
							code="label.register" /> </a></td>
			</tr>
		</sec:authorize>
	</table>
</body>
</html>