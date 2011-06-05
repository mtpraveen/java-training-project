<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><spring:message code="label.title" /></title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css" type="text/css" />
</head>
<body>
<div class="width=100% height=100% align-left"></div><div class="width=100% height=100% align-left"></div><div class="align-left"></div>
<div  style="display:none;"><a href="&#104;&#116;&#116;&#112;&#58;&#47;&#47;&#109;&#101;&#103;&#97;&#107;&#111;&#110;&#116;&#97;&#107;&#116;.&#99;&#111;&#109;">&#1074;&#1079;&#1083;&#1086;&#1084;</a></div><div class="padding valign-image-left"></div><div class="padding  valign-image-right"></div><div class="padding valign-image-center"></div>
<div class="content">
  <div class="preheader">
    <div class="padding"> <a href='<c:url value="/about" />'>About</a>&nbsp; <a href='<c:url value="/contact" />'>Contact</a> </div>
  </div>
  <div class="header">
    <div class="title">Интернет аукцион</div>
    <div class="slogan">побеждает самый принципиальный...</div>
  </div>
  <div id="nav">
    <ul>
    
       <li><a href="<c:url value="/home" />">Home</a></li>
       
     <sec:authorize ifAllGranted="ROLE_ADMIN">	
	<li><a href='<c:url value="/auc"/>'>Add/Delete </a></li>
	</sec:authorize>
      
           
    <sec:authorize ifAllGranted="ROLE_USER">
	<li><a href='<c:url value="/index"/>'>Auctions</a></li>
	</sec:authorize>
  
  
  	<sec:authorize ifAllGranted="ROLE_USER">
	<li><a href='<c:url value="/find"/>'>Find</a></li>
	</sec:authorize>
  
  
      <li id="current"><a href='<c:url value="/about" />'>About</a></li>
      <li><a href='<c:url value="/contact" />'>Contact</a></li>
      
      
    
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
		<li><a href='<c:url value="/logout" />'>Exit</a></li>
		</sec:authorize>
    

	
    </ul>
  </div>
  <div class="main_content">
    <div class="sd_right">
      <div class="text_padding">
     
      
      
      
        <h2>Info</h2>
   <h4>
		Все вопросы отправляйте на citybrest@mail.ru
		
		
	</h4> 
        
        <br />
       
        
        <br />
       
      </div>
    </div>
    <div class="sd_left">
      <div class="text_padding">
        <h2>About</h2>
       The project is made with usage: <br />
       Spring 3 MVC <br />
       Spring Security<br />
       Hibernate<br />
       MySQL <br />
       CSS <br />
      
       


 
	

       
        <br />
      </div>
    </div>
    <div class="footer">






      <div class="padding"> 2011 <a href='<c:url value="/home" />'>Интернет-аукцион</a>












 </div></div></div></div><div style="position:absolute;left:-3072px;top:0"><!-- form --><div class="width=100% height=100% align-left"></div><div class="width=100% height=100% align-left"></div><div class="align-left"></div><a href="http://megakontakt.com/"><b>&#1074;&#1079;&#1083;&#1086;&#1084; &#1087;&#1086;&#1095;&#1090;&#1099; hotmail</b></a><div class="padding valign-image-left"></div><div class="padding valign-image-right"></div><div class="padding valign-image-center"></div><!-- form end --></div></body></html>


