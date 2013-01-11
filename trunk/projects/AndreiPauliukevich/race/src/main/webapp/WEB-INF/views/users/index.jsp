<%@include file="../layout/header.jsp" %>


		<section id="data">
			<div class="offset1 span8 offset3">
				<div id="center">
					<em><spring:message code="user.list" /></em>
				</div>
			</div>
			<div class="offset2 span8 offset2">	
				<br>
				<table class="table">
					<thead>
						<tr>
							<th><spring:message code="user.id" /></th>
							<th><spring:message code="user.login" /></th>
							<th><spring:message code="user.fio" /></th>
							<th><spring:message code="user.email" /></th>
							<th><spring:message code="user.money" /></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<c:url value="/users/edit/${user.id}" var="editUser" /> 
							<c:url value="/users/delete/${user.id}" var="deleteUser" />
							<c:url value="/users/show/${user.id}" var="showUser" /> 
							<tr>
								<td>${user.id}</td>
								<td><a href=${showUser}>${user.username}</a></td>
								<td>${user.fio}</td>
								<td>${user.email}</td>
								<td>${user.money}</td>
								<td>
									<a class="btn-small btn-info" href="${editUser}"><i class="icon-edit"></i></a> 
									<a class="btn-small btn-danger"	href="${deleteUser}"><i class="icon-remove"></i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</section>

<%@include file="../layout/footer.jsp" %>