<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag body-content="tagdependent" %>
<%@ attribute name="table" required="true" rtexprvalue="true"%>
<%@ attribute name="masterId" required="false" rtexprvalue="true"%>
<c:if test="${!empty masterId}">
	<c:url value="new-${table}-${masterId}" var="editurl"/>
</c:if>
<c:if test="${empty masterId}">
	<c:url value="new-${table}-0" var="editurl"/>
</c:if>
<a href="${editurl}"><jsp:doBody/></a>


