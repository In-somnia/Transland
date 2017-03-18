<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>authorization</title>
    <link rel="stylesheet" type="text/css" href="css/authorizationPageStyle.css">
</head>
<body>
<c:set var="loc" value="${sessionScope.locale}"/>
<c:if test="${loc == null}">
    <fmt:setBundle basename="index" var="index"/>
</c:if>
<c:if test="${loc != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="authorization" var="auth"/>
<fmt:message bundle="${auth}" key="login" var="login"/>
<fmt:message bundle="${auth}" key="placeholderlogin" var="pslogin"/>
<fmt:message bundle="${auth}" key="password" var="pass"/>
<fmt:message bundle="${auth}" key="placeholderpassword" var="pspass"/>
<fmt:message bundle="${auth}" key="authorization" var="authorization"/>


    <div class="auth-field">
        <form id="authorization-form" action="authorizationController" method="post">
            <label for="j_login">${login}</label>
            <input type="email" maxlength="30" required id="j_login" name="email" index="1" placeholder="${pslogin}"><div class="error-message hidden" id="error1"></div>
            <label for="j_password">${pass}</label>
            <input type="password" minlength="6" maxlength="30" required id="j_password" name="password" index="2" placeholder="${pspass}"><div class="error-message" id="error2"></div>
        </form>
    </div>
    <div class="button">
        <input class="button" type="submit" value="Зарегистрироваться" disabled="disabled" style="display: none"/>
        <button class="button" id="enter-button" type="button">${authorization}</button>
    </div>
</body>
</html>
<script>
    function checkAuthValid(){
        if (checkAuthMap["email"] == true && checkAuthMap["password"] == true) {
            return true;
        }
        return false;
    }

    function submitAuth() {

        if (checkAuthValid())
        {
            document.getElementById("authorization-form").submit();
        }
    }
    var button = document.getElementById("enter-button");
    button.onclick = submitAuth;

</script>
<script type="text/javascript" src="scripts/authValidation.js"></script>
