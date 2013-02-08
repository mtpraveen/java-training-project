<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="logOff.page.title" bundle="${lang}"/></title>
<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
<link rel="stylesheet" href="images/style.css" type="text/css" />
</head>

<body>
	<div class="content">
		<div class="preheader">
			<div class="padding"></div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<div class="slogan"><fmt:message key="logOff.slogan" bundle="${lang}"/></div>
		</div>

		<div id="nav">
			<ul>
				<li><a href="index.html"><fmt:message key="logOff.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">
			<div class="sd_left">
				<div class="text_padding">
					<fmt:message key="logOff.body.message" bundle="${lang}"/>
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
