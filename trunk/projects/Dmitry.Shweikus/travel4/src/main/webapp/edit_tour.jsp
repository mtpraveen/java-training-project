<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
<form action='<my:Save/>' method="post">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="tours" name="table"/>
	<my:FieldCaption>Name:</my:FieldCaption>
	<input type="text" name="name" value="${tour.name}">
	<my:FieldCaption>Transport:</my:FieldCaption>
	<select name="transportKind">
		<option value="BUS"   <c:if test="${tour.transportKind == 'BUS'}">selected</c:if>>BUS</option>
		<option value="AVIA"  <c:if test="${tour.transportKind == 'AVIA'}">selected</c:if>>AVIA</option>
		<option value="TRAIN" <c:if test="${tour.transportKind == 'TRAIN'}">selected</c:if>>TRAIN</option>
	</select>
	<my:FieldCaption>Kind:</my:FieldCaption>
	<select name="travelKind">
		<option value="REST"      <c:if test="${tour.travelKind == 'REST'}">selected</c:if>>REST</option>
		<option value="SHOPPING"  <c:if test="${tour.travelKind == 'SHOPPING'}">selected</c:if>>SHOPPING</option>
		<option value="TOUR"      <c:if test="${tour.travelKind == 'TOUR'}">selected</c:if>>TOUR</option>
	</select>
	<my:FieldCaption>Days:</my:FieldCaption>
	<input type="text" name="daysCount" value="${tour.daysCount}">
	<my:FieldCaption>Description:</my:FieldCaption>
	<input type="text" name="description" value="${tour.description}">
	<my:FieldCaption>Documents:</my:FieldCaption>
	<input type="text" name="requiredDocuments" value="${tour.requiredDocuments}">
	<br />
	<input type="submit">
</form>
</x:page>