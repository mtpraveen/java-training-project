<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:directive.page import="java.util.*" />
<jsp:directive.page import="com.epam.logic.model.*" />

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="managerViewWorkers.page.title" bundle="${lang}"/></title>
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
			<div class="padding">
				<c:url value="${request.contextPath}/ServletManager?action=logoutAction" var="logoutUrl" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=managerViewWorkersList.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=managerViewWorkersList.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}">Ru</a> | 
				<a href="${enLocale}">En</a>
				<a href="${logoutUrl}"><fmt:message key="managerViewWorkers.logout" bundle="${lang}"/></a>&nbsp;
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
				<li><a href="index.html"><fmt:message key="managerViewWorkers.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">

			<div class="sd_left">
				<div class="text_padding">
					<jsp:useBean id="users" scope="session" class="com.epam.logic.model.Users" />
					<h2><fmt:message key="managerViewWorkers.table.header" bundle="${lang}"/></h2>
					<table frame="below" width="100%">
						<tr>
							<th align="left"><fmt:message key="managerViewWorkers.table.showJobsAction" bundle="${lang}"/></th>
							<th align="left"><fmt:message key="managerViewWorkers.table.addNewJobAction" bundle="${lang}"/></th>
							<th align="left"><fmt:message key="managerViewWorkers.table.name" bundle="${lang}"/></th>
							<th align="left"><fmt:message key="managerViewWorkers.table.surname" bundle="${lang}"/></th>
							<th align="left"><fmt:message key="managerViewWorkers.table.email" bundle="${lang}"/></th>
						</tr>
						<c:forEach var="user" items="${users.users}">
							<tr>
								<c:url
									value="${request.contextPath}/ServletManager?action=showJobsAction&userId=${user.id}&backJsp=managerViewJobsList.jsp"
									var="showJobsUrl" />
								<td><a href="${showJobsUrl}"><fmt:message key="managerViewWorkers.table.showJobs" bundle="${lang}"/></a></td>
								<c:url
									value="${request.contextPath}/ServletManager?action=addJobAction&insert=false"
									var="addJobUrl" />
								<td><a href="${addJobUrl}"><fmt:message key="managerViewWorkers.table.addJob" bundle="${lang}"/></a></td>
								<td width="200"><c:out value="${user.name}" /></td>
								<td width="150"><c:out value="${user.surname}" /></td>
								<td width="100"><c:out value="${user.email}" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
