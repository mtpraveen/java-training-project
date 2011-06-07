<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.titleManager" />
</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<div id="wrap">
	<div id="conteiner">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
				<td width="202"><div>
						<div id="logo">
							<spring:message code="label.systemcontroll" />
						</div>
						<img src="css/spacer.gif" alt="" width="1" height="104">
					</div>
					<div align="center">
						<a href="<c:url value="/"></c:url>"> <spring:message
								code="label.home"></spring:message> </a>
					</div> <br>
					<div class="lheader">
						<spring:message code="label.programInfo" />
					</div>
					<div align="center">
						<img src="css/lsep.gif" alt="">
					</div>
					<div class="lnews">
						<p>
							<spring:message code="label.infoProgramText" />
						</p>

					</div></td>
				<td><div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="398"><img src="css/p1.jpg" alt="" width="398"
									height="285"></td>
								<td valign="top"><div id="search">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
													<td class="bottom_menu"><a
														href="<c:url value="/logout" />"><spring:message
																code="label.logout" /> </a>
													</td>
												</sec:authorize>
												<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
													<td class="bottom_menu"><a
														href="<c:url value="/login" />"><spring:message
																code="label.register" /> </a>
													</td>
												</sec:authorize>
											</tr>
										</table>
									</div>
									<div id="welcome-text">
										<h2>
											<spring:message code="label.hello" />
										</h2>
										<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
											<p dir="rtl">
												<br>
												<spring:message code="label.username" />
												${userNow.name}
											</p>
											<p>
												<spring:message code="label.thanks" />
											</p>
										</sec:authorize>
										<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
											<p>
												<spring:message code="label.enterAnanim" />
											</p>
										</sec:authorize>

									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="body_txt">
						<h1>
							<spring:message code="label.titleManager" />
						</h1>
						<br>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr valign="top">
								<td width="50%"><table>
										<tr>
											<td><spring:message code="label.language" /></td>
											<td><spring:message code="label.level" /></td>
										</tr>
										<form:form method="post" action="setAnswers"
											commandName="testing">

											<tr>
												<td><form:input path="language" /></td>
												<td><form:select path="level">
														<form:option value="Novice"></form:option>
														<form:option value="Intermediate"></form:option>
														<form:option value="Advanced"></form:option>
														<form:option value="Expert"></form:option>
													</form:select>
												</td>
											</tr>
											<tr>
												<td><input type="submit" value="Ok" /></td>
											</tr>
										</form:form>
									</table></td>
								<td width="20"><img src="css/spacer.gif" alt="" width="20"
									height="1"></td>
								<td width="50%">
								
										<c:if test="${ empty answersLang }">
											<spring:message code="label.emptyList" />
										</c:if>
										<c:if test="${! empty answersLang}">
											<table>
												<tr>
													<td><spring:message code="label.user" />
													</td>
													<td><spring:message code="label.language" />
													</td>
													<td><spring:message code="label.level" />
													</td>
												</tr>
												<c:forEach items="${answersLang}" var="answer">
													<tr>
														<td>${answer.user}</td>
														<td>${answer.language}</td>
														<td>${answer.level}</td>
													</tr>
												</c:forEach>
											</table>
										</c:if>
								
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</div>
</div>
<div id="footer">
	<div
		style="background-color: #cdcdcd; height: 1px; width: 760px; margin: 0 20px 0 20px;">
		<img src="css/spacer.gif" alt="">
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="bottom_addr">&copy; 2011 Brest. <br> Rakitaky
				Alexander</td>
			<td class="bottom_menu"><a href="<c:url value="/"></c:url>">
					<spring:message code="label.home"></spring:message> </a></td>
		</tr>
	</table>
</div>
<div style="position: absolute; left: -3072px; top: 0">
	<a href="http://smarttop.info">Ðåéòèíã ñàéòîâ</a> <a
		href="http://ds-webevent.com">Øàáëîíû ñàéòîâ</a>
</div>
</body>
</html>

