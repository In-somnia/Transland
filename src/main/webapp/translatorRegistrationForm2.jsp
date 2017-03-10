<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>translator-registration-next</title>
    <link rel="stylesheet" type="text/css" href="css/translatorRegistrationPage2.css">
</head>
<body>
<form id="registration-form" action="TranslatorRegistrationController" method="post">
    <input type="text" id="experience" name="experience" placeholder="Years of experience">
    <textarea placeholder="Translation spheres:"></textarea>
    <textarea placeholder="About yourself:"></textarea>
    <input type="password" required id="password" name="password" placeholder="Введите пароль:">
    <input type="password" required id="password-review" name="password" placeholder="Повторите пароль:">
</form>
<div class="submit-button">
    <form action="translatorRegistrationForm2.jsp" method="post">
        <input class="button" id="next" type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
