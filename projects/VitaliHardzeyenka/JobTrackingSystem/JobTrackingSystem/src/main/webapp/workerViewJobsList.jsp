<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%>
<%@ page import="com.epam.logic.model.*" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="workerViewJobsList.page.title" bundle="${lang}"/></title>
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
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=workerViewJobsList.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=workerViewJobsList.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}">Ru</a> | 
				<a href="${enLocale}">En</a>
				<a href="${logoutUrl}"><fmt:message key="workerViewJobsList.logout" bundle="${lang}"/></a>&nbsp;
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
				<li><a href="index.html"><fmt:message key="workerViewJobsList.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">
			<div class="sd_left">
				<div class="text_padding">
					<jsp:useBean id="users" scope="session" class="com.epam.logic.model.Users" />
					
					<h2><fmt:message key="workerViewJobsList.header" bundle="${lang}"/></h2>
					
					<c:forEach var="user" items="${users.users}">
						<h3>
							<c:out value="${user.name} ${user.surname}" />
						</h3>
						<table frame="below" width="100%">
							<tr>
								<th align="left"><fmt:message key="workerViewJobsList.table.edit" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.name" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.percents" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.beginDate" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.endDate" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.status" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.priority" bundle="${lang}"/></th>
								<th align="left"><fmt:message key="workerViewJobsList.table.manager" bundle="${lang}"/></th>
							</tr>
							<c:forEach var="job" items="${user.jobs.jobs}">
								<tr>
									<c:url
										value="${request.contextPath}/ServletManager?action=editJobAction&id=${job.id}&update=false"
										var="editUrl" />
									<td><a href="${editUrl}"><fmt:message key="workerViewJobsList.table.edit" bundle="${lang}"/></a></td>
									<td width="200"><c:out value="${job.name}" /></td>
									<td width="20"><c:out value="${job.percents}" /></td>
									<td width="120"><c:out value="${job.beginDate}" /></td>
									<td width="120"><c:out value="${job.endDate}" /></td>
									<td width="150"><c:out value="${job.status.status}" /></td>
									<td width="150"><c:out value="${job.priority.priority}" /></td>
									<td width="200"><c:out value="${job.manager.name} ${job.manager.surname}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:forEach>
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
