<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%>
<%@ page import="com.epam.logic.model.*" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="editJob.page.title" bundle="${lang}"/></title>
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
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=editJob.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=editJob.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}">Ru</a> | 
				<a href="${enLocale}">En</a>
				<a href="${logoutUrl}"><fmt:message key="editJob.logout" bundle="${lang}"/></a>&nbsp;
			</div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<div class="slogan"><fmt:message key="editJob.slogan" bundle="${lang}"/></div>
		</div>

		<div id="nav">
			<ul>
				<li><a href="index.html"><fmt:message key="editJob.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">

			<div class="sd_left">
				<div class="text_padding">
					<jsp:useBean id="job" scope="session" class="com.epam.logic.model.Job" />
					<c:url value="${request.contextPath}/ServletManager?action=editJobAction&jobId=${job.id}&update=true" var="submitUrl" />
					<form method="post" action="${submitUrl}">
						<table>
							<tr>
								<td><fmt:message key="editJob.table.name" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="name"
									value="${job.name}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.beginDate" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="beginDate"
									value="${job.beginDate}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.endDate" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="endDate"
									value="${job.endDate}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.status" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="status"
									value="${job.status.status}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.percents" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="percents"
									value="${job.percents}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.priority" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="priority"
									value="${job.priority.priority}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.worker" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="worker"
									value="${job.worker.id}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.manager" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="manager"
									value="${job.manager.id}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="editJob.table.description" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="description"
									value="${job.description}" /></td>
							</tr>
						</table>
						<br /> <input type="submit" name="editJob" value="<fmt:message key="editJob.table.edit" bundle="${lang}"/>" />
					</form>
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
