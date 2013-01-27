<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.searchForm {
	float: left;
	margin-right: 20px;
}

.resultTable {
	padding-top: 15px;
}
</style>
</head>

<body>
	<c:if test="${empty role}">
		<jsp:forward page="/index.jsp">
			<jsp:param value="Please log in!" name="err"/>
		</jsp:forward>
	</c:if>
	<fmt:setLocale value="${currentLanguage }" />
	<fmt:setBundle basename="text" />
	<div class="searchForm">
		<form action="SearchServlet" method="post">
			<fmt:message key="NAME" />
			<br> <input type="text" name="searchingName"><br>
			<fmt:message key="AUTHOR" />
			<br> <input type="text" name="searchingAuthor"><br>
			<fmt:message key="YEAR" />
			<br> <input type="text" name="searchingYear"><br>
			<fmt:message key="STYLE" />
			<br> <input type="text" name="searchingStyle"><br>
			<fmt:message key="ALBUM" />
			<br> <input type="text" name="searchingAlbum"><br>
			<input type="submit" value="<fmt:message key="SEARCH"/>">
		</form>
	</div>
	<!-- end of search Form -->
	<div class="resultTable">
		<c:if test="${!empty df}"> ${df}</c:if>
	</div>
</body>
</html>