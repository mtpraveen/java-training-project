<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
	<title>BlogHost</title>
</head>
<body>
<h1>
	Переименование блога:  
</h1>
Cтарое название блога: ${blognameold}<br>
Новое название блога: ${blogname}
<hr>
<a href="/Blog/">Назад</a>
</body>
</html>