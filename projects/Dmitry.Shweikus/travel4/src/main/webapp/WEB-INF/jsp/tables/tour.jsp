<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<legend><fmt:message key="tours"/></legend>
	<table class="table table-bordered table-hover table-condensed table-striped">
		<thead>
		<tr>
			<th><fmt:message key="name"/></th>
			<th><fmt:message key="kind"/></th>
			<th><fmt:message key="transport"/></th>
			<th><fmt:message key="days"/></th>
			<th><fmt:message key="description"/></th>
			<th><fmt:message key="documents"/></th>
			<th>&nbsp;</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${items}" var="tour">
			<tr>
				<td>${tour.name}</td>
				<td>${tour.travelKind}</td>
				<td>${tour.transportKind}</td>
				<td>${tour.daysCount}</td>
				<td>${tour.description}</td>
				<td>${tour.requiredDocuments}</td>
					<td>
						<my:Exec action="view" id="${tour.id}" table="tours" />
						<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
							<my:Exec action="edit" id="${tour.id}" table="tours" />
							<my:Exec action="delete" id="${tour.id}" table="tours" />
						</c:if></c:if>
					</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
		<br />
		<my:Create table="tours">create_new_tour</my:Create>
	</c:if></c:if>	
</x:page>
