<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit-page</title>
    <link rel="stylesheet" type="text/css" href="../css/editPage.css">
</head>
<body>
    <header><h1>Transland</h1></header>
    <main>
        <article class="profile">
            <section class="info">
                <div class="pic"></div>
                <h2>${sessionScope.userData.firstName} ${sessionScope.userData.patronymic} ${sessionScope.userData.lastName}</h2>
                <form class="form" id="edit-form" action="PageEditController" method="post">
                    <b>Контактная информация:</b>
                    <input type="text" required id="city" name="city" index="1" placeholder="Город:" value="${sessionScope.userData.city}"/><span id="er1" class="error hidden"></span>
                    <input type="text" required id="cell" name="cell" index="2" placeholder="Тел:" value="${sessionScope.userData.cell}"/><span id="er2" class="error hidden"></span>
                    <b>Образование:</b>
                    <input type="text" required id="university" name="university" index="3" placeholder="Университет:" value="${sessionScope.userData.education.university}"/><span id="er3" class="error hidden"></span>
                    <input type="text" required id="department" name="department" index="4" placeholder="Факультет:" value="${sessionScope.userData.education.department}"/><span id="er4" class="error hidden"></span>
                    <div>
                        <div class="half-width">
                            <input type="text" id="ed-form-input" required list="education-form" name="edForm" index="5" placeholder="Форма обучения:"><span id="er5" class="error hidden"></span>
                            <datalist id="education-form">
                                <option value="FULL_TIME">
                                <option value="PART_TIME">
                            </datalist>
                        </div>
                        <div class="half-width">
                            <input type="text" required id="graduation-year" class="half-width" name="gradYear" index="6" placeholder="Год выпуска:" value="${sessionScope.userData.education.graduationYear}"/><span id="er6" class="error hidden"></span>
                        </div>
                    </div>
                    <input type="text" required id="experience" name="experience" index="7" placeholder="Опыт:" value="${sessionScope.userData.experience}"/><span id="er7" class="error hidden"></span>
                    <input type="text" required id="info" name="info" index="8" placeholder="О себе:" value="${sessionScope.userData.info}"/><span id="er8" class="error hidden"></span>
                    <input class="button" id="save" type="submit" value="Сохранить"/>
                </form>
            </section>
        </article>
    </main>
</body>
</html>
<script>
    function checkValid(){
        var result = true;
        for (var name in editValidationMap){
            result = result && editValidationMap[name];
        }
        return result;
    }

    function checkAndSubmit(){
        if (checkValid()){
            document.getElementById("edit-form").submit();
        }
    }
    document.getElementById("save").onclick = checkAndSubmit;
</script>
<script type="text/javascript" src="../scripts/editPageValidation.js"></script>