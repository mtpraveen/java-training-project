		<div class="offset3 span6 offset3">
			<c:url var="saveUrl" value="/horses/new" />
			<form:form modelAttribute="horse" method="post" action="${saveUrl}" class="form-inline"  >
				<form:errors path="name" cssClass="alert" element="div" />
				<fieldset>
					<spring:message code="horce.name.placeholder" var="placeholder" />
					<form:input path="name" type="text" placeholder="${placeholder}" />
					<button type="submit" class="btn"><spring:message code="submit" /></button>
				</fieldset>
			</form:form>
		</div>