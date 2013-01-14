<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<table border="1">
		<tr>
			<th>Date</th>
			<th>Tour</th>
			<th>Client</th>
			<th>Count</th>
			<th>Price</th>
			<th>Manager</th>
			<th>Finished</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${items}" var="item">
			<tr>
				<td>${item.order.date}</td>
				<td>${item.shedule.date} ${item.tour.name}</td>
				<td>${item.client.firstName} ${item.client.lastName}</td>
				<td>${item.order.count}</td>
				<td>${item.order.totalPrice}</td>
				<td>${item.user.name}</td>
				<td>
					<c:if test="${item.order.finished}">
						${item.order.finishedDate}					
					</c:if>
					<c:if test="${! item.order.finished}">
						-					
					</c:if>
				</td>
				<td>
					<my:Exec action="view" id="${item.order.id}" table="orders" /><br />
					<c:if test="${loggeduser.admin || loggeduser == user }">
						<my:Exec action="edit" id="${item.order.id}" table="orders" /><br />
						<my:Exec action="delete" id="${item.order.id}" table="orders" /><br />
					</c:if>
				</td>	
			</tr>
		</c:forEach>
	</table>
</x:page>
