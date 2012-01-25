<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

@using (Html.BeginForm()) {
    
    <h2>@Html.DisplayFor(model =>  model.topic.Body)</h2>
    
    @Html.ValidationSummary(true)
    <fieldset>
        <legend>Введите текст:</legend>
                
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
    @Html.ActionLink("Вернуться в тему", "Details", new { id = Model.topicID} )
</div>


</body>
</html>
