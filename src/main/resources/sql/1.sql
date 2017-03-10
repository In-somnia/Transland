CREATE TABLE Education(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR(50) NOT NULL ,
  city VARCHAR(100) NOT NULL,
  university VARCHAR(50) NOT NULL,
  department VARCHAR(50) NOT NULL,
  education_type BOOL NOT NULL,
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
  password VARCHAR(255) NOT NULL,
  education_id BIGINT,
  experience VARCHAR(20) NOT NULL,
  topics VARCHAR(200),
  info VARCHAR(100),

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