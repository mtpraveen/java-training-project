<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

@using (Html.BeginForm()) {
    @Html.ValidationSummary(true)
    <fieldset>
        <legend>Новая тема</legend>
        <div class="editor-label">
            @Html.LabelFor(model => model.Title)
        </div>
        <div class="editor-field">
            @Html.EditorFor(model => model.Title)
            @Html.ValidationMessageFor(model => model.Title)
        </div>

        <div class="editor-label">
            @Html.LabelFor(model => model.Body)
        </div>
        <div class="textarea">
            @Html.TextAreaFor(model => model.Body)
            @Html.ValidationMessageFor(model => model.Body)
        </div>

        <p>
            <input type="submit" value="Опубликовать" />
        </p>
    </fieldset>
}

<div>
    @Html.ActionLink("Вернуться к списку тем", "Index")
</div>


</body>
</html>
