<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<p>@Html.ActionLink("Создать новую тему", "Create")</p>

<table width="95%" border="0">
  <tr>
    <td>
        <table width="100%" border="0">
        @foreach (var item in Model)
        {
          <tr>
            <td height="10"><hr width="100%" size="2" /></td>
          </tr>
          <tr>
            <td height="20"><p>@Html.ActionLink(item.Title, "Details", "Topic", new { id = item.ID }, "")</p></td>
            <td width = 10%>
            @{
            if (ViewBag.userIsModerator)
                using (Html.BeginForm("DeleteTopic", "Home", new { topicId = item.ID }, ""))
                {
                    <input type = "submit" value = "Удалить тему" />
                }
            }
            </td>
          </tr>
          <tr>
	        <td height="20"><p>Автор: @Html.ActionLink(item.AuthorID, (System.Web.HttpContext.Current.User.Identity.Name == item.AuthorID) ? "Edit" : "Details", "User", new { id = item.AuthorID }, "")</p>
                            <p>Последнее обновление: @Html.DisplayFor(modelItem => item.TimeLastUpdated)</p>
                            <p>Высказано мнений: @Html.DisplayFor(modelItem => item.CommentsCount)</p>
            </td>
          </tr>
          <tr>
	        <td height="45"><p>@Html.Truncate(item.Body)</p></td>
          </tr>
        }
        </table>
    </td>
  </tr>
</table>

<hr align="left" width="100%" size="2" />


</body>
</html>
