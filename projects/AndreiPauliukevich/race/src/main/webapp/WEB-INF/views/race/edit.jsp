<%@include file="../layout/header.jsp"%>

<section id="data">
	<c:url var="editUrl" value="/race/edit/${race.id}" />
	<c:url var="cancel" value="/race/" />
	<form:form commandName="race" method="post" action="${editUrl}">
		<form:errors path="startTime" cssClass="alert offset3 span6 " element="div" />
		<form:errors path="coeff" cssClass="alert offset3 span6 " element="div" />
		<form:errors path="horses" cssClass="alert offset3 span6 " element="div" />
		<div class="offset4 span4">
		<fieldset>

			<legend><spring:message code="race.edit" />:</legend>
			
			<label><spring:message code="race.startTime" />:</label>
			<form:input path="startTime" id="startTime" disabled="true" />
			
			<label><spring:message code="race.coeff" />:</label>
			<form:input path="coeff" disabled="true" />
			
			<label><spring:message code="race.horse"  />:</label>
			<form:select path="winner" multiple="false">
				<form:options items="${race.horses}" itemLabel="name" itemValue="id" />
			</form:select>
												
			<br>
			<button type="submit" class="btn"><spring:message code="submit" /></button>
			<a href="${cancel}" class="btn"><spring:message code="cancel" /></a>
			<br>&nbsp;
		</fieldset>
		</div>
	</form:form>
</section>

<script>$('#startTime').datetimepicker( { dateFormat: "yy-mm-dd", timeFormat: "hh:mm:ss" }  );</script>

<%@include file="../layout/footer.jsp"%>