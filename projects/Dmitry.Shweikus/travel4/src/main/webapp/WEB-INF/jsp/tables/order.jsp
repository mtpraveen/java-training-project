<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<legend><fmt:message key="orders"/></legend>
	<table class="table table-bordered table-hover table-condensed table-striped">
		<thead>
		<tr>
			<th><fmt:message key="date"/></th>
			<th><fmt:message key="tour"/></th>
			<th><fmt:message key="client"/></th>
			<th><fmt:message key="count"/></th>
			<th><fmt:message key="price"/></th>
			<th><fmt:message key="manager"/></th>
			<th><fmt:message key="finished"/></th>
			<th>&nbsp;</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${items}" var="order">
			<tr>
				<td>${order.date}</td>
				<td>${order.tourShedule.date} ${order.tourShedule.tour.name}</td>
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
					<c:if test="${loggeduser.admin || loggeduser == item.user }">
							<my:Exec action="edit" id="${order.id}" table="orders" />
							<my:Exec action="delete" id="${order.id}" table="orders" />
					</c:if>
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
</x:page>
