CREATE TABLE IF NOT EXISTS produto (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         categoria_id INTEGER,
                         valor NUMERIC(10, 2) NOT NULL,
                         FOREIGN KEY (categoria_id) REFERENCES Categoria (id)
);
