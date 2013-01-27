<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="${currentLanguage}" />
	<fmt:setBundle basename="text" />
	<H1>
		<fmt:message key="REGISTRATIONPAGE" />
	</H1>
	<br>
	<c:if test="${!empty err}"> ${err} </c:if>
	<form action="registration" method="post">
		<fmt:message key="LOGIN" />
		<br> <input type="text" name="usersLogin"><br>
		<fmt:message key="PASSWORD" />
		<br> <input type="password" name="usersPassword"><br>
		<fmt:message key="CONFIRMPASSWORD" />
		<br> <input type="password" name="passwordConfirm"><br>
		<input type="submit" value="<fmt:message key="REGISTER"/>">
	</form>

</body>
</html>