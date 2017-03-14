var authData = [
    {
        index: 1,
        id: "j_login",
        name: "email",
        pattern: /^[-_A-Za-z0-9+]+([.]?[-_A-Za-z0-9]+)*@[-A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/,
        errMessage: "Латиница, цифры, \"_\", \"-\" и \".\" в формате: example@gmail.com"
    },
    {
        index: 2,
        id: "j_password",
        name: "password",
        pattern: /[A-za-z0-9]{6,50}/,
        errMessage: "Латиница, цифры не менее 6 символов"
    }
];

var checkAuthMap = {
    email: false,
    password: false
};

document.getElementById("j_login").onchange = validateAuth;
document.getElementById("j_password").onchange = validateAuth;

function validateAuth() {
    var index = this.getAttribute("index");
    var value = this.value;
    var name = this.getAttribute("name");
    var error = document.getElementById("error" + index);

    this.value = (this.value.replace(/</g, "&lt;")).replace(/>/g, "&gt;");

    if (!checkPattern(index, value)){
        error.value = authData[index-1].errMessage;
        this.style.border = "red 2px solid";
        error.classList.remove('hidden');
        error.textContent = authData[index-1].errMessage;
        checkAuthMap[name] = false;

    } else {
        this.style.border = "";
        checkAuthMap[name] = true;
        error.classList.add("hidden");
        error.textContent = "";
    }
}

function checkPattern(index, value) {
    var pattern = authData[index-1].pattern;
    if (pattern.test(value)) {
        return true;
    }
    return false;
}



