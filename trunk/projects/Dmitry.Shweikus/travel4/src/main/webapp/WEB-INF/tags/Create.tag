<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag body-content="tagdependent" %>
<%@ attribute name="table" required="true" rtexprvalue="true"%>
<%@ attribute name="masterId" required="false" rtexprvalue="true"%>
<c:url value="new-${table}" var="newurl"/>
<form action="${newurl}" method="post">
	<c:if test="${!empty masterId}">
		<input type="hidden" name="masterId" value="${masterId}"/>
	</c:if>
	<input type="submit" value="<jsp:doBody/>">
</form>


