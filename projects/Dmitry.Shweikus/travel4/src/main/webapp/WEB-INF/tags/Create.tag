<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag body-content="tagdependent" %>
<%@ attribute name="table" required="true" rtexprvalue="true"%>
<%@ attribute name="masterId" required="false" rtexprvalue="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${bundle}"/>
<c:url value="/new/${table}" var="newurl"/>
<script type="text/javascript">
	function doCreateNewItem() {
		document.forms["createItemForm"].submit();
	}
</script>
<form action="${newurl}" method="post" name="createItemForm">
	<c:if test="${!empty masterId}">
		<input type="hidden" name="masterId" value="${masterId}"/>
	</c:if>
	<jsp:doBody var="create_tag_body"/>
	<fmt:message key="${create_tag_body}" var="create_tag_caption"/>
	<a class="btn btn-primary" id="createNewItemLink" onclick="doCreateNewItem();" href="#">
	<i class="icon-plus icon-white"></i> ${create_tag_caption}
	</a>
</form>


