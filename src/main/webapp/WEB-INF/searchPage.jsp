<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>search-colleagues</title>
    <link rel="stylesheet" type="text/css" href=".././css/searchPage.css">
</head>
<body>
<c:set var="loc" value="${sessionScope.locale}"/>
<c:set var="def" value="${requestScope.locale}"/>
<c:if test="${loc == null}">
    <fmt:setLocale value="${def}"/>
</c:if>
<c:if test="${loc != null}">
    <fmt:setLocale value="${loc}"/>
</c:if>
<fmt:setBundle basename="translatorRegistration" var="reg"/>
<fmt:message bundle="${reg}" key="fnameplaceholder" var="name"/>
<fmt:message bundle="${reg}" key="lnameplaceholder" var="surname"/>
<fmt:message bundle="${reg}" key="mnameplaceholder" var="patronymic"/>
<fmt:message bundle="${reg}" key="cityplaceholder" var="city"/>
<fmt:message bundle="${reg}" key="cellplaceholder" var="cell"/>
<fmt:message bundle="${reg}" key="emailplaceholder" var="email"/>
<fmt:setBundle basename="translatorSearch" var="search"/>
<fmt:message bundle="${search}" key="criteria" var="criteria"/>
<fmt:message bundle="${search}" key="res" var="ressearch"/>
<fmt:message bundle="${search}" key="find" var="find"/>
<fmt:message bundle="${search}" key="watchbutton" var="watch"/>
<fmt:setBundle basename="translatorProfile" var="profile"/>
<fmt:message bundle="${profile}" key="mypagebutton" var="mypage"/>
<fmt:message bundle="${profile}" key="exitbutton" var="exit"/>

<header><h1>Transland</h1></header>
<main>
    <form id="exit-form" action="ProfileExitController" method="post">
        <button class="button" id="exit">${exit}</button>
    </form>
    <form id="my-page-form" action="MyPageRedirectController" method="post">
        <button class="button" id="profile-button">${mypage}</button>
    </form>
    <aside class="search-block">
        <form id="search-form" action="SearchController?page=0" method="post">
            <p>${criteria}</p>
            <input type="text" maxlength="20" id="first-name" name="firstName" index="1" placeholder="${name}">
            <span id="er1" class="error hidden"></span>
            <input type="text" maxlength="20" id="last-name" name="lastName" index="2" placeholder="${surname}">
            <span id="er2" class="error hidden"></span>
            <input type="text" maxlength="20" id="middle-name" name="middleName" index="3" placeholder="${patronymic}">
            <span id="er3" class="error hidden"></span>
            <input type="text" maxlength="20" id="city" name="city" index="4" placeholder="${city}">
            <span id="er4" class="error hidden"></span>
            <input type="tel" maxlength="16" id="cell" name="cell" index="5" placeholder="${cell}">
            <span id="er5" class="error hidden"></span>
            <input type="email" maxlength="30" id="email" name="email" index="6" placeholder="${email}">
            <span id="er6" class="error hidden"></span>
            <input class="button" id="search-button" type="submit" value="${find}"/>
        </form>
    </aside>
    <article class="profile">
        <section class="info">
            <div><p>${ressearch}</p></div>
            <tr>
            <c:set var="thisPage" value="${sessionScope.currentPage}"/>
            <c:forEach var="counter" begin="1" end="${sessionScope.pages}">
                <c:if test="${counter == thisPage}">
                    <td>${thisPage}</td>
                </c:if>
                <c:if test="${counter != thisPage}">
                    <td><a href="SearchController?page=${counter}"><c:out value="${counter}"/></a></td>
                </c:if>
            </c:forEach>
            </tr>
            <c:forEach var="translator" items="${sessionScope.thisPageTranslators}">
                    <div class="search-results"><div class="search-data"><p>
                    <c:out value="${translator.firstName}"/>
                    <c:out value="${translator.patronymic}"/>
                    <c:out value="${translator.lastName}"/>
                    <c:out value="${translator.city}"/>
                    <c:out value="${translator.cell}"/>
                    <c:out value="${translator.email}"/></p></div>
                        <form class = "watch-form" action="SeeProfileController?${translator.email}" method="post">
                            <button class="watch-button">${watch}</button>
                        </form>
                    </div>
            </c:forEach>
        </section>
    </article>
</main>
</body>
</html>
