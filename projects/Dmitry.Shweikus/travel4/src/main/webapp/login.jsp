<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<x:page>
	<c:if test="${! empty err}">
		<font color="red"><b>${err}</b></font>
	</c:if>
	<c:url value="login" var="loginUrl"/>
	<FORM action="${loginUrl}" method="post">
		Login <BR /> 
		<INPUT type="text" name="login" value="${login}"/> <BR /> 
		Password <BR />
		<INPUT type="password" name="password" /><BR /> 
		<INPUT type="submit" />
	</FORM>
</x:page>
