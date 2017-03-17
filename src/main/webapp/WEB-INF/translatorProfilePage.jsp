<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <title>translator-profile</title>
    <link rel="stylesheet" type="text/css" href=".././css/translatorProfilePage.css">
</head>
<body>
<header><h1>Transland</h1></header>
<main>
    <article class="profile">
        <section class="info">
            <div class="pic"></div>
            <h2>${sessionScope.userData.firstName} ${sessionScope.userData.patronymic} ${sessionScope.userData.lastName}</h2>
            <div class="contacts">
                <p><b>Контактная информация:</b></p>
                <p><b>г.</b> ${sessionScope.userData.city}</p>
                <p><b>тел.</b> ${sessionScope.userData.cell}</p>
                <p><b>эл.почта:</b> ${sessionScope.userData.email}</p>
            </div>
            <div class="education">
                <p><b>Образование:</b></p>
                <p><b>Университет:</b> ${sessionScope.userData.education.university}</p>
                <p><b>Факультет:</b> ${sessionScope.userData.education.department}</p>
                <p><b>Форма обучения:</b> ${sessionScope.userData.education.educationType}</p>
                <p><b>Год выпуска:</b> ${sessionScope.userData.education.graduationYear}</p>
                <p><b>Опыт:</b> ${sessionScope.userData.experience}</p>
                <p><b>О себе:</b> ${sessionScope.userData.info}</p>
            </div>
            <div class="about-myself">
            </div>
        </section>
        <aside class="button-block">
            <form id="exit-form" action="ProfileExitController" method="post">
                <button class="button" id="exit">Выйти</button>
            </form>
            <form id="my-page-form" action="MyPageRedirectController" method="post">
                <button class="side-button" id="profile-button">Моя страница</button>
            </form>
            <form id="colleagues-search" action="SearchRedirectController" method="post">
                <button class="side-button" id="colleagues-button">Найти коллег</button>
            </form>
            <form id="edit-page" action="EditPageRedirectController" method="post">
                <button class="side-button" id="edit-button">Редактировать</button>
            </form>
            <form id="delete-page" action="PageRemoveController" method="post">
                <button class="side-button" id="delete-button">Удалить страницу</button>
            </form>
        </aside>
    </article>
</main>
</body>
</html>
