<%@include file="WEB-INF/views/header.jsp"%>


<c:if test="${not empty param.error}">
	<c:if test="${param.error}">
		<font color="red"> <spring:message code="label.loginerror" />
			: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</font>
	</c:if>
</c:if>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
	<table>
		<tr>
			<td align="right"><spring:message code="label.login" /></td>
			<td><input type="text" name="j_username" /></td>
		</tr>
		<tr>
			<td align="right"><spring:message code="label.password" /></td>
			<td><input type="password" name="j_password" /></td>
		</tr>
		<tr>
			<td align="right"><spring:message code="label.remember" /></td>
			<td><input type="checkbox" name="_spring_security_remember_me" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="Login" />
				<input type="reset" value="Reset" /></td>
		</tr>
	</table>
</form>
<%@include file="WEB-INF/views/footer.jsp"%>