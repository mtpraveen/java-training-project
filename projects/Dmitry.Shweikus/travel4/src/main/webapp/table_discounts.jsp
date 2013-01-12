<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<table border="1">
		<tr>
			<th>Threshold</th>
			<th>Percent</th>
			<th>Active</th>
			<c:if test="${loggeduser.admin}">
				<th>&nbsp;</th>
			</c:if>
		</tr>
		<c:forEach items="${items}" var="discount">
			<tr <c:if test="${!discount.active}">bgcolor="#BFBFBF"</c:if>>
				<td align="right">${discount.threshold}</td>
				<td align="right">${discount.percent}%</td>
				<td><c:if test="${discount.active}">Yes</c:if> <c:if
						test="${!discount.active}">No</c:if></td>
				<c:if test="${loggeduser.admin}">
					<td>
						<my:Exec action="view" id="${discount.id}" table="discounts" /><br />
						<my:Exec action="edit" id="${discount.id}" table="discounts" /><br />
						<my:Exec action="delete" id="${discount.id}" table="discounts" /><br />
					</td>
				</c:if>		
			</tr>
		</c:forEach>
	</table>
	<c:if test="${loggeduser.admin}">
		<br />
		<my:Create table="discounts">Create new discount</my:Create>
	</c:if>	
</x:page>
