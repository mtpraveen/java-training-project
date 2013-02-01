<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<legend>Tour</legend>
	<my:FieldCaption>Name:</my:FieldCaption>
	${tour.name}
	<my:FieldCaption>Kind:</my:FieldCaption>
	${tour.transportKind} <br /> ${tour.travelKind} <br /> ${tour.daysCount} day(s)
	<my:FieldCaption>Description:</my:FieldCaption>
	${tour.description}
	<my:FieldCaption>Documents:</my:FieldCaption>
	${tour.requiredDocuments}<br />
	
	<legend>Tours schedules</legend>
	<table class="table table-bordered table-hover table-condensed table-striped">
		<thead>
		<tr>
			<th>Date</th>
			<th>Price</th>
			<th>Count</th>
			<th>&nbsp;</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${shedules}" var="tourshedule">
			<tr>
				<td>${tourshedule.date}</td>
				<td>${tourshedule.price}</td>
				<td>${tourshedule.count}</td>
				<td>
					<my:Exec action="view" id="${tourshedule.id}" table="tourshedules" />
					<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
							<my:Exec action="edit" id="${tourshedule.id}" table="tourshedules" />
							<my:Exec action="delete" id="${tourshedule.id}" table="tourshedules" />
					</c:if></c:if>
				</td>	
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
	<my:Create table="tourshedules" masterId="${entityid}">create_new_tour_schedule</my:Create>
	</c:if></c:if>	
</x:page>
