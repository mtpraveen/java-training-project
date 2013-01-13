<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<script>
  $(document).ready(function(){
		$("#confirmpassword").rules("add", {
			equalTo: "#password"
			});
	  });
</script>
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="users" name="table"/>
	
	<my:FieldCaption>Name: *</my:FieldCaption>
	<input type="text" name="name" value="${seluser.name}" class="required" minlength="3">
	
	<my:FieldCaption>Login: *</my:FieldCaption>
	<input type="text" name="login" value="${seluser.login}" class="required" minlength="3">
	
	<c:choose>
		<c:when test="${isNew}">
			<my:FieldCaption>Password: *</my:FieldCaption>
			<input type="password" name="password" id="password" class="required">	
		</c:when>
		<c:otherwise>
			<my:FieldCaption>Password:</my:FieldCaption>
			<input type="password" name="password" id="password">	
			<br/> (enter new password if you want change old password)
		</c:otherwise>
	</c:choose>
	
	<my:FieldCaption>Confirm password: </my:FieldCaption>
	<input type="password" name="confirmpassword" id="confirmpassword">

	<br />
	<input type="checkbox" name="admin" <c:if test="${seluser.admin}">checked</c:if> value="on"> Admin <br />
	<input type="submit">
</form>
</x:page>
