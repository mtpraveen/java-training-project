<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hyper travel</title>
<link rel="stylesheet" href="jquery/style.css" />
<c:if test="${! empty editor}">
	<link rel="stylesheet" href="jquery/jquery-ui.css" />
	<script src="jquery/jquery-1.8.3.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery.validate.js"></script>
	<style type="text/css">
		label { width: 10em; float: left; }
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
</head>
<body>
	<table border="0">
		<tr>
			<td colspan="2" align="right">
				<c:if test="${!empty loggeduser}"> 
					Hello, ${loggeduser.name} 
					<c:url value="logout" var="logoutUrl" />
					<a href="${logoutUrl}">logout</a>
				</c:if> 
				<c:if test="${empty loggeduser}">
					<c:url value="login" var="loginUrl" />
					<a href="${loginUrl}">login</a>
				</c:if></td>
		</tr>
		<tr>
			<td colspan="2">
				<h1>Hyper travel - best travel company in world</h1>
			</td>
		</tr>
		<tr>
			<td width="100px">
				<c:if test="${!empty loggeduser}"> 
					<c:if test="${loggeduser.admin}">
						<c:url value="install" var="installUrl" />
						<a href="${installUrl}">Recreate all</a> <br/>
						<my:Exec table="users" action="show"/><br />
					</c:if>	
					<my:Exec table="clients" action="show"/><br />
					<my:Exec table="orders" action="show"/><br />
				</c:if>
				<my:Exec table="tours" action="show"/><br />
				<my:Exec table="discounts" action="show"/><br />
			</td>
			<td>${travel_content}</td>
		</tr>
	</table>
</body>
</html>