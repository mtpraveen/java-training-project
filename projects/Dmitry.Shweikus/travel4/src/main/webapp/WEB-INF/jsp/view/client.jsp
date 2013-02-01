<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<legend>Client</legend>
	<my:FieldCaption>Name:</my:FieldCaption>
	${client.firstName} ${client.lastName}
	<my:FieldCaption>Documents:</my:FieldCaption>
	<c:if test="${client.document1 != ''}">${client.document1} <br /></c:if>
	<c:if test="${client.document2 != ''}">${client.document2} <br /></c:if>
	<c:if test="${client.document3 != ''}">${client.document3} <br /></c:if>
	<c:if test="${client.document4 != ''}">${client.document4} </c:if>
	<my:FieldCaption>Description:</my:FieldCaption>
	${client.description}<br />
	<legend>Client orders</legend>
	<table class="table table-bordered table-hover table-condensed table-striped">
		<thead>
		<tr>
			<th>Date</th>
			<th>Tour</th>
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
				<td>${order.tourShedule.date} ${order.tourShedule.tour.name}</td>
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
	<my:Create table="orders" masterId="${entityid}">create_new_order</my:Create>
</x:page>
