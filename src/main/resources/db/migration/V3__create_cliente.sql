CREATE TABLE IF NOT EXISTS Cliente (
                         id SERIAL PRIMARY KEY,
                         cpf VARCHAR(14) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL
);
