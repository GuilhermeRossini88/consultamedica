CREATE TABLE tbl_contato (
  ID SERIAL PRIMARY KEY,
  NOME VARCHAR(100) NOT NULL,
  DATA_NASCIMENTO DATE NOT NULL,
  EMAIL VARCHAR(100) NOT NULL,
  SENHA VARCHAR(100) NOT NULL
);