<%@include file="header.jsp"%>

<h2>
	<spring:message code="label.poll" />
</h2>

<c:if test="${!empty questionList}">
	<c:forEach items="${questionList}" var="question">
		<div id="poll-container">
			<h3>
				<spring:message code="label.question" />
			</h3>
			<form:form id='poll' action="poll" method="post"
				onsubmit="return validateForm(this);">
				<p>${question.question}</p>
				<c:forEach items="${question.questionVarinats}" var="variant">
					<p>
						<input type="radio" name="vote" value="${variant.id}"
							id="${variant.id}" /><label for="${variant.id}">&nbsp;${variant.variant}</label>
					</p>
				</c:forEach>
				<input type="submit" value=<spring:message code="label.vote" /> />
			</form:form>
		</div>
	</c:forEach>
</c:if>

<%@include file="footer.jsp"%>