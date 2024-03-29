<%@include file="../layout/header.jsp"%>

<section id="data">

<c:url var="saveUrl" value="/bid/new/${race.id}" />

	<form:form commandName="bid" method="post" action="${saveUrl}">
		<form:errors path="amount" cssClass="alert offset3 span6" element="div" />
		<form:errors path="race" cssClass="alert offset3 span6" element="div" />
		<div class="offset4 span4">
		<fieldset>
			<legend><spring:message code="bid.new" />:</legend><br /> 
			<label><spring:message code="race.startTime" />:${race.startTime}</label><br /> 
			<label><spring:message	code="race.coeff" />: ${race.coeff}</label><br /> 
			<label><spring:message	code="bid.amount" />:<form:input path="amount" class="span1" /></label><br />
			<label><spring:message code="bid.horse" />:</label><br />
			<form:select path="horse" multiple="false">
				<form:options items="${horses}" itemLabel="name" itemValue="id" />
			</form:select>
			<br />
			<button type="submit" class="btn">Submit</button>
			<br>&nbsp;
		</fieldset>
		</div>
	</form:form>

</section>
<%@include file="../layout/footer.jsp"%>