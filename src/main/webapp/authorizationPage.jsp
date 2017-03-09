<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
    <link rel="stylesheet" type="text/css" href="css/authorizationPageStyle.css">
</head>
<body>
    <div class="auth-field">
        <form action="authorizationController" method="post">
            <label for="email">login:</label>
            <input type="email" required id="email" name="email" placeholder="Введите Ваш регистрационный email:">
            <label for="password">password:</label>
            <input type="password" required id="password" name="password" placeholder="Введите пароль:">
        </form>
    </div>
</body>
</html>
