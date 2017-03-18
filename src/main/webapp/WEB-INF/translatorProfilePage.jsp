<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>translator-profile</title>
    <link rel="stylesheet" type="text/css" href=".././css/translatorProfilePage.css">
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
<fmt:setBundle basename="translatorProfile" var="profile"/>
<fmt:message bundle="${profile}" key="contacts" var="contacts"/>
<fmt:message bundle="${profile}" key="city" var="city"/>
<fmt:message bundle="${profile}" key="cell" var="cell"/>
<fmt:message bundle="${profile}" key="email" var="email"/>
<fmt:message bundle="${profile}" key="education" var="edu"/>
<fmt:message bundle="${profile}" key="university" var="uni"/>
<fmt:message bundle="${profile}" key="department" var="dep"/>
<fmt:message bundle="${profile}" key="educationform" var="edform"/>
<fmt:message bundle="${profile}" key="graduationyear" var="gradyear"/>
<fmt:message bundle="${profile}" key="experience" var="exp"/>
<fmt:message bundle="${profile}" key="info" var="info"/>
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
            <h2>${sessionScope.userData.firstName} ${sessionScope.userData.patronymic} ${sessionScope.userData.lastName}</h2>
            <div class="contacts">
                <p><b>${contacts}</b></p>
                <p><b>${city}</b> ${sessionScope.userData.city}</p>
                <p><b>${cell}</b> ${sessionScope.userData.cell}</p>
                <p><b>${email}</b> ${sessionScope.userData.email}</p>
            </div>
            <div class="education">
                <p><b>${edu}</b></p>
                <p><b>${uni}</b> ${sessionScope.userData.education.university}</p>
                <p><b>${dep}</b> ${sessionScope.userData.education.department}</p>
                <p><b>${edform}</b> ${sessionScope.userData.education.educationType}</p>
                <p><b>${gradyear}</b> ${sessionScope.userData.education.graduationYear}</p>
                <p><b>${exp}</b> ${sessionScope.userData.experience}</p>
                <p><b>${info}</b> ${sessionScope.userData.info}</p>
            </div>
            <div class="about-myself">
            </div>
        </section>
        <aside class="button-block">
            <form id="exit-form" action="ProfileExitController" method="post">
                <button class="button" id="exit">${exit}</button>
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
