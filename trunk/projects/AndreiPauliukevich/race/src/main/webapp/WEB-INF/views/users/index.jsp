<%@include file="../layout/header.jsp" %>


		<section id="data">
			<div class="offset1 span8 offset3">
				<center>
					<em><spring:message code="user.list" /></em>
				</center>
			</div>
			<div class="offset2 span8 offset2">	
				<br>
				<table class="table">
					<thead>
						<tr>
							<th>id</th>
							<th>login</th>
							<th>name</th>
							<th>email</th>
							<th>money</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.fio}</td>
								<td>${user.email}</td>
								<td>${user.money}</td>
								<td><c:url value="/users/edit/${user.id}" var="editUser" /> 
									<c:url value="/users/delete/${user.id}" var="deleteUser" /> 
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