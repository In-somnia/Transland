<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <title>translator-profile</title>
    <link rel="stylesheet" type="text/css" href=".././css/translatorProfilePage.css">
</head>
<body>
<header><h1>Transland</h1></header>
    <div class="pic">
    </div>
    <%--<c:set var="name" scope="page" value=""/>--%>
    <div class="info">
        <h2>${sessionScope.userData.firstName} ${sessionScope.userData.patronymic} ${sessionScope.userData.lastName}</h2>

        <p>г. ${sessionScope.userData.city}</p>
        <p>тел. ${sessionScope.userData.cell}</p>
        <p>эл.почта: ${sessionScope.userData.email}</p>
        <p>Образование:</p>
        <p>Университет: ${sessionScope.userData.education.university}</p>
        <p>Факультет: ${sessionScope.userData.education.department}</p>
        <p>Форма обучения: ${sessionScope.userData.education.educationType}</p>
        <p>Год выпуска: ${sessionScope.userData.education.graduationYear}</p>
        <p>Опыт: ${sessionScope.userData.experience}</p>
        <p>О себе: ${sessionScope.userData.info}</p>
    </div>

<button class="button" id="exit">Выйти</button>
</body>
</html>
