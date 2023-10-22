CREATE TABLE IF NOT EXISTS Cliente (
                                       id INTEGER PRIMARY KEY,
                                       name VARCHAR(255),
                                       cpf VARCHAR(14)  UNIQUE,
                                       email VARCHAR(255),
                                       senha VARCHAR(255),
                                       status CHAR(1) NOT NULL
);