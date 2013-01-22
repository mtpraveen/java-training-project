<%@include file="header.jsp"%>
<h2>
	<spring:message code="label.registry" />
</h2>

<form:form method="post" action="registration" commandName="contact"
	onsubmit="return validateForm(this);">

	<table>
		<tr>
			<td><form:label path="firstname">
					<spring:message code="label.firstname" />
				</form:label></td>
			<td><form:input path="firstname" /></td>
			<td><form:errors path="firstname" /></td>
		</tr>
		<tr>
			<td><form:label path="lastname">
					<spring:message code="label.lastname" />
				</form:label></td>
			<td><form:input path="lastname" /></td>
			<td><form:errors path="lastname" /></td>
		</tr>
		<tr>
			<td><form:label path="email">
					<spring:message code="label.email" />
				</form:label></td>
			<td><form:input path="email" /></td>
			<td><form:errors path="email" /></td>
		</tr>
		<tr>
			<td><form:label path="telephone">
					<spring:message code="label.telephone" />
				</form:label></td>
			<td><form:input path="telephone" /></td>
			<td><form:errors path="telephone" /></td>
		</tr>
		<tr>
			<td><form:label path="password">
					<spring:message code="label.password" />
				</form:label></td>
			<td><form:input type="password" path="password" /></td>
			<td><form:errors path="password" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.register"/>" /></td>
		</tr>
	</table>
</form:form>

<%@include file="footer.jsp"%>
