var valiData = [
    {
        index: 1,
        id: "first-name",
        name: "firstName",
        pattern: /^[А-Я][а-я]{1,49}$/,
        page: 1,
        errorMessage: "Кириллица не более 50 символов"

    },
    {
        index: 2,
        id: "last-name",
        name: "lastName",
        pattern: /^[А-Я][а-я]{1,49}$/,
        page: 1,
        errorMessage: "Кириллица не более 50 символов"
    },
    {
        index: 3,
        id: "middle-name",
        name: "middleName",
        pattern: /^[А-Я][а-я]{1,49}$/,
        page: 1,
        errorMessage: "Кириллица не более 50 символов"
    },
    {
        index: 4,
        id: "city",
        name: "city",
        pattern: /^[А-Я][а-я]+([\-][А-Я])?[а-я]{1,49}$/,
        page: 1,
        errorMessage: "Кириллица, \"-\" и пробел не более 50 символов"
    },
    {
        index: 5,
        id: "cell",
        name: "cell",
        pattern: /\+[0-9]\([0-9]{3}\)[0-9]{3}-[0-9]{2}-[0-9]{2}/,
        page: 1,
        errorMessage: "Цифры в формате: +х(ххх)ххх-хх-хх"

    },
    {
        index: 6,
        id: "email",
        name: "email",
        pattern: /^[-_A-Za-z0-9+]+([.]?[-_A-Za-z0-9]+)*@[-A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/,
        page: 1,
        errorMessage: "Латиница, цифры, \"_\", \"-\" и \".\" в формате: example@gmail.com"

    },
    {
        index: 7,
        id: "university",
        name: "university",
        pattern: /^[А-Я][А-Яа-я0-9\s\\.]{2,49}$/,
        page: 1,
        errorMessage: "Кириллица, \".\" и пробел не более 50 символов"

    },
    {
        index: 8,
        id: "department",
        name: "department",
        pattern: /^[А-Я][А-Яа-я0-9\s]{3,49}$/,
        page: 1,
        errorMessage: "Кириллица и пробел не более 50 символов"

    },
    {
        index: 9,
        id: "education-form",
        name: "edForm",
        pattern: "",
        page: 1,
        errorMessage: "Поле не должно быть пустым"
    },
    {
        index: 10,
        id: "graduation-year",
        name: "gradYear",
        pattern: /^[0-9]{4}$/,
        page: 1,
        errorMessage: "Четыре цифры в диапазоне 1950-2021"
    },
    {
        index: 11,
        id: "experience",
        name: "experience",
        pattern: /^[А-Я0-9]?[А-Яа-я0-9\s]{1,19}$/,
        page: 2,
        errorMessage: "Кириллица, цифры и пробел не более 20 символов"
    },
    {
        index: 12,
        id: "password",
        name: "password",
        pattern: /[A-za-z0-9]{6,50}/,
        page: 2,
        errorMessage: "Латиница, цифры не менее 6 символов"
    }
];