<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<x:page>
<legend>Order</legend>
<my:FieldCaption>Date:</my:FieldCaption>
${order.date}
<my:FieldCaption>Tour:</my:FieldCaption>
${order.tourShedule.date} ${order.tourShedule.tour.name}
<my:FieldCaption>Client:</my:FieldCaption>
${order.client.firstName} ${order.client.lastName}
<my:FieldCaption>Count:</my:FieldCaption>
${order.count}
<my:FieldCaption>Price:</my:FieldCaption>
${order.totalPrice}
<my:FieldCaption>Manager:</my:FieldCaption>
${order.user.name}
<my:FieldCaption>Description:</my:FieldCaption>
${order.description}
<my:FieldCaption>Finished:</my:FieldCaption>
<c:if test="${order.finished}">
${order.finishedDate}					
</c:if>
<c:if test="${! order.finished}">
not					
</c:if>
<br />
<br />
<legend>Payments</legend>
<table class="table table-bordered table-hover table-condensed table-striped">
	<thead>
	<tr>
		<th>Date</th>
		<th>Amount</th>
		<c:if test="${loggeduser.admin || loggeduser == order.user }">
			<th>&nbsp;</th>
		</c:if>	
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${payments}" var="payment">
		<tr>
			<td>${payment.date}</td>
			<td>${payment.amount}</td>
			<c:if test="${loggeduser.admin || loggeduser == order.user }">
				<td>
					<my:Exec action="edit" id="${payment.id}" table="payments" />
					<my:Exec action="delete" id="${payment.id}" table="payments" />
				</td>
			</c:if>		
		</tr>
	</c:forEach>
	</tbody>
</table>
<c:if test="${loggeduser.admin || loggeduser == order.user }">
	<br />
	<my:Create table="payments" masterId="${entityid}">create_new_payment</my:Create>
</c:if>	

</x:page>
