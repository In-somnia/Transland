<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
    <link rel="stylesheet" type="text/css" href="css/authorizationPageStyle.css">
</head>
<body>
    <div class="auth-field">
        <form id="authorization-form" action="authorizationController" method="post">
            <label for="j_login">login:</label>
            <input type="email" required id="j_login" name="email" placeholder="Введите Ваш регистрационный email:">
            <label for="j_password">password:</label>
            <input type="password" required id="j_password" name="password" placeholder="Введите пароль:">
        </form>
    </div>
    <div class="button">
        <button class="button" id="enter-button" type="button">Войти</button>
    </div>
</body>
</html>
<script>
    function submitAuth() {
        document.getElementById("authorization-form").submit()
    }
    var button = document.getElementById("enter-button");
    button.onclick = submitAuth;
</script>
<script type="text/javascript" src="scripts/authValidation.js"></script>
