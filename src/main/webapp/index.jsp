<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Transland</title>
    <link rel="stylesheet" type="text/css" href="css/welcomePageStyle.css">
  </head>
  <body>
      <h1>Transland</h1>
      <div class="buttons">
          <form action="translatorRegistrationForm.jsp" method="post">
              <input class="button" id="translator" type="submit" value="Я - переводчик!"/>
          </form>
      </div>
      <div class="buttons">
          <form action="employerRegistrationForm.jsp" method="post">
              <input class="button" id="employer" type="submit" value="Я - работодатель!"/>
          </form>
      </div>
      <div class="enter-button">
          <form action="authorizationPage.jsp" method="post">
              <input class="button" id="enter" type="submit" value="Вход"/>
          </form>
      </div>
  </body>
</html>
