DROP DATABASE IF EXISTS newdata;
CREATE DATABASE newdata;
USE newdata;

CREATE TABLE Tasks (
    id INT(15) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    date DATE,
    state ENUM('complated', 'not_complated', 'abandoned') NOT NULL
);
