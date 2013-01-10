<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="date" required="false" rtexprvalue="true" type="java.sql.Date"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>
<c:if test="${empty date}">
	<c:set value="${0}" var="sel_y"/>
	<c:set value="${0}" var="sel_m"/>
	<c:set value="${0}" var="sel_d"/>
</c:if>
<c:if test="${! empty date}">
	<jsp:useBean id="calendar" class="com.travel.web.beans.CalendarWrapper" />
	<c:set property="date" target="${calendar}" value="${date}"/>
	<c:set value="${calendar.year}" var="sel_y"/>
	<c:set value="${calendar.month+1}" var="sel_m"/>
	<c:set value="${calendar.day}" var="sel_d"/>
</c:if>
<select name="${name}__y">
	<option value="" <c:if test="${sel_y == 0}">selected</c:if> />
	<c:forEach begin="1970" end="2030" var="year">
		<option value="${year}" <c:if test="${sel_y == year}">selected</c:if>>${year}</option>
	</c:forEach>
</select>

<select name="${name}__m">
	<option value=""   <c:if test="${sel_m == 0}">selected</c:if> />
	<option value="1"  <c:if test="${sel_m == 1}">selected</c:if>>January</option>
	<option value="2"  <c:if test="${sel_m == 2}">selected</c:if>>February</option>
	<option value="3"  <c:if test="${sel_m == 3}">selected</c:if>>March</option>
	<option value="4"  <c:if test="${sel_m == 4}">selected</c:if>>April</option>
	<option value="5"  <c:if test="${sel_m == 5}">selected</c:if>>May</option>
	<option value="6"  <c:if test="${sel_m == 6}">selected</c:if>>June</option>
	<option value="7"  <c:if test="${sel_m == 7}">selected</c:if>>July</option>
	<option value="8"  <c:if test="${sel_m == 8}">selected</c:if>>August</option>
	<option value="9"  <c:if test="${sel_m == 9}">selected</c:if>>September</option>
	<option value="10" <c:if test="${sel_m == 10}">selected</c:if>>October</option>
	<option value="11" <c:if test="${sel_m == 11}">selected</c:if>>November</option>
	<option value="12" <c:if test="${sel_m == 12}">selected</c:if>>December</option>
</select>

<select name="${name}__d">
	<option value="" <c:if test="${sel_d == 0}">selected</c:if> />
	<c:forEach begin="1" end="31" var="day">
		<option value="${day}" <c:if test="${sel_d == day}">selected</c:if>>${day}</option>
	</c:forEach>
</select>

