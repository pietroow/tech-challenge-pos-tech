CREATE TABLE IF NOT EXISTS status_pedido (
                                         id INTEGER PRIMARY KEY,
                                         nome VARCHAR(100) NOT NULL
);

INSERT INTO status_pedido (id, nome) VALUES (1, 'Realizado');
INSERT INTO status_pedido (id, nome) VALUES (2, 'Pendente');
INSERT INTO status_pedido (id, nome) VALUES (3, 'Confirmado');
INSERT INTO status_pedido (id, nome) VALUES (4, 'Em preparação');
INSERT INTO status_pedido (id, nome) VALUES (5, 'Concluído');
INSERT INTO status_pedido (id, nome) VALUES (6, 'Cancelado');
