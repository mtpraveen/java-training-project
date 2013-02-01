<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setBundle basename="${bundle}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><fmt:message key="site_title"/></title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
<link rel="stylesheet" href="<c:url value="/css/custom.css" />" />
<script src="<c:url value="/jquery/jquery-1.8.3.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>" ></script>
<c:if test="${! empty editor}">
	<link rel="stylesheet" href="<c:url value="/jquery/jquery-ui.css" />" />
	<script src="<c:url value="/jquery/jquery-ui.js" />"></script>
	<script src="<c:url value="/jquery/jquery.validate.js" />"></script>
	<style type="text/css">
		label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
		input.error { border: 2px solid #F00; }
		.submit { margin-left: 12em; }
	</style>
  <script>
  $(document).ready(function(){
    $("#commentForm").validate();
  });
  </script>
</c:if>
<script>
function confirmDelete()
{
	answer = confirm("<fmt:message key="confirm_delete"/>");
	return answer;
}
</script>
</head>
<body>
<p/>
<div class="container-fluid">
	<div id="userinfo" class="pull-right">
		<c:if test="${!empty loggeduser}">
			<fmt:message key="hello_user">
				<fmt:param value="${loggeduser.name}"/>
			</fmt:message> 
			<c:url value="/logout" var="logoutUrl" />
			<a href="${logoutUrl}" id="logout" class="btn btn-info btn-mini"><i class="icon-user icon-white"></i> <fmt:message key="logout"/></a>
		</c:if> 
		<c:if test="${empty loggeduser}">
			<c:url value="/login" var="loginUrl" />
			<a href="${loginUrl}" id="login" class="btn btn-info btn-mini"><i class="icon-user icon-white"></i> <fmt:message key="login"/></a>
		</c:if>
		&nbsp;
		<c:url value="/language/en" var="langurl" />
		<a href="${langurl}" class="btn btn-mini"><img src="<c:url value="/images/en.png"/>" /></a>
		&nbsp;
		<c:url value="/language/ru" var="langurl" />
		<a href="${langurl}" class="btn btn-mini"><img src="<c:url value="/images/ru.png"/>" /></a>
	</div>
	<div class="hero-unit">
	<c:url value="/index" var="indexUrl"></c:url>
		<a href="${indexUrl}">
			<h1><fmt:message key="site_title"/></h1>
		</a>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span2">
			<ul class="nav nav-tabs nav-stacked">
				<c:if test="${!empty loggeduser}"> 
					<c:if test="${loggeduser.admin}">
						<li>
							<my:Exec table="users" action="show" label="users"/>
						</li>	
					</c:if>	
					<li>
						<my:Exec table="clients" action="show" label="clients"/>
					</li>	
					<li>
						<my:Exec table="orders" action="show" label="orders"/>
					</li>	
				</c:if>
				<li>
					<my:Exec table="tours" action="show" label="tours"/>
				</li>	
				<li>
					<my:Exec table="discounts" action="show" label="discounts"/>
				</li>	
			</ul>
			<c:if test="${!empty loggeduser}"> 
				<c:if test="${loggeduser.admin}">
					<c:url value="/install" var="installUrl" />
					
					<a  href="#confirmRecreate" class="btn btn-danger btn-block" data-toggle="modal">
						<i class="icon-fire icon-white"></i>
						<fmt:message key="recreate_all"/>
					</a>
					<div class="modal" id="confirmRecreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					  <div class="modal-body">
					    <p>Confirm delete all data and fill database with sample data</p>
					  </div>
					  <div class="modal-footer">
					    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
					    <a href="${installUrl}" class="btn btn-danger">Yes, delete</a>
					  </div>
					</div>	
					<script>
					$("#confirmRecreate").hide();
					</script>			
				</c:if>
			</c:if>	
		</div>
		<div class="span10">
			${travel_content}
		</div>
	</div>
</div>		
</body>
</html>