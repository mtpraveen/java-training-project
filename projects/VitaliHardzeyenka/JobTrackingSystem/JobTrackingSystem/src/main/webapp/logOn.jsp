<%@ page language="java" contentType= "text/html; charset=Cp1251" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="logOn.page.title" bundle="${lang}"/></title>
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
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=logOn.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=logOn.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}" id="ruLocale">Ru</a> | 
				<a href="${enLocale}" id="enLocale">En</a>
				<a href="${logoutUrl}"><fmt:message key="logOn.logout" bundle="${lang}"/></a>&nbsp;
			</div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<div class="slogan"><fmt:message key="logOn.slogan" bundle="${lang}"/></div>
		</div>

		<div id="nav">
			<ul>
				<li><a href="index.html"><fmt:message key="logOn.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">
			<div class="sd_left">
				<c:out value="${request.contextPath}" />
				<div class="text_padding">
					<c:url value="${request.contextPath}/ServletManager?action=logonAction" var="submitUrl" />
					<form method="post" action="${submitUrl}">
						<table>
							<tr>
								<c:url value="${request.contextPath}/registration.jsp" var="registrationUrl" />
								<td><a href="${registrationUrl}" id="registration"  name="registration"><fmt:message key="logOn.table.registration" bundle="${lang}"/></a></td>
							</tr>
							<tr>
								<td><fmt:message key="logOn.table.login" bundle="${lang}"/></td>
								<td><input type="text" size="50" name="login"></input></td>
							</tr>
							<tr>
								<td><fmt:message key="logOn.table.password" bundle="${lang}"/></td>
								<td><input type="password" size="50" name="password"></input></td>
							</tr>
						</table>
						<br /> <input type="submit" name="login" value="<fmt:message key="logOn.table.login" bundle="${lang}"/>"></input>
					</form>

				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
