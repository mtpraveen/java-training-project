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
			<th><fmt:message key="date"/></th>
			<th><fmt:message key="tour"/></th>
			<th><fmt:message key="client"/></th>
			<th><fmt:message key="count"/></th>
			<th><fmt:message key="price"/></th>
			<th><fmt:message key="manager"/></th>
			<th><fmt:message key="finished"/></th>
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
					<ul>
						<li>
							<my:Exec action="view" id="${item.order.id}" table="orders" />
						</li>	
						<c:if test="${loggeduser.admin || loggeduser == item.user }">
							<li>
								<my:Exec action="edit" id="${item.order.id}" table="orders" />
							</li>	
							<li>
								<my:Exec action="delete" id="${item.order.id}" table="orders" />
							</li>	
						</c:if>
					</ul>
				</td>	
			</tr>
		</c:forEach>
	</table>
</x:page>
