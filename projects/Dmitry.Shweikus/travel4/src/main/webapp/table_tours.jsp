<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Kind</th>
			<th>Description</th>
			<th>Documents</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${items}" var="tour">
			<tr>
				<td>${tour.name}</td>
				<td>${tour.transportKind} <br /> ${tour.travelKind} <br /> ${tour.daysCount} day(s)</td>
				<td>${tour.description}</td>
				<td>${tour.requiredDocuments}</td>
					<td>
						<my:Exec action="view" id="${tour.id}" table="tours" /><br />
						<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
							<my:Exec action="edit" id="${tour.id}" table="tours" /><br />
							<my:Exec action="delete" id="${tour.id}" table="tours" /><br />
						</c:if></c:if>		
					</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
		<br />
		<my:Create table="tours">Create new tour</my:Create>
	</c:if></c:if>	
</x:page>
