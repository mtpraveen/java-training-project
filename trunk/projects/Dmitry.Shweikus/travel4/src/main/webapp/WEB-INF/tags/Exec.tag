<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="table" required="true" rtexprvalue="true"%>
<%@ attribute name="id" required="false" rtexprvalue="true"%>
<%@ attribute name="masterId" required="false" rtexprvalue="true"%>
<%@ attribute name="action" required="true" rtexprvalue="true"%>
<%@ attribute name="label" required="false" rtexprvalue="true"%>
<c:if test="${action == 'edit'}">
	<c:url value="edit-${table}-${id}" var="editurl"/>
	<a href="${editurl}">edit</a>
</c:if>
<c:if test="${action == 'view'}">
	<c:url value="view-${table}-${id}" var="editurl"/>
	<a href="${editurl}">view</a>
</c:if>
<c:if test="${action == 'show'}">
	<c:url value="show-${table}" var="editurl"/>
	<a href="${editurl}">${table}</a>
</c:if>
<c:if test="${action == 'delete'}">
	<c:url value="delete-${table}-${id}" var="editurl"/>
	<a href="${editurl}">delete</a>
</c:if>