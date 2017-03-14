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
    <div class="info">
        <h2>${sessionScope.userData.firstName} ${sessionScope.userData.patronymic} ${sessionScope.userData.lastName}</h2>
        <p><b>Контактная информация:</b></p>
        <p><b>г.</b> ${sessionScope.userData.city}</p>
        <p><b>тел.</b> ${sessionScope.userData.cell}</p>
        <p><b>эл.почта:</b> ${sessionScope.userData.email}</p>
        <p><b>Образование:</b></p>
        <p><b>Университет:</b> ${sessionScope.userData.education.university}</p>
        <p><b>Факультет:</b> ${sessionScope.userData.education.department}</p>
        <p><b>Форма обучения:</b> ${sessionScope.userData.education.educationType}</p>
        <p><b>Год выпуска:</b> ${sessionScope.userData.education.graduationYear}</p>
        <p><b>Опыт:</b> ${sessionScope.userData.experience}</p>
        <p><b>О себе:</b> ${sessionScope.userData.info}</p>
    </div>

<button class="button" id="exit">Выйти</button>
</body>
</html>
