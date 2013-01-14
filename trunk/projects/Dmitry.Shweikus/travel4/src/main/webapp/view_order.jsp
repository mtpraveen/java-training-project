<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="../WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<x:page>
<my:FieldCaption>Date:</my:FieldCaption>
${order.order.date}
<my:FieldCaption>Tour:</my:FieldCaption>
${order.shedule.date} ${order.tour.name}
<my:FieldCaption>Client:</my:FieldCaption>
${order.client.firstName} ${order.client.lastName}
<my:FieldCaption>Count:</my:FieldCaption>
${order.order.count}
<my:FieldCaption>Price:</my:FieldCaption>
${order.order.totalPrice}
<my:FieldCaption>Manager:</my:FieldCaption>
${order.user.name}
<my:FieldCaption>Description:</my:FieldCaption>
${order.order.description}
<my:FieldCaption>Finished:</my:FieldCaption>
<c:if test="${order.order.finished}">
${order.order.finishedDate}					
</c:if>
<c:if test="${! order.order.finished}">
not					
</c:if>
<br />
<br />
<h3>Payments</h3>
<table border="1">
	<tr>
		<th>Date</th>
		<th>Amount</th>
		<c:if test="${loggeduser.admin || loggeduser == order.user }">
			<th>&nbsp;</th>
		</c:if>	
	</tr>
	<c:forEach items="${payments}" var="payment">
		<tr>
			<td>${payment.date}</td>
			<td>${payment.amount}</td>
			<c:if test="${loggeduser.admin || loggeduser == order.user }">
				<td>
					<my:Exec action="edit" id="${payment.id}" table="payments" /><br />
					<my:Exec action="delete" id="${payment.id}" table="payments" /><br />
				</td>
			</c:if>		
		</tr>
	</c:forEach>
</table>
<c:if test="${loggeduser.admin || loggeduser == order.user }">
	<br />
	<my:Create table="payments" masterId="${entityid}">Create new payment</my:Create>
</c:if>	

</x:page>
