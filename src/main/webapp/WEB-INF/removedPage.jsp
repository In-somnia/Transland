<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Removed</title>
    <link rel="stylesheet" type="text/css" href=".././css/removedPage.css">
</head>
<body>
<header><h1>Transland</h1></header>
<main>
    <article class="profile">
        <section class="info">
            <div class="pic"></div>
            <h2>Ваша страница удалена!</h2>
            <div class="about-myself"></div>
        </section>
        <aside class="button-block">
            <form id="exit-form" action="ProfileExitController" method="post">
                <button class="button" id="exit">Выйти</button>
            </form>
            <form id="restore-button" action="PageRestoreController" method="post">
                <button class="button" id="restore">Восстановить</button>
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
