<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><fmt:message key="site_title"/></title>
<link rel="stylesheet" href="jquery/style.css" />
<c:if test="${! empty editor}">
	<link rel="stylesheet" href="jquery/jquery-ui.css" />
	<script src="jquery/jquery-1.8.3.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery.validate.js"></script>
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
<link rel="stylesheet" href="styles/default.css" />
</head>
<body>
<div id="header">
	<div id="userinfo">
		<c:if test="${!empty loggeduser}">
			<fmt:message key="hello_user">
				<fmt:param value="${loggeduser.name}"/>
			</fmt:message> 
			<c:url value="logout" var="logoutUrl" />
			<a href="${logoutUrl}"><fmt:message key="logout"/></a>
		</c:if> 
		<c:if test="${empty loggeduser}">
			<c:url value="login" var="loginUrl" />
			<a href="${loginUrl}"><fmt:message key="login"/></a>
		</c:if>
		&nbsp;
		<c:url value="language-en" var="langurl" />
		<a href="${langurl}"><img src="images/en.png"/></a>
		&nbsp;
		<c:url value="language-ru" var="langurl" />
		<a href="${langurl}"><img src="images/ru.png"/></a>
	</div>
	<c:url value="index" var="indexUrl"></c:url>
	<a href="${indexUrl}"><img alt="hyper travel logo" src="images/logo.png" class="logo"/></a>
	<h1><fmt:message key="site_title"/></h1>
</div>
<div id="body">
	<div id="menubox">
		<ul>
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
		<br />
		<br />
		<c:if test="${!empty loggeduser}"> 
			<c:if test="${loggeduser.admin}">
				<c:url value="install" var="installUrl" />
				<a href="${installUrl}" onclick="return confirmDelete()"><fmt:message key="recreate_all"/></a>
			</c:if>
		</c:if>		 
	</div>
	<div id="content">
		${travel_content}
	</div>
</div>		
</body>
</html>