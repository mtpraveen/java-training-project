<%@include file="header.jsp"%>

<h2>
	<spring:message code="label.electionResults" />
</h2>

<c:if test="${!today}">
	<p>
		<a href="/votemanager/today"><spring:message code="label.today" /></a>
	</p>
</c:if>
<c:if test="${today}">
	<p>
		<a href="/votemanager/results"><spring:message
				code="label.allResults" /></a>
	</p>
</c:if>
<c:if test="${!empty questionList}">
	<c:forEach items="${questionList}" var="question">
		<table id="poll-container" border="1">
			<tr>
				<td colspan="2">${question.question}</td>
			</tr>
			<tr>
				<td><spring:message code="label.variant" /></td>
				<td><spring:message code="label.votes" /></td>
			</tr>
			<c:forEach items="${question.questionVarinats}" var="variant">
				<tr>
					<td><label>${variant.variant}</label></td>
					<td><label>${variant.count}</label></td>
				</tr>
			</c:forEach>
		</table>
		<p></p>
	</c:forEach>
</c:if>

<%@include file="footer.jsp"%>