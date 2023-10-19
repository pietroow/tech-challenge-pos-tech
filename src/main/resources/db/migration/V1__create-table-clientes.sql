CREATE TABLE Cliente (
                         id SERIAL PRIMARY KEY,
                         cpf VARCHAR(14) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL
);