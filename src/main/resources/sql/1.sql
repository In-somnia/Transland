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
  experience VARCHAR(20) NOT NULL,
  info VARCHAR(250),
  password VARCHAR(50) NOT NULL,

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