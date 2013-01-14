<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="clients" name="table"/>
	<my:FieldCaption>First name: *</my:FieldCaption>
	<input type="text" name="firstName" value="${client.firstName}" class="required" minlength="3"> 
	<my:FieldCaption>Last name: *</my:FieldCaption>
	<input type="text" name="lastName" value="${client.lastName}" class="required" minlength="3"> 
	<my:FieldCaption>Documents 1: *</my:FieldCaption>
	<input type="text" name="document1" value="${client.document1}" class="required" minlength="3"> 
	<my:FieldCaption>Documents 2:</my:FieldCaption>
	<input type="text" name="document2" value="${client.document2}"> 
	<my:FieldCaption>Documents 3:</my:FieldCaption>
	<input type="text" name="document3" value="${client.document3}"> 
	<my:FieldCaption>Documents 4:</my:FieldCaption>
	<input type="text" name="document4" value="${client.document4}"> 
	<my:FieldCaption>Description:</my:FieldCaption>
	<input type="text" name="description" value="${client.description}">
	<br> 
	<input type="submit">
</form>
</x:page>
