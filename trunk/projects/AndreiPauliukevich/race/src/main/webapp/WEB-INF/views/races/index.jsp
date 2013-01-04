<%@include file="../layout/header.jsp"%>


<section id="data">
	<div class="offset1 span10">
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>	
		</c:if>
				
		<center>
			<em><spring:message code="race.list" /></em>
		</center>
	</div>
	<div class="offset1 span10 offset1">

		<br>
		<table class="table" id="race">
			<thead>
				<tr>
					<th><spring:message code="race.id" /></th>
					<th><spring:message code="race.startTime" /></th>
					<th><spring:message code="race.coeff" /></th>
					<th><spring:message code="race.winner" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${races}" var="race">
					<tr>
						<td>${race.id}</td>
						<td>${race.startTime}</td>
						<td>${race.coeff}</td>
						<td>${race.winner.name}</td>
						<td><c:url value="/races/edit/${race.id}" var="editRace" />
							<c:url value="/races/delete/${race.id}" var="deleteRace" /> 
							<a class="btn-small btn-info" href="${editRace}"><i	class="icon-briefcase"></i></a> 
							<a class="btn-small btn-danger"	href="${deleteRace}"><i class="icon-remove"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:url value="/races/new" var="newRace" />
		<a href="${newRace}" class="btn "><spring:message code="create"  /></a>
		<br/>&nbsp;
		
	</div>
</section>


<%@include file="../layout/footer.jsp"%>