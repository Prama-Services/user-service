
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id IDENTITY NOT NULL PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(255) NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(30) NOT NULL
);
