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
			<th><fmt:message key="threshold"/></th>
			<th><fmt:message key="percent"/></th>
			<th><fmt:message key="active"/></th>
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
						<ul>
							<li>
								<my:Exec action="view" id="${discount.id}" table="discounts" />
							</li>
							<li>
								<my:Exec action="edit" id="${discount.id}" table="discounts" />
							</li>
							<li>
								<my:Exec action="delete" id="${discount.id}" table="discounts" />
							</li>
						</ul>	
					</td>
				</c:if>		
			</tr>
		</c:forEach>
	</table>
	<c:if test="${loggeduser.admin}">
		<br />
		<my:Create table="discounts">сreate_new_discount</my:Create>
	</c:if>	
</x:page>
