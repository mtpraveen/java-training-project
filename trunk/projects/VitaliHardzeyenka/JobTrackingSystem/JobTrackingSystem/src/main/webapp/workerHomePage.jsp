<%@ page language="java" contentType= "text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="workerHomePage.page.title" bundle="${lang}"/></title>
<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
<link rel="stylesheet" href="images/style.css" type="text/css" />
</head>

<body>
	<div class="content">
		<div class="preheader">
			<div class="padding">
				<c:url value="${request.contextPath}/ServletManager?action=logoutAction" var="logoutUrl" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=workerHomePage.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=workerHomePage.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}">Ru</a> | 
				<a href="${enLocale}">En</a>
				<a href="${logoutUrl}"><fmt:message key="workerHomePage.logout" bundle="${lang}"/></a>&nbsp;
			</div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<c:set var="user" value="${session.getAttribute('user')}" scope="page" />
			<c:set var="nameSurname" value="${user.name} ${user.surname}" scope="page" />
			<div class="slogan"><c:out value="${nameSurname}" /></div>
		</div>

		<div id="nav">
			<ul>
				<li><a href="index.html"><fmt:message key="workerHomePage.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">
			<div class="sd_left">
				<div class="text_padding">
					<jsp:useBean id="user" scope="session" class="com.epam.logic.model.User" />
					<c:url
						value="${request.contextPath}/ServletManager?action=showJobsAction&userId=${user.id}&backJsp=workerViewJobsList.jsp"
						var="showJobsUrl" />
					<a href="${showJobsUrl}"><fmt:message key="workerHomePage.body.showJobs" bundle="${lang}"/></a>
				</div>
			</div>

			<div class="footer"></div>
		</div>

	</div>
</body>
</html>
