<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

@model MvcSimpleBlog.Models.Topic

<fieldset>
    <legend>@Html.DisplayFor(model => model.Title)</legend>

    <div class="display-label-field"><b>Содержание:</b> @Html.DisplayFor(model => model.Body)</div>

    <div class="display-label-field"><b>Кол-во комментариев:</b> @Html.DisplayFor(model => model.CommentsCount)</div>

    <div class="display-label-field"><b>Последнее обновление:</b> @Html.DisplayFor(model => model.TimeLastUpdated)</div>

</fieldset>


<ul>
    @foreach (var item in Model.commentsList)
    {
        <li>@Html.DisplayFor(model => item.Body) @Html.ActionLink("(развернуть)", "Details", "Comment", new {commentID = item.CommentID}, "")</li>
    }
</ul>


<p>
   @Html.ActionLink("Комментировать", "AddComment", new { topicId = Model.ID } ) |
   @Html.ActionLink("К списку тем", "Index", "Home")
</p>


</body>
</html>
