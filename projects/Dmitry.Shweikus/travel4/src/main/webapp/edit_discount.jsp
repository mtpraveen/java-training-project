<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
<form action='<my:Save/>' method="post">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="discounts" name="table"/>
	<my:FieldCaption>Threshold</my:FieldCaption>
	<input type="text" name="threshold" value="${discount.threshold}">
	<my:FieldCaption>Percent</my:FieldCaption>
	<input type="text" name="percent" value="${discount.percent}">
	<br/><input type="checkbox" name="active" value="on" <c:if test="${discount.active}">checked</c:if>> Active <br />
	<input type="submit">
</form>
</x:page>
