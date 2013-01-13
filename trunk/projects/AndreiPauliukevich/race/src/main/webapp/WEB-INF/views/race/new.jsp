<%@include file="../layout/header.jsp"%>

<section id="data">
	
		<c:url var="saveUrl" value="/race/new" />
		
		<form:form commandName="race" method="post" action="${saveUrl}">
			<form:errors path="startTime" cssClass="alert offset3 span6 " element="div" />
			<form:errors path="coeff" cssClass="alert offset3 span6 " element="div" />
			<form:errors path="horses" cssClass="alert offset3 span6 " element="div" />
			<div class="offset4 span4">
			<fieldset>

				<legend><spring:message code="race.new" />:</legend>
				
				<label><spring:message code="race.startTime" />:</label>
				<form:input path="startTime" id="startTime" />
				
				<label><spring:message code="race.coeff" />:</label>
				<form:input path="coeff"/>
				
				<label><spring:message code="race.horse"  />:</label>
				<form:select path="horses" >
					<form:options items="${horses}" itemLabel="name" itemValue="id" />
				</form:select>
					
				<br>
				<button type="submit" class="btn">Submit</button><br>&nbsp;
			</fieldset>
			</div>
		</form:form>
		
	
</section>

<script>$('#startTime').datetimepicker( { dateFormat: "yy-mm-dd", timeFormat: "HH:mm:ss" }  );</script>

<%@include file="../layout/footer.jsp"%>