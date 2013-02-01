<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<legend>Discount</legend>
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="discounts" name="table"/>
	<my:FieldCaption>Threshold: *</my:FieldCaption>
	<input type="text" name="threshold" id="threshold" value="${discount.threshold}" class="required number" min="0">
	<my:FieldCaption>Percent: *</my:FieldCaption>
	<input type="number" name="percent" id="percent" value="${discount.percent}" class="required digits" min="0" max="100" >
	<br/>
	<label class="checkbox">
		<input type="checkbox" name="active" value="on" <c:if test="${discount.active}">checked</c:if>> Active <br />
	</label>
	<input type="submit">
</form>
</x:page>
