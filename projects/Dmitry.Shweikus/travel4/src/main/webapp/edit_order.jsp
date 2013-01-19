<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="orders" name="table"/>
	<c:if test="${isNew}"><input type="hidden" name="masterId" value="${masterId}"/></c:if>
	<c:choose>
		<c:when test="${isNew}">
			<my:FieldCaption>Date: *</my:FieldCaption>
			<my:DateInput name="date" date="${order.order.date}" id="orderdate" required="1"/>			
			<my:FieldCaption>Tour:</my:FieldCaption>
			<select name="sheduleId" onchange="onTourChange();">
				<c:forEach items="${shedules}" var="tourSheduleItem">
					<option value="${tourSheduleItem.shedule.id}">${tourSheduleItem.shedule.date} ${tourSheduleItem.tour.name} : ${tourSheduleItem.shedule.price}</option>
				</c:forEach>
			</select>
			<script>
				function getPrice()
				{
					var sheduleId = document.forms["commentForm"]["sheduleId"].value;
					var prices = [];
					<c:forEach items="${shedules}" var="tourSheduleItem">
					prices[${tourSheduleItem.shedule.id}] = ${tourSheduleItem.shedule.price}; 
					</c:forEach>
					var price = prices[sheduleId];
					if (price == null)
						return 0;
					else
						return price;
				}
				function getDiscount()
				{
					return document.forms["commentForm"]["discount"].value;
				}
				function onTourChange()
				{
					var count = document.forms["commentForm"]["count"].value;
					var cost = getPrice()*count;
					document.forms["commentForm"]["totalPriceWithoutDiscount"].value = cost;		
					document.forms["commentForm"]["totalPrice"].value = cost*(100 - getDiscount()) / 100;
				}
			</script>
			<my:FieldCaption>Client:</my:FieldCaption>
			${order.client.firstName} ${order.client.lastName}
			<br/>
			Total client payments : ${totalPayments}
			<my:FieldCaption>Discount</my:FieldCaption>
			<input type="text" name="discount" value="${discount}" class="required digits" min="0" max="100" onchange="onTourChange();"> %
			<my:FieldCaption>Count: *</my:FieldCaption>
			<input type="text" name="count" value="${order.order.count}" class="required digits" min="1" onchange="onTourChange();">
			<my:FieldCaption>Cost: </my:FieldCaption>
			<input type="text" name="totalPriceWithoutDiscount" value="0" readonly="1">
			<my:FieldCaption>Cost with discount: *</my:FieldCaption>
			<input type="text" name="totalPrice" value="${order.order.totalPrice}" class="required number" min="0">
			<my:FieldCaption>Manager:</my:FieldCaption>
			${order.user.name}
			<my:FieldCaption>Description:</my:FieldCaption>
			<input type="text" name="description" value="${order.order.description}">
		</c:when>
		<c:otherwise>
			<my:FieldCaption>Date: </my:FieldCaption>
			${order.order.date}
			${totalPayments }<br/>${discount}
			<my:FieldCaption>Tour:</my:FieldCaption>
			${order.shedule.date} ${order.tour.name} : ${order.shedule.price}
			<my:FieldCaption>Client:</my:FieldCaption>
			${order.client.firstName} ${order.client.lastName}
			<my:FieldCaption>Count: </my:FieldCaption>
			${order.order.count}
			<my:FieldCaption>Price: </my:FieldCaption>
			${order.order.totalPrice}
			<my:FieldCaption>Manager:</my:FieldCaption>
			${order.user.name}
			<my:FieldCaption>Description:</my:FieldCaption>
			${order.order.description}
		</c:otherwise>
	</c:choose>
	<br/>
	<input type="checkbox" name="finished" value="ON" <c:if test="${order.order.finished}">checked</c:if>/> Finished
	<my:FieldCaption>Finished at:</my:FieldCaption>
	<my:DateInput name="finishedDate" date="${order.order.finishedDate}" id="finishedDate"/>
	<br />
	<br />
	<input type="submit">
</form>
</x:page>
