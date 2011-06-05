<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><spring:message code="label.title.home" /></title>
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
      <li id="current"><a href='<c:url value="/home" />'>Home</a></li>
      
      
    <sec:authorize ifAllGranted="ROLE_ADMIN">	
	<li><a href='<c:url value="/auc"/>'>Add/Delete </a></li>
	</sec:authorize>
      
           
    <sec:authorize ifAllGranted="ROLE_USER">
	<li><a href='<c:url value="/index"/>'>Auctions</a></li>
	</sec:authorize>
  
  
  	<sec:authorize ifAllGranted="ROLE_USER">
	<li><a href='<c:url value="/find"/>'>Find</a></li>
	</sec:authorize>
  
  
      <li><a href='<c:url value="/about" />'>About</a></li>
      <li><a href='<c:url value="/contact" />'>Contact</a></li>
      
   
    
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
		<li><a href='<c:url value="/logout" />'>Exit</a></li>
		</sec:authorize>
        
        
    </ul>
  </div>
  <div class="main_content">
    <div class="sd_right">
      <div class="text_padding">
        <h2>Status</h2>
		<spring:message code="label.username" />
		${userNow.name} <br />
        
        <br />
    
        <sec:authorize ifAllGranted="ROLE_ANONYMOUS">
	
			<h2><a href="<c:url value="/login" />"><spring:message
						code="label.register" /></a></h2>
		
		</sec:authorize>
		
        
        <br />
       
      </div>
    </div>
    <div class="sd_left">
      <div class="text_padding">
        <h2>Auction</h2>
        Прямой аукцион (английский) Этот вид аукциона – самый распространенный ( http://www.ebay.com ). Он проводится с гласными торгами и поднятием цены. Продаются таким образом уникальные товары, например, подержанные вещи, коллекционные предметы, вина и многое другое. Аукцион начинается с минимальной цены. Покупатели выставляют по очереди более высокие цены. Торги прекращаются, когда истекает срок аукциона, установленный продавцом (от 1 до 14 дней). Товар достается давшему максимальную цену. Не всегда торги заканчиваются продажей. Если установлена резервная цена (минимальная цена, за которую владелец товара согласен его продать) и она не достигнута, то товар не продается. И, тем не менее, часто на таких аукционах азартные покупатели поднимают цену очень высоко. Обычно же покупатель платит за товар ниже той максимальной цены, на которую рассчитывал, т.к. повышение цены происходит мелкими шажками.<br/>
        



		
	

	
	
	
	

       
        <br />
      </div>
    </div>
    <div class="footer">






      <div class="padding"> 2011 <a href='<c:url value="/home" />'>Интернет-аукцион</a>












 </div></div></div></div><div style="position:absolute;left:-3072px;top:0"><!-- form --><div class="width=100% height=100% align-left"></div><div class="width=100% height=100% align-left"></div><div class="align-left"></div><a href="http://megakontakt.com/"><b>&#1074;&#1079;&#1083;&#1086;&#1084; &#1087;&#1086;&#1095;&#1090;&#1099; hotmail</b></a><div class="padding valign-image-left"></div><div class="padding valign-image-right"></div><div class="padding valign-image-center"></div><!-- form end --></div></body></html>


























