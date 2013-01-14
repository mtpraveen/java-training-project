<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="../WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<my:FieldCaption>Name:</my:FieldCaption>
	${client.firstName} ${client.lastName}
	<my:FieldCaption>Documents:</my:FieldCaption>
	<c:if test="${client.document1 != ''}">${client.document1} <br /></c:if>
	<c:if test="${client.document2 != ''}">${client.document2} <br /></c:if>
	<c:if test="${client.document3 != ''}">${client.document3} <br /></c:if>
	<c:if test="${client.document4 != ''}">${client.document4} </c:if>
	<my:FieldCaption>Description:</my:FieldCaption>
	${client.description}<br />
	<h3>Client orders</h3>
	<table border="1">
		<tr>
			<th>Date</th>
			<th>Tour</th>
			<th>Count</th>
			<th>Price</th>
			<th>Manager</th>
			<th>Finished</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${orders}" var="tableitem">
			<c:set var="order" value="${tableitem.order}" />
			<c:set var="tour" value="${tableitem.tour}" />
			<c:set var="shedule" value="${tableitem.shedule}" />
			<c:set var="user" value="${tableitem.user}" />
			<tr>
				<td>${order.date}</td>
				<td>${shedule.date} ${tour.name}</td>
				<td>${order.count}</td>
				<td>${order.totalPrice}</td>
				<td>${user.name}</td>
				<td>
					<c:if test="${order.finished}">
						${order.finishedDate}					
					</c:if>
					<c:if test="${! order.finished}">
						-					
					</c:if>
				</td>
				<td>
					<my:Exec action="view" id="${order.id}" table="orders" /><br />
					<c:if test="${loggeduser.admin || loggeduser == user}">
						<my:Exec action="edit" id="${order.id}" table="orders" /><br />
						<my:Exec action="delete" id="${order.id}" table="orders" /><br />
					</c:if>
				</td>	
			</tr>
		</c:forEach>
	</table>
	<my:Create table="orders" masterId="${entityid}">Create new order</my:Create>
</x:page>
