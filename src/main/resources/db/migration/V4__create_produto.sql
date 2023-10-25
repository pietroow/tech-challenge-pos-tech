CREATE TABLE IF NOT EXISTS produto (
                                       id INTEGER PRIMARY KEY,
                                       nome VARCHAR(100) NOT NULL,
                                       descricao VARCHAR(500),
                                       categoria VARCHAR(500),
                                       valor NUMERIC(10, 2) NOT NULL
);