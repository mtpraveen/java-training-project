<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="date" required="false" rtexprvalue="true" type="java.sql.Date"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>
<%@ attribute name="id" required="true" rtexprvalue="true"%>
<%@ attribute name="required" required="false" rtexprvalue="true"%>
<script>
  $(function() {
    $( "#${id}" ).datepicker();
    $( "#${id}" ).datepicker( "option", "dateFormat", "yy-mm-dd");
    <c:if test="${!empty date }">
	    $( "#${id}" ).datepicker( "setDate", "${date}" );
	</c:if>
  });
</script>
<c:choose>
	<c:when test="${empty required}">
		<c:set value="dateISO" var="inputClass"/>
	</c:when>
	<c:otherwise>
		<c:set value="dateISO required" var="inputClass"/>
	</c:otherwise>	
</c:choose>
<input type="text" id="${id}" value="${date}" name="${name}" class="${inputClass}"/>

