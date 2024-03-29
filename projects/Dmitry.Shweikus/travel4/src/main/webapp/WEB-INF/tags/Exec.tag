<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="table" required="true" rtexprvalue="true"%>
<%@ attribute name="id" required="false" rtexprvalue="true"%>
<%@ attribute name="masterId" required="false" rtexprvalue="true"%>
<%@ attribute name="action" required="true" rtexprvalue="true"%>
<%@ attribute name="label" required="false" rtexprvalue="true"%>
<fmt:setBundle basename="${bundle}"/>
<c:if test="${action == 'edit'}">
	<c:url value="/edit/${table}/${id}" var="editurl"/>
	<a href="${editurl}" class="btn btn-mini btn-warning"><i class="icon-edit icon-white"></i></a>
</c:if>
<c:if test="${action == 'view'}">
	<c:url value="/view/${table}/${id}" var="editurl"/>
	<a href="${editurl}" class="btn btn-mini btn-info"><i class="icon-search icon-white"></i></a>
</c:if>
<c:if test="${action == 'show'}">
	<c:url value="/show/${table}" var="editurl" />
	<a href="${editurl}" id="${label}"><fmt:message key="${label}"/></a>
</c:if>
<c:if test="${action == 'delete'}">
	<c:url value="/delete/${table}/${id}" var="editurl"/>
	<a href="${editurl}" onclick="return confirmDelete()" class="btn btn-mini btn-danger"><i class="icon-search icon-remove icon-white"></i></a>
</c:if>