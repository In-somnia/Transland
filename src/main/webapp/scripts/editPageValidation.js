var editData = [
    {
        index: 1,
        id: "city",
        name: "city",
        pattern: /^[А-Я][а-я]+([\-][А-Я])?[а-я]{1,49}$/,
        errorMessage: "Кириллица, \"-\" и пробел не более 20 символов"
    },
    {
        index: 2,
        id: "cell",
        name: "cell",
        pattern: /\+[0-9]\([0-9]{3}\)[0-9]{3}-[0-9]{2}-[0-9]{2}/,
        errorMessage: "Цифры в формате: +х(ххх)ххх-хх-хх"

    },
    {
        index: 3,
        id: "university",
        name: "university",
        pattern: /^[А-Я][А-Яа-я0-9\s\\.]{2,49}$/,
        errorMessage: "Кириллица, \".\" и пробел не более 50 символов"

    },
    {
        index: 4,
        id: "department",
        name: "department",
        pattern: /^[А-Я][А-Яа-я0-9\s]{3,49}$/,
        errorMessage: "Кириллица и пробел не более 50 символов"

    },
    {
        index: 5,
        id: "graduation-year",
        name: "gradYear",
        pattern: /^[0-9]{4}$/,
        errorMessage: "Четыре цифры в диапазоне 1950-2021"
    },
    {
        index: 6,
        id: "experience",
        name: "experience",
        pattern: /^[А-Я0-9]?[А-Яа-я0-9\s]{1,19}$/,
        errorMessage: "Кириллица, цифры и пробел не более 20 символов"
    }
];
var editValidationMap = {
        city: false,
        cell: false,
        university: false,
        department: false,
        edForm: true,
        gradYear: false,
        experience: false,
        info: false
    };

document.getElementById("city").onchange = validateEditData;
document.getElementById("cell").onchange = validateEditData;
document.getElementById("university").onchange = validateEditData;
document.getElementById("department").onchange = validateEditData;
document.getElementById("graduation-year").onchange = validateEditData;
document.getElementById("experience").onchange = validateEditData;
document.getElementById("info").onchange = editInfoProtect;

function validateEditData(){

    var index = this.getAttribute("index");
    var value = this.value;
    var name = this.getAttribute("name");
    var errorSpan = document.getElementById("er" + index);

    this.value = (this.value.replace(/</g, "&lt;")).replace(/>/g, "&gt;");

    if (!checkEditPattern(index, value)){
        errorSpan.value = editData[index-1].errorMessage;
        this.style.border = "red 2px solid";
        errorSpan.classList.remove('hidden');
        errorSpan.textContent = editData[index-1].errorMessage;
        editValidationMap[name] = false;

    } else {
        this.style.border = "";
        editValidationMap[name] = true;
        errorSpan.classList.add("hidden");
        errorSpan.textContent = "";
    }
}

function checkEditPattern(index, value) {
    var pattern = editData[index-1].pattern;
    return pattern.test(value);

}
function editInfoProtect() {

    this.value = (this.value.replace(/</g, "&lt;")).replace(/>/g, "&gt;");
    var name = this.getAttribute("name");
    editValidationMap[name] = true;
}
