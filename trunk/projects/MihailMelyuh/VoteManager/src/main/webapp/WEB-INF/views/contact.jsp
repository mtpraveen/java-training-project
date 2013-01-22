<%@include file="header.jsp"%>

<h2>
	<spring:message code="label.contactmanagement" />
</h2>

<form:form method="post" action="contacts" commandName="contact"
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
				value="<spring:message code="label.addcontact"/>" /></td>
		</tr>
	</table>
</form:form>

<h3>
	<spring:message code="label.contacts" />
</h3>
<c:if test="${!empty contactList}">
	<table border="1">
		<tr>
			<th><spring:message code="label.firstname" /></th>
			<th><spring:message code="label.email" /></th>
			<th><spring:message code="label.telephone" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${contactList}" var="contact">
			<tr>
				<td>${contact.lastname}, ${contact.firstname}</td>
				<td>${contact.email}</td>
				<td>${contact.telephone}</td>
				<td><a href="deleteContact/${contact.id}"><spring:message
							code="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="footer.jsp"%>