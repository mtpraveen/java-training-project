<%@include file="../layout/header.jsp" %>

			<section id="data">
				<div class="offset3 span6 offset3">

					<dl class="dl-horizontal">
						<dt>Login:</dt>
						<dd>${user.username}</dd>
						<dt></dt>
						<hr>
						<dd></dd>
						<dt>User FIO</dt>
						<dd>${user.fio}</dd>
						<dt></dt>
						<hr>
						<dd></dd>
						<dt>E-mail:</dt>
						<dd>${user.email}</dd>
					</dl>


					<c:url var="editUrl" value="/users/edit/${user.id}" />
					<a href="${editUrl}" class="btn btn-small"><spring:message code="user.edit"  /></a>
					<br/>&nbsp;
				</div>
			</section>
<%@include file="../layout/footer.jsp" %>