<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Login</th>
			<th>Password hash</th>
			<th>Admin</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${items}" var="seluser">
			<tr>
				<td>${seluser.name}</td>
				<td>${seluser.login}</td>
				<td>${seluser.password}</td>
				<td><c:if test="${seluser.admin}">Yes</c:if>
					<c:if test="${!seluser.admin}">No</c:if></td>
				<td>
					<my:Exec action="view" id="${seluser.id}" table="users" /><br />
					<my:Exec action="edit" id="${seluser.id}" table="users" /><br />
					<my:Exec action="delete" id="${seluser.id}" table="users" /><br />
				</td>	
			</tr>
		</c:forEach>
	</table>
	<br />
	<my:Create table="users">Create new user</my:Create>
</x:page>
