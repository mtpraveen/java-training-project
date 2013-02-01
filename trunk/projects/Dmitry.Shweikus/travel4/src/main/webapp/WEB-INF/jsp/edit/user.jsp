<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="/WEB-INF/simple.tld"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<x:page editor="1">
<script type="text/javascript" language="javascript">
  $(document).ready(function(){
        $("#erorrMsg").hide();	  
		$("#confirmpassword").rules("add", {
			equalTo: "#password"
			});
	  });
  var loginExists = false;
  function checkValidLogin() 
  {
      $("#erorrMsg").hide();	  
	  loginExists = false;
		var httpRequest;

		if (window.XMLHttpRequest) 
		{ 
			httpRequest = new XMLHttpRequest();
			if (httpRequest.overrideMimeType) 
			{
				httpRequest.overrideMimeType('text/xml');
			}
		} 
		else if (window.ActiveXObject) { // IE
			try {
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} 
			catch (e) {
				try {
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} 
				catch (e) {
					return true; 
				}
			}
		}

		if (!httpRequest) {
			return true;
		}
		
		var url = "<c:url value="/ajax/user" />?name=" + document.forms["commentForm"]["login"].value; 
		httpRequest.onreadystatechange = function() { parseResponse(httpRequest); };
		httpRequest.open('GET', url, false);
		httpRequest.send(null);
		return !loginExists;
	}
	function parseResponse(httpRequest) 
	{
		if (httpRequest.readyState == 4) 
		{
			if (httpRequest.status == 200) 
			{
				var users = httpRequest.responseXML.getElementsByTagName('user');
				for(var i=0;i<users.length;i++)
				{
					if (users[i].attributes["id"].value != document.forms["commentForm"]["id"].value)
					{
						loginExists = true;
					    $("#erorrMsg").show();	  
						return;
					}	
				}
			}
		}
	}
</script>
<div class="alert alert-error" id="erorrMsg">
	<h4>ERROR:</h4>
	User with this login already exists	
</div>
<form action='<my:Save/>' method="post" class="cmxform" id="commentForm">
	<legend>User</legend>
	<input type="hidden" value="${entityid}" name="id"/>
	<input type="hidden" value="users" name="table"/>
	
	<my:FieldCaption>Name: *</my:FieldCaption>
	<input type="text" name="name" value="${seluser.name}" class="required" minlength="3">
	
	<my:FieldCaption>Login: *</my:FieldCaption>
	<input type="text" name="login" value="${seluser.login}" class="required" minlength="3">
	
	<c:choose>
		<c:when test="${isNew}">
			<my:FieldCaption>Password: *</my:FieldCaption>
			<input type="password" name="password" id="password" class="required">	
		</c:when>
		<c:otherwise>
			<my:FieldCaption>Password:</my:FieldCaption>
			<input type="password" name="password" id="password">	
			<br/> (enter new password if you want change old password)
		</c:otherwise>
	</c:choose>
	
	<my:FieldCaption>Confirm password: </my:FieldCaption>
	<input type="password" name="confirmpassword" id="confirmpassword">

	<br />
	<label class="checkbox">
		<input type="checkbox" name="admin" <c:if test="${seluser.admin}">checked</c:if> value="on"> Admin <br />
	</label>
	<input type="submit" onclick="return checkValidLogin()">
</form>
</x:page>
