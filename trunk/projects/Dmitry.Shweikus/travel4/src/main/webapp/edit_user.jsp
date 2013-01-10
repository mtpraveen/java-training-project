<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
<form action='<my:Save/>' method="post">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="users" name="table"/>
	<my:FieldCaption>Name</my:FieldCaption>
	<input type="text" name="name" value="${seluser.name}">
	<my:FieldCaption>Login</my:FieldCaption>
	<input type="text" name="login" value="${seluser.login}">
	<my:FieldCaption>Password</my:FieldCaption>
	<input type="password" name="password"> 
	<c:if test="${!isNew}">	(enter new password if you want change old password)</c:if>
	<my:FieldCaption>Confirm password </my:FieldCaption>
	<input type="password" name="confirmpassword">
	<br />
	<input type="checkbox" name="admin" <c:if test="${seluser.admin}">checked</c:if> value="on"> Admin <br />
	<input type="submit">
</form>
</x:page>
