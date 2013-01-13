<%@include file="../layout/header.jsp"%>

<section id="data">
	<div class="offset3 span6 offset3">
		<c:url var="saveUrl" value="/horse/edit/${horse.id}" />
		<form:form modelAttribute="horse" method="post" action="${saveUrl}">
			<form:errors path="name" cssClass="alert" element="div" />
			<fieldset>
				<legend>
					<spring:message code="horse.edit" />
				</legend>
				<label><spring:message code="horse.name" />:</label>
				<spring:message code="horce.name.placeholder" var="placeholder" />
				<form:input path="name" type="text" placeholder="${placeholder}" />
				<br>
				<button type="submit" class="btn">
					<spring:message code="edit" />
				</button>
			</fieldset>
		</form:form>
	</div>
</section>

<%@include file="../layout/footer.jsp"%>