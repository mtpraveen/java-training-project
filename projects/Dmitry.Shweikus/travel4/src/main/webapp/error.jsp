<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="WEB-INF/simple.tld"%>
<x:page>
	<h1>
		<font color="RED">
			<c:choose>
				<c:when test="${empty errorMessage}">
				<table class="mytable">
					<tr >
						<td >Error:</td>
						<td>${pageContext.exception} ${pageContext.exception.cause}</td>
					</tr>
					<tr>
						<td>URI:</td>
						<td>${pageContext.errorData.requestURI}</td>
					</tr>
					<tr >
						<td>Status code:</td>
						<td>${pageContext.errorData.statusCode}</td>
					</tr>
					<tr>
						<td>Stack trace:</td>
						<td>
							<c:forEach var="trace" 
							         items="${pageContext.exception.stackTrace}">
								<p>${trace}</p>
							</c:forEach>
						</td>
					</tr>
				</table>		
				</c:when>
				<c:otherwise>${errorMessage}</c:otherwise>
			</c:choose>
		</font>
	</h1>
</x:page>
