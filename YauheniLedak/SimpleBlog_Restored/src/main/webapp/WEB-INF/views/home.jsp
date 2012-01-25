<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<p><a href = "/SimpleBlog/create">Создать новую тему</a></p>

<table width="95%" border="0">
  <tr>
    <td>
        <table width="100%" border="0">
   
        <c:forEach items="${model}" var="item">

          <tr>
            <td height="10"><hr width="100%" size="2" /></td>
          </tr>
          <tr>
            <td height="20"><p>@Html.ActionLink(item.Title, "Details", "Topic", new { id = item.ID }, "")</p></td>
            <td width = 10%>
            </td>
          </tr>
          <tr>
	        <td height="20"><p>Автор: $(item.AuthorID)</p>
                            <p>Последнее обновление: $(item.TimeLastUpdated)</p>
                            <p>Высказано мнений: $(item.CommentsCount)</p>
            </td>
          </tr>
          <tr>
	        <td height="45"><p>$(item.Body)</p></td>
          </tr>
        </c:forEach>

        </table>
    </td>
  </tr>
</table>

<hr align="left" width="100%" size="2" />

</body>
</html>
