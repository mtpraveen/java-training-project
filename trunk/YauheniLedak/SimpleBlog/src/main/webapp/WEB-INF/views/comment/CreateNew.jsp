<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Создать комментарий</title>
</head>
<body>

<h2>Создать комментарий</h2>

@using (Html.BeginForm()) {
    @Html.ValidationSummary(true)
    <fieldset>
        <legend>Введите текст</legend>
        <div class="editor-label">
            @Html.LabelFor(model => model.Body)
        </div>
        <div class="editor-field">
            @Html.TextAreaFor(model => model.Body)
            @Html.ValidationMessageFor(model => model.Body)
        </div>
        <p>
            <input type="submit" value="Опубликовать" />
        </p>
    </fieldset>
}

<div>
    @Html.ActionLink("Вернуться к просмотру комментария", "Details", new { commentId = Model.ParentID})
</div>

</body>
</html>
