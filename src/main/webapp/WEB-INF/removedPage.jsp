<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Removed</title>
    <link rel="stylesheet" type="text/css" href=".././css/removedPage.css">
</head>
<body>
<c:set var="loc" value="${sessionScope.locale}"/>
<c:set var="def" value="${requestScope.locale}"/>
<c:if test="${loc == null}">
    <fmt:setLocale value="${def}"/>
</c:if>
<c:if test="${loc != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="translatorDelete" var="delete"/>
<fmt:setBundle basename="translatorProfile" var="profile"/>
<fmt:message bundle="${delete}" key="message" var="message"/>
<fmt:message bundle="${delete}" key="restorebutton" var="restore"/>
<fmt:message bundle="${profile}" key="mypagebutton" var="mypage"/>
<fmt:message bundle="${profile}" key="colleaguesbutton" var="colleagues"/>
<fmt:message bundle="${profile}" key="editbutton" var="edit"/>
<fmt:message bundle="${profile}" key="deletebutton" var="del"/>
<fmt:message bundle="${profile}" key="exitbutton" var="exit"/>
<header><h1>Transland</h1></header>
<main>
    <article class="profile">
        <section class="info">
            <div class="pic"></div>
            <h2>${message}</h2>
            <div class="about-myself"></div>
        </section>
        <aside class="button-block">
            <form id="exit-form" action="ProfileExitController" method="post">
                <button class="button" id="exit">${exit}</button>
            </form>
            <form id="restore-button" action="PageRestoreController" method="post">
                <button class="button" id="restore">${restore}</button>
            </form>
            <form id="my-page-form" action="MyPageRedirectController" method="post">
            <button class="side-button" id="profile-button">${mypage}</button>
            </form>
            <form id="colleagues-search" action="SearchRedirectController" method="post">
            <button class="side-button" id="colleagues-button">${colleagues}</button>
            </form>
            <form id="edit-page" action="EditPageRedirectController" method="post">
                <button class="side-button" id="edit-button">${edit}</button>
            </form>
            <form id="delete-page" action="PageRemoveController" method="post">
                <button class="side-button" id="delete-button">${del}</button>
            </form>
        </aside>
    </article>
</main>



</body>
</html>
