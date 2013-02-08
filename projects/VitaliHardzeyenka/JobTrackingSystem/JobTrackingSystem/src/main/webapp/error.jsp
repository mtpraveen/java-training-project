<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="error.page.title" bundle="${lang}"/></title>
<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
<link rel="stylesheet" href="images/style.css" type="text/css" />
<style type="text/css">
table,hr {
	color: black;
	font-family: Verdana, sans-serif;
	font-size: x-small;
}
</style>
</head>

<body>
	<div class="content">
		<div class="preheader">
			<div class="padding"></div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<div class="slogan"><fmt:message key="error.slogan" bundle="${lang}"/></div>
		</div>

		<div class="main_content">

			<div class="sd_left">
				<div class="text_padding">
					<h2>
						<fmt:message key="error.number" bundle="${lang}"/>
						<c:set var="errorNumber" value="${session.getAttribute('errorNumber')}" />
						<c:out value="${errorNumber}" />
					</h2>
					<fmt:message key="error.message" bundle="${lang}"/>
					<c:set var="errorMessage" value="${session.getAttribute('errorMessage')}" />
					<c:out value="${errorMessage}" />
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
