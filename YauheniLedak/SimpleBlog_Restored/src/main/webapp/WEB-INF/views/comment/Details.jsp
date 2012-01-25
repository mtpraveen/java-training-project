<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

@model MvcSimpleBlog.Models.Comment

<h2>Просмотр комментария</h2>

<fieldset>
    <div class="display-label-field"><b>Содержание:</b> @Html.DisplayFor(model => model.Body)</div>

    <div class="display-label-field"><b>Дата:</b> @Html.DisplayFor(model => model.Date)</div>

</fieldset>

<ul>
    @foreach (var item in Model.subCommentsList)
    {
        <li>@Html.DisplayFor(model => item.Body) @Html.ActionLink("(ветвь дискуссии)", "Details", "Comment", new {commentID = item.CommentID}, "")</li>
    }
</ul>

<p>
    @Html.ActionLink("Оставить комментарий", "CreateNew", new { parentId = Model.CommentID } ) |
    @Html.ActionLink("Назад в тему", "Details", "Topic", new { id=Model.topicID }, "")

</p>


</body>
</html>
