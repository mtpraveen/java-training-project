<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Documents</th>
			<th>Description</th>
			<th>&nbsp;</th>
		</tr>
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
					<my:Exec action="view" id="${client.id}" table="clients" /><br />
					<my:Exec action="edit" id="${client.id}" table="clients" /><br />
					<my:Exec action="delete" id="${client.id}" table="clients" /><br />
				</td>	
			</tr>
		</c:forEach>
	</table>
	<br />
	<my:Create table="clients">Create new client</my:Create>
</x:page>
