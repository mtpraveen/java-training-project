<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<script>
function onTourChange()
{
	count = document.forms["commentForm"]["count"].value;
	document.forms["commentForm"]["totalPrice"].value = getPrice()*count;
}
</script>
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="orders" name="table"/>
	<c:if test="${isNew}"><input type="hidden" name="masterId" value="${masterId}"/></c:if>
	<my:FieldCaption>Date: *</my:FieldCaption>
	<my:DateInput name="date" date="${order.order.date}" id="orderdate" required="1"/>
	<my:FieldCaption>Tour:</my:FieldCaption>

	<c:choose>
		<c:when test="${isNew}">
			<select name="sheduleId" onchange="onTourChange();">
				<c:forEach items="${shedules}" var="tourSheduleItem">
					<option value="${tourSheduleItem.shedule.id}">${tourSheduleItem.shedule.date} ${tourSheduleItem.tour.name} : ${tourSheduleItem.shedule.price}</option>
				</c:forEach>
			</select>
			<script>
				function getPrice()
				{
					price = 0;
					sheduleId = document.forms["commentForm"]["sheduleId"].value;
					<c:forEach items="${shedules}" var="tourSheduleItem">
					if(sheduleId == ${tourSheduleItem.shedule.id}) price = ${tourSheduleItem.shedule.price};
					</c:forEach>
					return price;
				}
			</script>
		</c:when>
		<c:otherwise>
			${order.shedule.date} ${order.tour.name} : ${order.shedule.price}
			<script>
				function getPrice()
				{
					return ${order.shedule.price};
				}
			</script>
		</c:otherwise>
	</c:choose>

	<my:FieldCaption>Client:</my:FieldCaption>
	${order.client.firstName} ${order.client.lastName}
	<my:FieldCaption>Count: *</my:FieldCaption>
	<input type="text" name="count" value="${order.order.count}" class="required digits" min="1" onchange="onTourChange();">
	<my:FieldCaption>Price: *</my:FieldCaption>
	<input type="text" name="totalPrice" value="${order.order.totalPrice}" class="required number" min="0">
	<my:FieldCaption>Manager:</my:FieldCaption>
	${order.user.name}
	<my:FieldCaption>Description:</my:FieldCaption>
	<input type="text" name="description" value="${order.order.description}">
	<br/>
	<br/>
	<input type="checkbox" name="finished" value="ON" <c:if test="${order.order.finished}">checked</c:if>/> Finished
	<my:FieldCaption>Finished at:</my:FieldCaption>
	<my:DateInput name="finishedDate" date="${order.order.finishedDate}" id="finishedDate"/>
	<br />
	 <br />
	<input type="submit">
</form>
</x:page>
