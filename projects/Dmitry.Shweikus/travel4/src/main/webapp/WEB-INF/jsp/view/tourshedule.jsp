<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<legend>Tour schedule</legend>
	<my:FieldCaption>Tour:</my:FieldCaption>
	${tourShedule.tour.name} 
	<my:FieldCaption>Date:</my:FieldCaption>
	${tourShedule.date} 
	<my:FieldCaption>Price:</my:FieldCaption>
	${tourShedule.price} 
	<my:FieldCaption>Count:</my:FieldCaption>
	${tourShedule.count} 

	<c:if test="${!empty loggeduser}">
		<legend>Orders for this schedule</legend>
		<table class="table table-bordered table-hover table-condensed table-striped">
			<thead>
			<tr>
				<th>Date</th>
				<th>Client</th>
				<th>Count</th>
				<th>Price</th>
				<th>Manager</th>
				<th>Finished</th>
				<th>&nbsp;</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${orders}" var="order">
				<tr>
					<td>${order.date}</td>
					<td>${order.client.firstName} ${order.client.lastName}</td>
					<td>${order.count}</td>
					<td>${order.totalPrice}</td>
					<td>${order.user.name}</td>
					<td>
						<c:if test="${order.finished}">
							${order.finishedDate}					
						</c:if>
						<c:if test="${! order.finished}">
							-					
						</c:if>
					</td>
					<td>
						<my:Exec action="view" id="${order.id}" table="orders" />
						<c:if test="${loggeduser.admin || loggeduser == user}">
							<my:Exec action="edit" id="${order.id}" table="orders" />
							<my:Exec action="delete" id="${order.id}" table="orders" />
						</c:if>
					</td>	
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>		
</x:page>
