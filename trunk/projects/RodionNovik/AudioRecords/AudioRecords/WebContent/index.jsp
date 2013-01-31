<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tags/security.tld" prefix="sec"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<c:set var="currentLanguage"
		value="${not empty param.currentLanguage ? param.currentLanguage : not empty currentLanguage ? currentLanguage : pageContext.request.locale}"
		scope="session" />
	<fmt:setLocale value="${currentLanguage}" />
	<fmt:setBundle basename="text" />
	<div class="languageBar">
		<form action=".">
			<select name="currentLanguage" onchange="submit()">
				<option value="en_US"
					${currentLanguage == 'en_US' ? 'selected' : ''}>English</option>
				<option value="ru_RU"
					${currentLanguage == 'ru_RU' ? 'selected' : ''}>Russian</option>
			</select>
		</form>
	</div>
	<sec:permission role="">
		<div class="loginBar">
			<c:if test="${!empty err}"> ${err} <br></c:if>
				<c:if test="${!empty param.err}"> ${param.err} <br>
			</c:if>
			<form action="LoginServlet" method="post" name="LoginForm">
				<fmt:message key="LOGIN" />
				<br> <input type="text" name="usersLogin"><br>
				<fmt:message key="PASSWORD" />
				<br> <input type="password" name="usersPassword"><br>
				<br> <input type="submit" value="<fmt:message key="ENTER"/>">
			</form>
			<form name="registration" action="registrationPage.jsp">
				<input type="submit" value="<fmt:message key="REGISTER"/>">
			</form>
		</div>
	</sec:permission>
	<sec:permission role="user">
		<fmt:message key="HELLO" />
	${user}
	<form action="LogOut" method="post">
			<a href="LogOut">Logout</a>
		</form>
	</sec:permission>
	<br>
	<br>
	<sec:permission role="user">
		<a href="searchPage.jsp"><fmt:message key="SEARCH" /></a>
	</sec:permission>

	<form action="GetAllRecords" method="get">
		<a href="GetAllRecords"><fmt:message key="VIEWALLRECORDS" /></a>
	</form>
</body>

</html>