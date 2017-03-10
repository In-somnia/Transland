<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
    <link rel="stylesheet" type="text/css" href="css/authorizationPageStyle.css">
</head>
<body>
    <div class="auth-field">
        <form action="authorizationController" method="post">
            <label for="j_login">login:</label>
            <input type="email" required id="j_login" name="email" placeholder="Введите Ваш регистрационный email:">
            <label for="j_password">password:</label>
            <input type="password" required id="j_password" name="password" placeholder="Введите пароль:">
        </form>
    </div>
</body>
</html>
