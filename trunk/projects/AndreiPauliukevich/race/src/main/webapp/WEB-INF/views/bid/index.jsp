<%@include file="../layout/header.jsp"%>
<section id="data">
	<div class="offset1 span10">
		<div id="center">
			<em><spring:message code="bid.list" /></em>
		</div>
	</div>

	<div class="offset1 span10 offset1">
		<br>
		<table class="table">
			<thead>
				<tr>
					<th><spring:message code="bid.id" /></th>
					<th><spring:message code="race.startTime" /></th>
					<th><spring:message code="race.coeff" /></th>
					<th><spring:message code="horse.name" /></th>
					<th><spring:message code="bid.amount" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bids}" var="bid">
					<tr>
						<td>${bid.race.id}</td>
						<td>${bid.race.startTime}</td>
						<td>${bid.race.coeff}</td>
						<td>${bid.horse.name}</td>
						<td>${bid.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</section>


<%@include file="../layout/footer.jsp"%>