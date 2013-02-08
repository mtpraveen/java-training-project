<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="addJob.page.title" bundle="${lang}"/></title>
<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
<link rel="stylesheet" href="images/style.css" type="text/css" />
<style type="text/css">
table,hr {
	color: black;
	font-family: Verdana, sans-serif;
	font-size: x-small;
	backgroutd: iherite;
}
</style>
</head>
<body>
	<div class="content">
		<div class="preheader">
			<div class="padding">
				<c:url value="${reqeust.contextPath}/ServletManager?action=logoutAction" var="logoutUrl" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=addJob.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=addJob.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}">Ru</a> | 
				<a href="${enLocale}">En</a>
				<a href="${logoutUrl}"><fmt:message key="addJob.logout" bundle="${lang}"/></a>&nbsp;
			</div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<div class="slogan"><fmt:message key="addJob.slogan" bundle="${lang}"/></div>
		</div>

		<div id="nav">
			<ul>
				<li><a href="index.html"><fmt:message key="addJob.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">
			<div class="sd_left">
				<div class="text_padding">
						<c:url
							value="${request.contextPath}/ServletManager?action=addJobAction&insert=true"
							var="submitUrl" />
						<form method="post" action="${submitUrl}">
							<table>
								<tr>
									<td><fmt:message key="addJob.table.name" bundle="${lang}"/></td>
									<td><input type="text" size="50" name="name" /></td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.beginDate" bundle="${lang}"/></td>
									<td><input type="text" size="50" name="beginDate" /></td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.endDate" bundle="${lang}"/></td>
									<td><input type="text" size="50" name="endDate" /></td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.status" bundle="${lang}"/></td>
									<td><select name="status" size="1">
											<option value="issued"><fmt:message key="addJob.table.status.issued" bundle="${lang}"/></option>
											<option value="started"><fmt:message key="addJob.table.status.started" bundle="${lang}"/></option>
											<option value="completed"><fmt:message key="addJob.table.status.completed" bundle="${lang}"/></option>
											<option value="for revision"><fmt:message key="addJob.table.status.forRevision" bundle="${lang}"/></option>
											<option value="closed"><fmt:message key="addJob.table.status.closed" bundle="${lang}"/></option>
										</select>
									</td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.priority" bundle="${lang}"/></td>
									<td><select name="priority" size="1">
											<option value="the lowest"><fmt:message key="addJob.table.priority.theLowest" bundle="${lang}"/></option>
											<option value="very low"><fmt:message key="addJob.table.priority.veryLow" bundle="${lang}"/></option>
											<option value="low"><fmt:message key="addJob.table.priority.low" bundle="${lang}"/></option>
											<option value="low intermediate"><fmt:message key="addJob.table.priority.lowIntermediate" bundle="${lang}"/></option>
											<option value="intermediate"><fmt:message key="addJob.table.priority.intermediate" bundle="${lang}"/></option>
											<option value="upper intermediate"><fmt:message key="addJob.table.priority.upperIntermediate" bundle="${lang}"/></option>
											<option value="important"><fmt:message key="addJob.table.priority.important" bundle="${lang}"/></option>
											<option value="high"><fmt:message key="addJob.table.priority.high" bundle="${lang}"/></option>
											<option value="very high"><fmt:message key="addJob.table.priority.veryHigh" bundle="${lang}"/></option>
											<option value="the highest"><fmt:message key="addJob.table.priority.theHighest" bundle="${lang}"/></option>
										</select>
									</td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.worker" bundle="${lang}"/></td>
									<td><input type="text" size="50" name="worker" /></td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.manager" bundle="${lang}"/></td>
									<td><input type="text" size="50" name="manager" /></td>
								</tr>
								<tr>
									<td><fmt:message key="addJob.table.description" bundle="${lang}"/></td>
									<td><input type="text" size="50" name="description"></td>
								</tr>
							</table>
							<br /> <input type="submit" name="addJob" value="<fmt:message key="addJob.table.add" bundle="${lang}"/>" />
						</form>
				</div>
			</div>
			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
