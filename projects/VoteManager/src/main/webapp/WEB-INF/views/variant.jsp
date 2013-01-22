<%@include file="header.jsp"%>


<table border="1">
	<tr>
		<td colspan="2"><spring:message code="label.variant" /></td>
	</tr>
	<c:if test="${!empty variantList}">
		<c:forEach items="${variantList}" var="variant">
			<tr>
				<td>${variant.variant}</td>
				<td><a href="delete-variant/${variant.id}"><spring:message
							code="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</c:if>
	<tr>
		<form:form method="post" action="show-variant" commandName="variant"
			onsubmit="return validateForm(this);">
			<td><form:input path="variant" /> <form:errors path="variant" /></td>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.addvariant"/>" /></td>
		</form:form>
	</tr>
</table>
<%@include file="footer.jsp"%>