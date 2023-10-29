CREATE TABLE IF NOT EXISTS categoria (
                                         id INTEGER PRIMARY KEY,
                                         nome VARCHAR(100) NOT NULL
);

INSERT INTO categoria (id, nome) VALUES (1, 'Lanche');
INSERT INTO categoria (id, nome) VALUES (2, 'Acompanhamento');
INSERT INTO categoria (id, nome) VALUES (3, 'Bebida');
INSERT INTO categoria (id, nome) VALUES (4, 'Sobremesa');
