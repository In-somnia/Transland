<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>translator-registration</title>
    <link rel="stylesheet" type="text/css" href="css/translatorRegistrationPage.css">
</head>
    <body>
        <form id="registration-form" action="TranslatorRegistrationController" method="post">
            <input type="text" required id="first-name" placeholder="First name:">
            <input type="text" required id="last-name" placeholder="Last name:">
            <input type="text" id="patronymic" placeholder="Middle name:">
            <input type="text" required id="city" placeholder="City:">
            <input type="tel" required id="cell" placeholder="Cell:">
            <input type="email" required id="email" placeholder="E-mail:">
            <input type="text" required id="university" placeholder="University:">
            <input type="text" required id="department" placeholder="Department:">
            <div class="half-width">
                <input type="text" required list="education-form" name="education-form" placeholder="Education-form:">
                <datalist id="education-form">
                    <option value="full-time">
                    <option value="part-time">
                </datalist>
            </div>
            <div class="half-width">
                <input type="text" required id="graduation-year" name="graduation-year" placeholder="Graduation year:">
            </div>
        </form>
        <div class="next-button">
            <form action="translatorRegistrationForm2.jsp" method="post">
                <input class="button" id="next" type="submit" value="Next"/>
            </form>
        </div>
    </body>
</html>
