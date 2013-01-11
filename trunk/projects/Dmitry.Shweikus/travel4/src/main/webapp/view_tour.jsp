<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="../WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<my:FieldCaption>Name:</my:FieldCaption>
	${tour.name}
	<my:FieldCaption>Kind:</my:FieldCaption>
	${tour.transportKind} <br /> ${tour.travelKind} <br /> ${tour.daysCount} day(s)
	<my:FieldCaption>Description:</my:FieldCaption>
	${tour.description}
	<my:FieldCaption>Documents:</my:FieldCaption>
	${tour.requiredDocuments}<br />
	
	<jsp:useBean id="toursheduledao" class="com.travel.dao.TourSheduleDao" />
	<jsp:useBean id="dao" class="com.travel.dao.TourDao" />
	<jsp:useBean id="provider" class="com.travel.web.beans.DataProviderBean" />
	<c:set target="${provider}" property="dao" value="${toursheduledao}"/>
	<c:set target="${provider}" property="masterDao" value="${dao}"/>
	<c:set target="${provider}" property="masterId" value="${entityid}"/>
	<h3>Tours schedule</h3>
	<table border="1">
		<tr>
			<th>Date</th>
			<th>Price</th>
			<th>Count</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${provider.detailRecords}" var="detailRecord">
			<c:set var="tourshedule" value="${detailRecord.entitys[0]}" />
			<tr>
				<td>${tourshedule.date}</td>
				<td>${tourshedule.price}</td>
				<td>${tourshedule.count}</td>
				<td>
					<my:Exec action="view" id="${tourshedule.id}" table="tourshedules" /><br />
					<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
						<my:Exec action="edit" id="${tourshedule.id}" table="tourshedules" /><br />
						<my:Exec action="delete" id="${tourshedule.id}" table="tourshedules" /><br />
					</c:if></c:if>
				</td>	
				
			</tr>
		</c:forEach>
	</table>
	<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
	<my:Create table="tourshedules" masterId="${entityid}">Create new tour schedule</my:Create>
	</c:if></c:if>	
</x:page>