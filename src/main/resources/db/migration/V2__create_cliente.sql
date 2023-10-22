CREATE TABLE IF NOT EXISTS Cliente (
                                       id INTEGER PRIMARY KEY,
                                       cpf VARCHAR(14)  UNIQUE,
                                       email VARCHAR(255),
                                       senha VARCHAR(255)
);