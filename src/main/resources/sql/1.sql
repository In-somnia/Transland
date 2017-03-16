CREATE TABLE Education(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  university VARCHAR(50) NOT NULL,
  department VARCHAR(50) NOT NULL,
  education_type VARCHAR(20) NOT NULL,
  graduation_date INT NOT NULL,

  UNIQUE (id)
);

CREATE TABLE Translator(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  patronymic VARCHAR(20),
  is_translator BOOL NOT NULL,
  city VARCHAR(20) NOT NULL,
  cell VARCHAR(16) NOT NULL,
  email VARCHAR(30) NOT NULL,
  education_id BIGINT NOT NULL,
  password VARCHAR(30) NOT NULL,
  experience VARCHAR(20) NOT NULL,
  info VARCHAR(150),
  isRemoved BOOL NOT NULL,


  FOREIGN KEY (education_id) REFERENCES Education(id),
  UNIQUE (id),
  UNIQUE (email)
);

CREATE TABLE Employer(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  patronymic VARCHAR(20),
  is_translator BOOL NOT NULL,
  city VARCHAR(20) NOT NULL,
  cell VARCHAR(16) NOT NULL,
  email VARCHAR(30) NOT NULL,
  firm VARCHAR(30),
  password VARCHAR(30) NOT NULL,

  UNIQUE (id),
  UNIQUE (email)
);

INSERT INTO Education (university, department, education_type, graduation_date)
VALUES (
  'МГУ',
  'Социологии',
  'FULL_TIME',
  '2011'
);

INSERT INTO Translator (first_name, last_name, patronymic, is_translator, city, cell, email, education_id,
                        experience, info, password, isRemoved)
VALUES (
  'Мария',
  'Иванова',
  'Сергеевна',
  true,
  'Москва',
  '+7(951)614-32-97',
  'masha@mail.ru',
  '1',
  '4 года',
  'Перевод художественной и технической литературы. Владею Trados 2015. Возможны срочные переводы.',
  'qwerty',
  false
);