<%@include file="../layout/header.jsp"%>


<section id="data">
	<div class="offset1 span8 offset3">
		<div id="center">
			<em><spring:message code="horse.list" /></em>
		</div>
	</div>
	<div class="offset3 span6 offset3">
		<br>
		<table class="table" id="horse">
			<thead>
				<tr>
					<th><spring:message code="horse.id" /></th>
					<th><spring:message code="horse.name" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${horses}" var="horse">
					<tr>
						<td>${horse.id}</td>
						<td>${horse.name}</td>
						<td><c:url value="/horse/edit/${horse.id}" var="editHorse" />
							<c:url value="/horse/delete/${horse.id}" var="deleteHorse" /> <a
							class="btn-small btn-info" href="${editHorse}"><i
								class="icon-edit"></i></a> <a class="btn-small btn-danger"
							href="${deleteHorse}"><i class="icon-remove"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:url value="/horse/new" var="newHorse" />
	</div>
</section>

<%@include file="new.jsp"%>

<%@include file="../layout/footer.jsp"%>