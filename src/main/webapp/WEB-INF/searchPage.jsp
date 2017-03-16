<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search-colleagues</title>
    <link rel="stylesheet" type="text/css" href=".././css/searchPage.css">
</head>
<body>
<header><h1>Transland</h1></header>
<main>
    <form id="exit-form" action="ProfileExitController" method="post">
        <button class="button" id="exit">Выйти</button>
    </form>
    <form id="my-page-form" action="MyPageRedirectController" method="post">
        <button class="button" id="profile-button">Моя страница</button>
    </form>
    <aside class="search-block">
        <form id="search-form" action="" method="post">
            <p>Параметры поиска:</p>
            <input type="text" maxlength="20" id="first-name" name="firstName" index="1" placeholder="Имя:"><span id="er1" class="error hidden"></span>
            <input type="text" maxlength="20" id="last-name" name="lastName" index="2" placeholder="Фамилия:"><span id="er2" class="error hidden"></span>
            <input type="text" maxlength="20" id="middle-name" name="middleName" index="3" placeholder="Отчество:"><span id="er3" class="error hidden"></span>
            <input type="text" maxlength="20" id="city" name="city" index="4" placeholder="Город:"><span id="er4" class="error hidden"></span>
            <input type="tel" maxlength="16" id="cell" name="cell" index="5" placeholder="Тел:"><span id="er5" class="error hidden"></span>
            <input type="email" maxlength="30" id="email" name="email" index="6" placeholder="Эл.почта:"><span id="er6" class="error hidden"></span>
            <input class="button" id="search-button" type="submit" value="Найти"/>
        </form>
    </aside>
    <article class="profile">
        <section class="info">
            <div><p>Результаты поиска:</p></div>
            <div><tr><td>1</td></tr></div>
            <div class="search-results"><div class="search-data"><p>Имя: Фамилия: Отчество:</p><p>Город: Тел: Эл.почта: </p></div><button class="add-button">Добавить</button></div>
            <div class="search-results"><div class="search-data"><p>Имя: Фамилия: Отчество:</p><p>Город: Тел: Эл.почта: </p></div><button class="add-button">Добавить</button></div>
            <div class="search-results"><div class="search-data"><p>Имя: Фамилия: Отчество:</p><p>Город: Тел: Эл.почта: </p></div><button class="add-button">Добавить</button></div>
            <div class="search-results"><div class="search-data"><p>Имя: Фамилия: Отчество:</p><p>Город: Тел: Эл.почта: </p></div><button class="add-button">Добавить</button></div>
        </section>
    </article>
</main>
</body>
</html>
