<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<legend>Order</legend>
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="orders" name="table"/>
	<c:if test="${isNew}"><input type="hidden" name="masterId" value="${masterId}"/></c:if>
	<c:choose>
		<c:when test="${isNew}">
			<my:FieldCaption>Date: *</my:FieldCaption>
			<my:DateInput name="date" date="${order.date}" id="orderdate" required="1"/>			
			<my:FieldCaption>Tour:</my:FieldCaption>
			<select name="sheduleId" onchange="onTourChange();">
				<c:forEach items="${shedules}" var="shedule">
					<option value="${shedule.id}">${shedule.date} ${shedule.tour.name} : ${shedule.price}</option>
				</c:forEach>
			</select>
			<script>
				function getPrice()
				{
					var sheduleId = document.forms["commentForm"]["sheduleId"].value;
					var prices = [];
					<c:forEach items="${shedules}" var="shedule">
					prices[${shedule.id}] = ${shedule.price}; 
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
			<input type="number" name="discount" value="${discount}" class="required digits" min="0" max="100" onchange="onTourChange();"> %
			<my:FieldCaption>Count: *</my:FieldCaption>
			<input type="number" name="count" value="${order.count}" class="required digits" min="1" onchange="onTourChange();">
			<my:FieldCaption>Cost: </my:FieldCaption>
			<input type="text" name="totalPriceWithoutDiscount" value="0" readonly="1">
			<my:FieldCaption>Cost with discount: *</my:FieldCaption>
			<input type="text" name="totalPrice" value="${order.totalPrice}" class="required number" min="0">
			<my:FieldCaption>Manager:</my:FieldCaption>
			${order.user.name}
			<my:FieldCaption>Description:</my:FieldCaption>
			<input type="text" name="description" value="${order.description}">
		</c:when>
		<c:otherwise>
			<my:FieldCaption>Date: </my:FieldCaption>
			${order.date}
			${totalPayments }<br/>${discount}
			<my:FieldCaption>Tour:</my:FieldCaption>
			${order.tourShedule.date} ${order.tourShedule.tour.name} : ${order.tourShedule.price}
			<my:FieldCaption>Client:</my:FieldCaption>
			${order.client.firstName} ${order.client.lastName}
			<my:FieldCaption>Count: </my:FieldCaption>
			${order.count}
			<my:FieldCaption>Price: </my:FieldCaption>
			${order.totalPrice}
			<my:FieldCaption>Manager:</my:FieldCaption>
			${order.user.name}
			<my:FieldCaption>Description:</my:FieldCaption>
			${order.description}
		</c:otherwise>
	</c:choose>
	<br/>
	<label class="checkbox">
		<input type="checkbox" name="finished" value="ON" <c:if test="${order.finished}">checked</c:if>/> Finished
	</label>
	<my:FieldCaption>Finished at:</my:FieldCaption>
	<my:DateInput name="finishedDate" date="${order.finishedDate}" id="finishedDate"/>
	<br />
	<br />
	<input type="submit">
</form>
</x:page>
