<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">

<script src="<c:url value="/resources/CheckForms.js"/>" type="text/javascript"></script>

<link href="<c:url value="/resources/main.css" />" rel="stylesheet"
	media="screen">
<title><spring:message code="label.title" /></title>

</head>
<body>
	<div id="container">
		<div class="top-row">
			<table>
				<tr>
					<sec:authorize access="hasRole('ROLE_USER')">
						<td><a href="<c:url value="/logout" />"> <spring:message
									code="label.logout" /></a></td>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
						<td><a href="/votemanager/registration"><spring:message
									code="label.register" /></a></td>
						<td><a href="/votemanager/login.jsp"><spring:message
									code="label.login" /></a></td>
					</sec:authorize>
					<td><span id="lang_panel"> <a href="?lang=en">en</a> |
							<a href="?lang=ru">ru</a>
					</span></td>
			</table>
		</div>
		<div class="left-col">
			<sec:authorize access="hasRole('ROLE_USER')">
				<p>
					<a href="/votemanager/vote"><spring:message code="label.vote" /></a>
				</p>
			</sec:authorize>
			<p>
				<a href="/votemanager/results"><spring:message
						code="label.electionResults" /></a>
			</p>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<p>
					<spring:message code="label.administration" />
				</p>
				<p>
					<a href="/votemanager/administration/question"><spring:message
							code="label.questionmanagement" /></a>
				</p>
				<p>
					<a href="/votemanager/administration/contacts"><spring:message
							code="label.contactmanagement" /></a>
				</p>
			</sec:authorize>
		</div>
		<div class="center-col">