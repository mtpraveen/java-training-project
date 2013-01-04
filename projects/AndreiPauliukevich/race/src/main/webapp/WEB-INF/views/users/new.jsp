<%@include file="../layout/header.jsp" %>


	    	<section id="data" >
				<div class="offset3 span5 offset3">
					
					<c:url var="saveUrl" value="/users/new" />
					<form:form modelAttribute="user" method="post" action="${saveUrl}">
						<form:errors path="username" cssClass="alert" element="div"/>
						<form:errors path="password" cssClass="alert" element="div"/>
						<form:errors path="fio" cssClass="alert" element="div"/>
						<form:errors path="email" cssClass="alert" element="div"/>
					    <fieldset>
						    <legend>Register new user</legend>
						    <label>Login:</label>
						    <form:input path="username" type="text" placeholder="Login..."></form:input>
						    <label>Password:</label>
						    <form:input path="password" type="text" ></form:input>
							<label>Name:</label>
						    <form:input path="fio" type="text" placeholder="First and Last name"></form:input>
						    <label>E-mail:</label>
						    <form:input path="email" type="text" placeholder="example@dot.com"></form:input>
							<br>
						    <button type="submit" class="btn">Submit</button>
					    </fieldset>
				    </form:form>
				</div>


	    	</section>

<%@include file="../layout/footer.jsp" %>