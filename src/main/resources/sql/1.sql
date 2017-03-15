CREATE TABLE Education(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  university VARCHAR(50) NOT NULL,
  department VARCHAR(50) NOT NULL,
  education_type VARCHAR(50) NOT NULL,
  graduation_date INT NOT NULL
);

CREATE TABLE Translator(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50),
  is_translator BOOL NOT NULL,
  city VARCHAR(50) NOT NULL,
  cell VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  education_id BIGINT NOT NULL,
  password VARCHAR(50) NOT NULL,
  experience VARCHAR(20) NOT NULL,
  info VARCHAR(250),
  isRemoved BOOL NOT NULL,


  FOREIGN KEY (education_id) REFERENCES Education(id),
  UNIQUE (id),
  UNIQUE (email)
);

CREATE TABLE Employer(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50),
  is_translator BOOL NOT NULL,
  city VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  firm VARCHAR(50),
  cell VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,

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
  'Просто Маша.',
  'qwerty',
  false
);