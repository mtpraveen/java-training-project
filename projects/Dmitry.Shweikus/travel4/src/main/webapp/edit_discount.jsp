<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="discounts" name="table"/>
	<my:FieldCaption>Threshold: *</my:FieldCaption>
	<input type="text" name="threshold" value="${discount.threshold}" class="required number" min="0">
	<my:FieldCaption>Percent: *</my:FieldCaption>
	<input type="text" name="percent" value="${discount.percent}" class="required digits" min="0">
	<br/><input type="checkbox" name="active" value="on" <c:if test="${discount.active}">checked</c:if>> Active <br />
	<input type="submit">
</form>
</x:page>
