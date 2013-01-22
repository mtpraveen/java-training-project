<%@include file="header.jsp"%>
<h2>
	<spring:message code="label.questionmanagement" />
</h2>

<form:form method="post" action="question" commandName="question"
	onsubmit="return validateForm(this);">

	<table>
		<tr>
			<td><form:label path="question">
					<spring:message code="label.question" />
				</form:label></td>
			<td><form:input path="question" /></td>
			<td><form:errors path="question" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.addquestion"/>" /></td>
		</tr>
	</table>
</form:form>

<h3>
	<spring:message code="label.questions" />
</h3>
<c:if test="${!empty questionList}">
	<table border="1">
		<tr>
			<th><spring:message code="label.question" /></th>
		</tr>
		<c:forEach items="${questionList}" var="question">
			<tr>
				<td>${question.question}</td>
				<td><a href="delete-question/${question.id}"><spring:message
							code="label.delete" /></a></td>
				<td><a href="${question.id}/show-variant"><spring:message
							code="label.showVariants" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="footer.jsp"%>