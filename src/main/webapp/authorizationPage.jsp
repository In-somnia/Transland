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
            <input type="email" required id="j_login" name="email" index="1" placeholder="Введите Ваш регистрационный email:"><div class="error-message hidden" id="error1"></div>
            <label for="j_password">password:</label>
            <input type="password" required id="j_password" name="password" index="2" placeholder="Введите пароль:"><div class="error-message" id="error2"></div>
        </form>
    </div>
    <div class="button">
        <button class="button" id="enter-button" type="button">Войти</button>
    </div>
</body>
</html>
<script>

    var button = document.getElementById("enter-button");
    button.onclick = submitAuth;

    function submitAuth() {
        if (checkAuthValid())
        {
            document.getElementById("authorization-form").submit()
        }
    }

    function checkAuthValid(){
        var result = true;
        var map = checkAuthMap;
        for (var name in map){
            result = result && map[name];
        }
        return result;
    }
</script>
<script type="text/javascript" src="scripts/authValidation.js"></script>
