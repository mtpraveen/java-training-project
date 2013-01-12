<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="../WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<my:FieldCaption>Threshold</my:FieldCaption>
	${discount.threshold}
	<my:FieldCaption>Percent</my:FieldCaption>
	${discount.percent}
	<my:FieldCaption>Active</my:FieldCaption>
	<c:choose>
		<c:when test="${discount.active}">Yes</c:when>
		<c:otherwise>No</c:otherwise>
	</c:choose>
</x:page>
