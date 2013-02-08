<%@ page language="java" contentType= "text/html; charset=Cp1251" pageEncoding="Cp1251"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="managerHomePage.page.title" bundle="${lang}"/></title>
<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
<link rel="stylesheet" href="images/style.css" type="text/css" />
</head>

<body> 
	<div class="content">
		<div class="preheader">
			<div class="padding">
				<c:url value="${request.contextPath}/ServletManager?action=logoutAction" var="logoutUrl" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=managerHomePage.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=managerHomePage.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}">Ru</a> | 
				<a href="${enLocale}">En</a>
				<a href="${logoutUrl}"><fmt:message key="managerHomePage.logout" bundle="${lang}"/></a>&nbsp;
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
				<li><a href="index.html"><fmt:message key="managerHomePage.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">

			<div class="sd_left">
				<div class="text_padding">
					<c:url value="${request.contextPath}/ServletManager?action=showUsersAction" var="showWorkersUrl" />
					<a href="${showWorkersUrl}" id="showWorkers"><fmt:message key="managerHomePage.body.showWorkers" bundle="${lang}"/></a>

					<c:url
						value="${request.contextPath}/ServletManager?action=showJobsAction&userId=all&backJsp=managerViewJobsList.jsp"
						var="showJobsUrl" />
					<a href="${showJobsUrl}" id="showJobs"><fmt:message key="managerHomePage.body.showJobs" bundle="${lang}"/></a>
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
