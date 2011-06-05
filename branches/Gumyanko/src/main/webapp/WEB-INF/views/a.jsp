<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><spring:message code="label.title" /></title>


<script type="text/javascript">
<!--

function validate(form) {
  var returnValue = true;
  var nameLot = form.nameLot.value;
 // var password1 = form.txtPassword.value;
  //var password2 = form.txtPassword2.value;
  if(nameLot.length <6) {
    returnValue = false;
    errormessage="В имени пользователя должно быть не менее 6 символов"
    document.getElementById("nameLot").nextSibling.innerHTML=errormessage;
    document.addLot.name.focus();
  }else{
    document.getElementById("nameLot").nextSibling.innerHTML="";
  }


  return returnValue;
}
//-->

</script>













<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css" type="text/css" />
</head>
<body>
<div class="width=100% height=100% align-left"></div><div class="width=100% height=100% align-left"></div><div class="align-left"></div>
<div  style="display:none;"><a href="&#104;&#116;&#116;&#112;&#58;&#47;&#47;&#109;&#101;&#103;&#97;&#107;&#111;&#110;&#116;&#97;&#107;&#116;.&#99;&#111;&#109;">&#1074;&#1079;&#1083;&#1086;&#1084;</a></div><div class="padding valign-image-left"></div><div class="padding  valign-image-right"></div><div class="padding valign-image-center"></div>
<div class="content">
  <div class="preheader">
    <div class="padding"> <a href='<c:url value="/about" />'>About</a> &nbsp; <a href='<c:url value="/contact" />'>Contact</a> </div>
  </div>
  <div class="header">
    <div class="title">Интернет аукцион</div>
    <div class="slogan">побеждает самый принципиальный...</div>
  </div>
  <div id="nav">
    <ul>
      <li><a href='<c:url value="/home" />'>Home</a></li>
      
      
    <sec:authorize ifAllGranted="ROLE_ADMIN">	
	<li id="current"><a href='<c:url value="/auc"/>'>Add/Delete </a></li>
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
		admin <br />
        
        <br />
    
       
       
      </div>
    </div>
    <div class="sd_left">
      <div class="text_padding">
       
       
      

<h2><spring:message code="label.add" /></h2>
<form:form name="addLot" method="post" action="add" commandName="tovar" onsubmit="return validate(this)">

	<table>
		<tr>
			<td><form:label path="name">
				<spring:message code="label.firstname" />
			</form:label></td>
			<td><form:input path="name" id="nameLot" /></td>  <span></span>
			 
		</tr>
	
		<tr>
			<td><form:label path="category">
				<spring:message code="label.category" />
			</form:label></td>
			<td><form:input path="category" /></td> 
		</tr>
		
		<tr>
			<td><form:label path="price">
				<spring:message code="label.price" />
			</form:label></td>
			<td><form:input path="price" /></td> 
			 
		</tr>

		<tr>
		
			<td colspan="2"><br/>
			<input type="submit"
				value="<spring:message code="label.addcontact"/>" /></td>
		</tr>
	</table>
</form:form>
<br/>
<h3><spring:message code="label.lots" /></h3>
<c:if test="${!empty tovarList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.firstname" /></th>
			<th><spring:message code="label.category" /></th>
			<th><spring:message code="label.price" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${tovarList}" var="tovar">
			<tr>
				<td>${tovar.name}</td>		
				<td>${tovar.category}</td> 	
				<td>${tovar.price}</td> 
				<td><a href="delete/${tovar.id}"><spring:message code="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
       
       
       
        



		
	

	
	
	
	

       
        <br />
      </div>
    </div>
    <div class="footer">






      <div class="padding"> 2011 <a href='<c:url value="/home" />'>Интернет-аукцион</a>












 </div></div></div></div><div style="position:absolute;left:-3072px;top:0"><!-- form --><div class="width=100% height=100% align-left"></div><div class="width=100% height=100% align-left"></div><div class="align-left"></div><a href="http://megakontakt.com/"><b>&#1074;&#1079;&#1083;&#1086;&#1084; &#1087;&#1086;&#1095;&#1090;&#1099; hotmail</b></a><div class="padding valign-image-left"></div><div class="padding valign-image-right"></div><div class="padding valign-image-center"></div><!-- form end --></div></body></html>


















































