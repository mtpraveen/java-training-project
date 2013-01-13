<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="tourshedules" name="table"/>
	<c:if test="${isNew}"><input type="hidden" name="masterId" value="${masterId}"/></c:if>
	<my:FieldCaption>Date: *</my:FieldCaption>
	<my:DateInput name="date" date="${tourShedule.date}" id="sheduleid" required="1"/>
	<my:FieldCaption>Price: *</my:FieldCaption>
	<input type="text" name="price" value="${tourShedule.price}" class="required number" min="0">
	<my:FieldCaption>Count: *</my:FieldCaption>
	<input type="text" name="count" value="${tourShedule.count}" class="required digits" min="1">
	<br />
	<br />
	<input type="submit">
</form>
</x:page>
