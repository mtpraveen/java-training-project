<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/bootstrap.css" />"	rel="stylesheet" media="screen">

<script src="<c:url value="/resources/js/jquery-1.8.3.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/js/slide.js" />" type="text/javascript" ></script>
<script src="<c:url value="/resources/js/tp.js" />" type="text/javascript" ></script>
<title><spring:message code="title" /></title>
</head>
<body>

			<c:url value="/login" var="login" />
			<c:url value="/user/new" var="signUp" />
			<c:url value="/user/show/" var="userShow" />
			<c:url value="/logout" var="logout" />

<div class="container">
<br/>
<sec:authorize access="isAnonymous()">
	<div class="offset8 span4">
		<a class="btn btn-link" href="${login}">Sign In</a>or<a class="btn btn-link" href="${signUp}">Sign Up</a>
	</div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<div class="span8">
		<ul class="nav nav-pills">
			<c:url value="/race" var="races_url" />
			<li><a href="${races_url}"><spring:message code="races" /></a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<c:url value="/user" var="users_url" />
				<c:url value="/horse" var="horses_url" />
			    <li><a href="${users_url}"><spring:message code="users" /></a></li>
			    <li><a href="${horses_url}"><spring:message code="horses" /></a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_USER')">
				<c:url value="/bid" var="bids_url" />
			    <li><a href="${bids_url}"><spring:message code="bids" /></a></li>
			</sec:authorize>
	    </ul>
	</div>
   	<div class="span3">
		Welcome <a href="${userShow}<sec:authentication property="principal.id" />"><sec:authentication property="principal.username" /></a> 
		&nbsp;&nbsp;[<a href="${logout}">logout</a>]
    </div>
</sec:authorize>
	

	


				