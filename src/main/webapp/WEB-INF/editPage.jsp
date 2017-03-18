<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>edit-page</title>
    <link rel="stylesheet" type="text/css" href="../css/editPage.css">
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
<fmt:setBundle basename="translatorEdit" var="edit"/>
<fmt:setBundle basename="translatorRegistration" var="reg"/>
<fmt:setBundle basename="translatorProfile" var="profile"/>
<fmt:message bundle="${edit}" key="save" var="save"/>
<fmt:message bundle="${profile}" key="contacts" var="contacts"/>
<fmt:message bundle="${reg}" key="cityplaceholder" var="city"/>
<fmt:message bundle="${reg}" key="cellplaceholder" var="cell"/>
<fmt:message bundle="${reg}" key="emailplaceholder" var="email"/>
<fmt:message bundle="${profile}" key="education" var="edu"/>
<fmt:message bundle="${reg}" key="uniplaceholder" var="uni"/>
<fmt:message bundle="${reg}" key="departmentplaceholder" var="dep"/>
<fmt:message bundle="${reg}" key="edformplaceholder" var="edform"/>
<fmt:message bundle="${reg}" key="gradyearplaceholder" var="gradyear"/>
<fmt:message bundle="${reg}" key="expplaceholder" var="exp"/>
<fmt:message bundle="${reg}" key="infoplaceholder" var="info"/>


    <header><h1>Transland</h1></header>
    <main>
        <article class="profile">
            <section class="info">
                <div class="pic"></div>
                <h2>${sessionScope.userData.firstName} ${sessionScope.userData.patronymic} ${sessionScope.userData.lastName}</h2>
                <form class="form" id="edit-form" action="PageEditController" method="post">
                    <b>${contacts}</b>
                    <input type="text" required id="city" name="city" index="1" placeholder="${city}" value="${sessionScope.userData.city}"/><span id="er1" class="error hidden"></span>
                    <input type="text" required id="cell" name="cell" index="2" placeholder="${cell}" value="${sessionScope.userData.cell}"/><span id="er2" class="error hidden"></span>
                    <b>Образование:</b>
                    <input type="text" required id="university" name="university" index="3" placeholder="${uni}" value="${sessionScope.userData.education.university}"/><span id="er3" class="error hidden"></span>
                    <input type="text" required id="department" name="department" index="4" placeholder="${dep}" value="${sessionScope.userData.education.department}"/><span id="er4" class="error hidden"></span>
                    <div>
                        <div class="half-width">
                            <input type="text" id="ed-form-input" required list="education-form" name="edForm" index="5" placeholder="${edform}"><span id="er5" class="error hidden"></span>
                            <datalist id="education-form">
                                <option value="FULL_TIME">
                                <option value="PART_TIME">
                            </datalist>
                        </div>
                        <div class="half-width">
                            <input type="text" required id="graduation-year" class="half-width" name="gradYear" index="6" placeholder="${gradyear}" value="${sessionScope.userData.education.graduationYear}"/><span id="er6" class="error hidden"></span>
                        </div>
                    </div>
                    <input type="text" required id="experience" name="experience" index="7" placeholder="${exp}" value="${sessionScope.userData.experience}"/><span id="er7" class="error hidden"></span>
                    <input type="text" required id="info" name="info" index="8" placeholder="${info}" value="${sessionScope.userData.info}"/><span id="er8" class="error hidden"></span>
                    <input class="button" id="save" type="submit" value="${save}"/>
                </form>
            </section>
        </article>
    </main>
</body>
</html>
<script>
    function checkValid(){
        var result = true;
        for (var name in editValidationMap){
            result = result && editValidationMap[name];
        }
        return result;
    }

    function checkAndSubmit(){
        if (checkValid()){
            document.getElementById("edit-form").submit();
        }
    }
    document.getElementById("save").onclick = checkAndSubmit;
</script>
<script type="text/javascript" src="../scripts/editPageValidation.js"></script>