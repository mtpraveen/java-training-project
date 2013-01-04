<%@include file="../layout/header.jsp"%>

			<br/>
			<sec:authorize access="isAnonymous()">
		    	<div class="offset2 span8 offset2">
						<br/><br/>
						<c:if test="${not empty message}">
							<div class="alert alert-error">${message}</div>	
						</c:if>
		    			<hr>
		    		    <form class="form-horizontal" action="j_spring_security_check" method="post" >
		    		    	<div class="control-group">
		    		    		<label class="control-label" for="inputUsername">Login:</label>
						    	<div class="controls">
						    		<input type="text" id="inputUsername" placeholder="Username" name="j_username">
						    	</div>
						    </div>
						    <div class="control-group">
		    		    		<label class="control-label" for="inputPassword">Password:</label>
		    		    			<div class="controls">
						    			<input id="inputPassword" type="password" placeholder="Password" name="j_password">
						    		</div>
						    </div>	
						    <div class="control-group">	
						    	<div class="controls">
						    		<button type="submit" class="btn">Sign in</button>
						    	</div>
						    </div>

					    </form>
	        	</div>
			</sec:authorize>
			
<%@include file="../layout/footer.jsp"%>			
