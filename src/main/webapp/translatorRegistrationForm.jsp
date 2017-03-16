<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>translator-registration</title>
    <link rel="stylesheet" type="text/css" href="css/translatorRegistrationPage.css">
</head>
<script src=""></script>
    <body>
        <form id="registration-form" action="TranslatorRegistrationController" method="post">
            <div id="part1">
                <input type="text" maxlength="20" required id="first-name" name="firstName" index="1" placeholder="Имя:"><span id="er1" class="error hidden"></span>
                <input type="text" maxlength="20" required id="last-name" name="lastName" index="2" placeholder="Фамилия:"><span id="er2" class="error hidden"></span>
                <input type="text" maxlength="20" id="middle-name" name="middleName" index="3" placeholder="Отчество:"><span id="er3" class="error hidden"></span>
                <input type="text" maxlength="20" required id="city" name="city" index="4" placeholder="Город:"><span id="er4" class="error hidden"></span>
                <input type="tel" maxlength="16" required id="cell" name="cell" index="5" placeholder="Тел. в формате +х(ххх)ххх-хх-хх:"><span id="er5" class="error hidden"></span>
                <input type="email" maxlength="30" required id="email" name="email" index="6" placeholder="Эл. почта:"><span id="er6" class="error hidden"></span>
                <input type="text" maxlength="50" required id="university" name="university" index="7" placeholder="Университет:"><span id="er7" class="error hidden"></span>
                <input type="text" maxlength="50" id="department" name="department" index="8" placeholder="Факультет:"><span id="er8" class="error hidden"></span>
                <div class="half-width">
                    <input type="text" maxlength="20" id="ed-form-input" required list="education-form" name="edForm" index="9" placeholder="Форма обучения:"><span id="er9" class="error hidden"></span>
                    <datalist id="education-form">
                        <option value="FULL_TIME">
                        <option value="PART_TIME">
                    </datalist>
                </div>
                <div class="half-width">
                    <input type="text" maxlength="4" required id="graduation-year" name="gradYear" index="10" placeholder="Год выпуска:"><span id="er10" class="error hidden"></span>
                </div>
            </div>
            <div id="part2" class="hidden">
                <input type="text" maxlength="20" id="experience" name="experience" index="11" placeholder="Опыт (в годах):"><span id="er11" class="error hidden"></span>
                <textarea id="info" maxlength="150" name="info" placeholder="О себе:"></textarea>
                <input type="password" maxlength="30" minlength="6" required id="password" name="password" index="12" placeholder="Введите пароль:"><span id="er12" class="error hidden"></span>
            </div>
        </form>
        <div id = "button-part1" class="next-button">
            <button class="button" id="next" type="button">Далее</button>
        </div>
        <div id = "button-part2" class="hidden submit-button">
            <input class="button" type="submit" value="Зарегистрироваться" disabled="disabled" style="display: none"/>
            <button type="button" class="button" id="submit">Зарегистрироваться</button>
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