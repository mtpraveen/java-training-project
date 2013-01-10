<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
<form action='<my:Save/>' method="post">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="orders" name="table"/>
	<c:if test="${isNew}"><input type="hidden" name="masterId" value="${masterId}"/></c:if>
	<my:FieldCaption>Date:</my:FieldCaption>
	<my:DateInput name="date" date="${order.order.date}"/>
	<my:FieldCaption>Tour:</my:FieldCaption>

	<c:if test="${!isNew}">${order.shedule.date} ${order.tour.name}</c:if>
	<c:if test="${isNew}">
		<select name="sheduleId">
			<jsp:useBean id="tourProvider" class="com.travel.web.beans.TourShedulesProviderBean" />
			<c:forEach items="${tourProvider.tours}" var="tourProviderItem">
				<c:set value="${tourProviderItem.entitys[0]}" var="tour"/>
				<c:set value="${tourProviderItem.entitys[1]}" var="shedule"/>
				<option value="${shedule.id}">${shedule.date} ${tour.name}</option>
			</c:forEach>
		</select>
	</c:if>

	<my:FieldCaption>Client:</my:FieldCaption>
	${order.client.firstName} ${order.client.lastName}
	<my:FieldCaption>Count:</my:FieldCaption>
	<input type="text" name="count" value="${order.order.count}">
	<my:FieldCaption>Price:</my:FieldCaption>
	<input type="text" name="totalPrice" value="${order.order.totalPrice}">
	<my:FieldCaption>Manager:</my:FieldCaption>
	${order.user.name}
	<my:FieldCaption>Description:</my:FieldCaption>
	<input type="text" name="description" value="${order.order.description}">
	<br/>
	<br/>
	<input type="checkbox" name="finished" value="ON" <c:if test="${order.order.finished}">checked</c:if>/> Finished
	<my:FieldCaption>Finished at:</my:FieldCaption>
	<my:DateInput name="finishedDate" date="${order.order.finishedDate}"/>
	<br />
	 <br />
	<input type="submit">
</form>
</x:page>
