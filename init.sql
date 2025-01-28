USE my_database;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      password VARCHAR(255) NOT NULL
);

#  The password is encypted using BCryptPasswordEncoder
INSERT INTO user (name, password)
VALUES ('username', '$2a$10$.k1tqAwoibT6ZFpZME9Mj.xK47L.oPRe8LirXpYgeGQL60pmKUf4O');