<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<c:set var="locale" value="${session.getAttribute('locale')}" scope="page" />
<fmt:setLocale value="${locale}"/>	
<fmt:setBundle basename="jspMessages" var="lang"/>

<title><fmt:message key="registration.page.title" bundle="${lang}"/></title>
<meta http-equiv="content-type" content="text/html;charset=UTF8" />
<link rel="stylesheet" href="images/style.css" type="text/css" />
<style type="text/css">
table {
	color: black;
	font-family: Verdana, sans-serif;
	font-size: x-small;
}

#blockPasswordReliabilityIndicator {
	background: #000;
	width: 98px;
	height: 10px;
	border: 1px solid #fff;
	display: block;
	overflow: hidden;
}

#passwordReliabilityIndicator {
	margin-left: -102px;
	width: 100px;
	height: 8px;
	background: #f00;
	border: 1px solid #fff;
	display: block;
}

#passwordCoincidenceIndicator {
	padding: 1px 4px;
	width: 136px;
	display: block;
	font-size: 10px;
}

#blockEmailReliabilityIndicator {
	background: #000;
	width: 98px;
	height: 10px;
	border: 1px solid #fff;
	display: block;
	overflow: hidden;
}

#emailReliabilityIndicator {
	margin-left: -102px;
	width: 100px;
	height: 8px;
	background: #f00;
	border: 1px solid #fff;
	display: block;
}
</style>
</head>

<body>
	<div class="content">
		<div class="preheader">
			<div class="padding">
				<c:url value="${request.contextPath}/ServletManager?action=logoutAction" var="logoutUrl" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=registration.jsp&locale=ru_RU" var="ruLocale" />
				<c:url value="${request.contextPath}/ServletManager?action=redirectAction&url=registration.jsp&locale=en _US" var="enLocale" />
				<a href="${ruLocale}" id="ruLocale">Ru</a> | 
				<a href="${enLocale}" id="enLocale">En</a>
				<a href="${logoutUrl}"><fmt:message key="registration.logout" bundle="${lang}"/></a>&nbsp;
			</div>
		</div>

		<div class="header">
			<div class="title">JOB TRACKING SYSTEM</div>
			<div class="slogan"><fmt:message key="registration.slogan" bundle="${lang}"/></div>
		</div>

		<div id="nav">
			<ul>
				<li><a href="http://web-mastery.info/"><fmt:message key="registration.link.home" bundle="${lang}"/></a></li>
			</ul>
		</div>

		<div class="main_content">
			<div class="sd_left">
				<div class="text_padding">
					<script type="text/javascript" src="FieldsFillingChecking.js"></script>
					<c:url value="${request.contextPath}/ServletManager?action=regisrationAction" var="submitUrl" />
					<form name="registrationForm" method="post" action="${submitUrl}"
							onSubmit="return checkFormForEmptyFields(this), emailChecking('registrationForm','email','emailReliabilityIndicator')">
						<table id="registrationTable">
							<tr>
								<c:url value="${request.contextPath}/logOn.jsp" var="logonUrl" />
								<td><a href="${logonUrl}"><fmt:message key="registration.logon" bundle="${lang}"/></a></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.login" bundle="${lang}"/></td>
								<td><input type="text" size="50" id="login" name="login" required></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.name" bundle="${lang}"/></td>
								<td><input type="text" size="50" id="name" name="name" required></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.surname" bundle="${lang}"/></td>
								<td><input type="text" size="50" id="surname" name="surname" required></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.password" bundle="${lang}"/></td>
								<td><input type="password" size="50" id="password" name="password"
									onKeyUp="checkPasswordValid('registrationForm','password','passwordReliabilityIndicator','submit'),isPaswordsEquals('registrationForm','password','repeatPassword','passwordCoincidenceIndicator','submit')"
									required> <span id="blockPasswordReliabilityIndicator"><span
										id="passwordReliabilityIndicator"></span></span></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.repeatPassword" bundle="${lang}"/></td>
								<td><input type="password" size="50" id="repeatPassword" name="repeatPassword"
									onKeyUp="isPaswordsEquals('registrationForm','password','repeatPassword','passwordCoincidenceIndicator','submit')"
									required> <span id="passwordCoincidenceIndicator"></span></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.email" bundle="${lang}"/></td>
								<td><input type="text" size="50" id="email" name="email"
									onkeyup="emailChecking('registrationForm','email','emailReliabilityIndicator')"
									required> <span id="blockEmailReliabilityIndicator"><span
										id="emailReliabilityIndicator"></span></span></td>
							</tr>
							<tr>
								<td><fmt:message key="registration.table.position" bundle="${lang}"/></td>
								<td><select id="position" name="position" size="1" required>
										<option value="manager"><fmt:message key="registration.table.position.manager" bundle="${lang}"/></option>
										<option value="worker"><fmt:message key="registration.table.position.worker" bundle="${lang}"/></option>
								</select></td>
							</tr>
						</table>
						<input type="submit" id="submit" name="submit" value=<fmt:message key="registration.table.accept" bundle="${lang}"/> disabled >
					</form>
				</div>
			</div>

			<div class="footer"></div>
		</div>
	</div>
</body>
</html>
