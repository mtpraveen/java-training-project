<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<legend>User</legend>
	<my:FieldCaption>Name:</my:FieldCaption>
	${seluser.name}
	<my:FieldCaption>Login:</my:FieldCaption>
	${seluser.login}
	<my:FieldCaption>Admin:</my:FieldCaption>
	<c:if test="${seluser.admin}">Yes</c:if>
	<c:if test="${!seluser.admin}">No</c:if>
	<br />
	<br />
</x:page>
