<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<x:page>
	<c:if test="${! empty err}">
		<font color="red"><b>${err}</b></font>
	</c:if>
	<c:url value="login" var="loginUrl"/>
	<FORM action="${loginUrl}" method="post">
		Login <BR /> <INPUT type="text" name="login" value="${login}"/> <BR /> Password <BR />
		<INPUT type="password" name="password" /><BR /> <INPUT type="submit" />
	</FORM>
</x:page>
