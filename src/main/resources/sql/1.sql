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


