<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Transland</title>
    <link rel="stylesheet" type="text/css" href="css/welcomePageStyle.css">
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
  <fmt:setBundle basename="index" var="index"/>
  <fmt:message bundle="${index}" key="translator" var="translator"/>
  <fmt:message bundle="${index}" key="businessman" var="businessman"/>
  <fmt:message bundle="${index}" key="enter" var="enter"/>
  <fmt:message bundle="${index}" key="english" var="english"/>
  <fmt:message bundle="${index}" key="russian" var="russian"/>
      <h1>Transland</h1>
      <div class="buttons">
          <form action="translatorRegistrationForm.jsp" method="post">
              <input class="button" id="translator" type="submit" value="${translator}"/>
          </form>
      </div>
      <div class="buttons">
          <form action="" method="post">
              <input class="button" id="employer" type="submit" value="${businessman}"/>
          </form>
      </div>
      <div class="enter-button">
          <form action="authorizationPage.jsp" method="post">
              <input class="button" id="enter" type="submit" value="${enter}"/>
          </form>
      </div>
      <div class="localization">
          <form action="LocalizationController?language=en" method="post">
              <input class="button" id="english" type="submit" value="${english}"/>
          </form>
          <form action="LocalizationController?language=ru" method="post">
              <input class="button" id="russian" type="submit" value="${russian}"/>
          </form>
      </div>
  </body>
</html>
