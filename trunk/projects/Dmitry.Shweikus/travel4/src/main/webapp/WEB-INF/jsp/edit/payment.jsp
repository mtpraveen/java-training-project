<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<legend>Payment</legend>
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="payments" name="table"/>
	<c:if test="${isNew}"><input type="hidden" name="masterId" value="${masterId}"/></c:if>
	<my:FieldCaption>Date: *</my:FieldCaption>
	<my:DateInput name="date" date="${payment.date}" id="paymentdate" required="1"/>
	<my:FieldCaption>Amount: *</my:FieldCaption>
	<input type="text" name="amount" value="${payment.amount}" id="amount" class="required number">
	<br />
	<input type="submit">
</form>
</x:page>
