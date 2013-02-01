<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<legend><fmt:message key="clients"/></legend>
	<table class="table table-bordered table-hover table-condensed table-striped">
		<thead>
			<tr>
				<th><fmt:message key="name"/></th>
				<th><fmt:message key="documents"/></th>
				<th><fmt:message key="description"/></th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${items}" var="client">
			<tr>
				<td>${client.firstName} ${client.lastName}</td>
				<td> 
					<c:if test="${client.document1 != ''}">${client.document1} <br /></c:if>
					<c:if test="${client.document2 != ''}">${client.document2} <br /></c:if>
					<c:if test="${client.document3 != ''}">${client.document3} <br /></c:if>
					<c:if test="${client.document4 != ''}">${client.document4} <br /></c:if>
				</td>
				<td>${client.description}</td>
				<td>
					<my:Exec action="view" id="${client.id}" table="clients" />
					<my:Exec action="edit" id="${client.id}" table="clients" />
					<my:Exec action="delete" id="${client.id}" table="clients" />
				</td>	
			</tr>
		</c:forEach>
	</table>
	<br />
	<my:Create table="clients">create_new_client</my:Create>
</x:page>
