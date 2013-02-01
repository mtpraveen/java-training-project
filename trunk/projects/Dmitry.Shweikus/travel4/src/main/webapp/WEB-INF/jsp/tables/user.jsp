<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<x:page>
	<legend><fmt:message key="users"/></legend>
	<table class="table table-bordered table-hover table-condensed table-striped">
		<thead>
			<tr>
				<th><fmt:message key="name"/></th>
				<th><fmt:message key="login"/></th>
				<th><fmt:message key="admin"/></th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${items}" var="seluser">
			<tr>
				<td>${seluser.name}</td>
				<td>${seluser.login}</td>
				<td><c:if test="${seluser.admin}"><fmt:message key="yes"/></c:if>
					<c:if test="${!seluser.admin}"><fmt:message key="no"/></c:if></td>
				<td>
					<my:Exec action="view" id="${seluser.id}" table="users" />
					<my:Exec action="edit" id="${seluser.id}" table="users" />
					<my:Exec action="delete" id="${seluser.id}" table="users" />
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br />
	<my:Create table="users">create_new_user</my:Create>
</x:page>
