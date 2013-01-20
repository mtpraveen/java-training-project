<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<table class="mytable">
		<tr>
			<th><fmt:message key="name"/></th>
			<th><fmt:message key="kind"/></th>
			<th><fmt:message key="description"/></th>
			<th><fmt:message key="documents"/></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${items}" var="tour">
			<tr>
				<td>${tour.name}</td>
				<td>${tour.transportKind} <br /> ${tour.travelKind} <br /> ${tour.daysCount} day(s)</td>
				<td>${tour.description}</td>
				<td>${tour.requiredDocuments}</td>
					<td>
						<ul>
							<li>
								<my:Exec action="view" id="${tour.id}" table="tours" />
							</li>	
								<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
								<li>
									<my:Exec action="edit" id="${tour.id}" table="tours" />
								</li>	
								<li>
									<my:Exec action="delete" id="${tour.id}" table="tours" />
								</li>	
								</c:if></c:if>
						</ul>
					</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${!empty loggeduser}"><c:if test="${loggeduser.admin}">
		<br />
		<my:Create table="tours">create_new_tour</my:Create>
	</c:if></c:if>	
</x:page>
