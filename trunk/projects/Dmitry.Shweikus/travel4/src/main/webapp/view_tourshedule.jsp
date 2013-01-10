<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="../WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<my:FieldCaption>Tour:</my:FieldCaption>
	${tour.name} 
	<my:FieldCaption>Date:</my:FieldCaption>
	${tourShedule.date} 
	<my:FieldCaption>Price:</my:FieldCaption>
	${tourShedule.price} 
	<my:FieldCaption>Count:</my:FieldCaption>
	${tourShedule.count} 

	<c:if test="${!empty loggeduser}">
		<h3>Orders for this shedule</h3>
		<table border="1">
			<tr>
				<th>Date</th>
				<th>Client</th>
				<th>Count</th>
				<th>Price</th>
				<th>Manager</th>
				<th>Finished</th>
				<th>&nbsp;</th>
			</tr>
			<jsp:useBean id="orders" class="com.travel.web.beans.OrdersProviderBean"/>
			<c:set target="${orders}" property="masterId" value="${entityid}"/>
			<c:forEach items="${orders.recordsForTourShedule}" var="tableitem">
				<c:set var="order" value="${tableitem.entitys[0]}" />
				<c:set var="tour" value="${tableitem.entitys[1]}" />
				<c:set var="shedule" value="${tableitem.entitys[2]}" />
				<c:set var="user" value="${tableitem.entitys[3]}" />
				<c:set var="client" value="${tableitem.entitys[4]}" />
				<tr>
					<td>${order.date}</td>
					<td>${client.firstName} ${client.lastName}</td>
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
	</c:if>		
</x:page>
