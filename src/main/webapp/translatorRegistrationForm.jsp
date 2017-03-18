<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>translator-registration</title>
    <link rel="stylesheet" type="text/css" href="css/translatorRegistrationPage.css">
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
    <fmt:setBundle basename="translatorRegistration" var="reg"/>
    <fmt:message bundle="${reg}" key="fnameplaceholder" var="fname"/>
    <fmt:message bundle="${reg}" key="lnameplaceholder" var="lname"/>
    <fmt:message bundle="${reg}" key="mnameplaceholder" var="mname"/>
    <fmt:message bundle="${reg}" key="cityplaceholder" var="city"/>
    <fmt:message bundle="${reg}" key="cellplaceholder" var="cell"/>
    <fmt:message bundle="${reg}" key="emailplaceholder" var="email"/>
    <fmt:message bundle="${reg}" key="uniplaceholder" var="uni"/>
    <fmt:message bundle="${reg}" key="departmentplaceholder" var="department"/>
    <fmt:message bundle="${reg}" key="edformplaceholder" var="edform"/>
    <fmt:message bundle="${reg}" key="gradyearplaceholder" var="gradyear"/>
    <fmt:message bundle="${reg}" key="expplaceholder" var="exp"/>
    <fmt:message bundle="${reg}" key="infoplaceholder" var="info"/>
    <fmt:message bundle="${reg}" key="passplaceholder" var="pass"/>
    <fmt:message bundle="${reg}" key="nextbutton" var="next"/>
    <fmt:message bundle="${reg}" key="submitbutton" var="submit"/>

        <form id="registration-form" action="TranslatorRegistrationController" method="post">
            <div id="part1">
                <input type="text" maxlength="20" required id="first-name" name="firstName" index="1" placeholder="${fname}"><span id="er1" class="error hidden"></span>
                <input type="text" maxlength="20" required id="last-name" name="lastName" index="2" placeholder="${lname}"><span id="er2" class="error hidden"></span>
                <input type="text" maxlength="20" id="middle-name" name="middleName" index="3" placeholder="${mname}"><span id="er3" class="error hidden"></span>
                <input type="text" maxlength="20" required id="city" name="city" index="4" placeholder="${city}"><span id="er4" class="error hidden"></span>
                <input type="tel" maxlength="16" required id="cell" name="cell" index="5" placeholder="${cell}"><span id="er5" class="error hidden"></span>
                <input type="email" maxlength="30" required id="email" name="email" index="6" placeholder="${email}"><span id="er6" class="error hidden"></span>
                <input type="text" maxlength="50" required id="university" name="university" index="7" placeholder="${uni}"><span id="er7" class="error hidden"></span>
                <input type="text" maxlength="50" id="department" name="department" index="8" placeholder="${department}"><span id="er8" class="error hidden"></span>
                <div class="half-width">
                    <input type="text" maxlength="20" id="ed-form-input" required list="education-form" name="edForm" index="9" placeholder="${edform}"><span id="er9" class="error hidden"></span>
                    <datalist id="education-form">
                        <option value="FULL_TIME">
                        <option value="PART_TIME">
                    </datalist>
                </div>
                <div class="half-width">
                    <input type="text" maxlength="4" required id="graduation-year" name="gradYear" index="10" placeholder="${gradyear}"><span id="er10" class="error hidden"></span>
                </div>
            </div>
            <div id="part2" class="hidden">
                <input type="text" maxlength="20" id="experience" name="experience" index="11" placeholder="${exp}"><span id="er11" class="error hidden"></span>
                <textarea id="info" maxlength="150" name="info" placeholder="${info}"></textarea>
                <input type="password" maxlength="30" minlength="6" required id="password" name="password" index="12" placeholder="${pass}"><span id="er12" class="error hidden"></span>
            </div>
        </form>
        <div id = "button-part1" class="next-button">
            <button class="button" id="next" type="button">${next}</button>
        </div>
        <div id = "button-part2" class="hidden submit-button">
            <input class="button" type="submit" value="Зарегистрироваться" disabled="disabled" style="display: none"/>
            <button type="button" class="button" id="submit">${submit}</button>
        </div>
    </body>
</html>
<script>
    var currentView = 1;
    var nextBtn = document.getElementById("next");

    function nextView() {
        var view1 = document.getElementById("part1");
        var view2 = document.getElementById("part2");
        var nextButton = document.getElementById("button-part1");
        var submitButton = document.getElementById("button-part2");
        if (checkValid()){
            view1.classList.add("hidden");
            view2.classList.remove("hidden");
            nextButton.classList.add("hidden");
            submitButton.classList.remove("hidden");
            currentView = 2;
        } else {
            alert("something went wrong");
        }
    }
    function checkValid(){
        var result = true;
        var view = validationMap[currentView];
        for (var v in view){
            result = result && view[v];
        }
        return result;
    }

    nextBtn.onclick = nextView;

    function checkAndSubmit(){
        if (checkValid()){
            document.getElementById("registration-form").submit();
        }
    }
    document.getElementById("submit").onclick = checkAndSubmit;
</script>
<script type="text/javascript" src="scripts/regValidation.js"></script>