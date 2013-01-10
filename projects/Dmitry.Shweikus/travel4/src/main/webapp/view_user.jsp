<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="../WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<my:FieldCaption>Name:</my:FieldCaption>
	${seluser.name}
	<my:FieldCaption>Login:</my:FieldCaption>
	${seluser.login}
	<my:FieldCaption>Password hash:</my:FieldCaption>
	${seluser.password}
	<my:FieldCaption>Admin:</my:FieldCaption>
	<c:if test="${seluser.admin}">Yes</c:if>
	<c:if test="${!seluser.admin}">No</c:if>
	<br />
	<br />
</x:page>
