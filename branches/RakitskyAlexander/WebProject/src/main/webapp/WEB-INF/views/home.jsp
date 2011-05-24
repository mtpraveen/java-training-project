<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title.home"/></title>
</head>
<body>
<h5><spring:message code="label.username"/> ${userNow.name}</h5>
<table>
	<tr><td><a href='<c:url value="/addAnswers"/>'> <spring:message code="label.title.add" /> </a></td></tr>
	<tr><td><a href='<c:url value="/showAnswers"/>'> <spring:message code="label.showAnswer" /> </a></td></tr>
	<tr><td><a href='<c:url value="/manager"/>'> <spring:message code="label.titleManager" /> </a>  </td></tr>
	<tr><td><a href='<c:url value="/admin"/>'> <spring:message code="label.title.admin" /> </a></td></tr>
	<tr><td><a href="<c:url value="/logout" />"><spring:message code="label.logout" /></a></td></tr>
	<tr><td><a href="<c:url value="/login" />"><spring:message code="label.register" /></a></td></tr>
</table>
</body>
</html>