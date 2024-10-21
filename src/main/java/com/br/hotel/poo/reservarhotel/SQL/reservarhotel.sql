CREATE DATABASE IF NOT EXISTS reservahotel;

USE reservahotel;

CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destino VARCHAR(255) NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    adultos INT NOT NULL,
    criancas INT NOT NULL,
    quartos INT NOT NULL
);

CREATE TABLE hospedes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    reservas INT,
    hotel VARCHAR(255)
);



CREATE TABLE hoteis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    estrelas INT NOT NULL,
    destino VARCHAR(255) NOT NULL
);

CREATE TABLE hospedes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    reservas INT,
    hotel VARCHAR(255)
);


CREATE TABLE estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sigla VARCHAR(2) NOT NULL
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO hospedes (nome, email, check_in, reservas, hotel)
VALUES ('Vinicius Samuel', 'vini21@gmail.com', '2024-10-20', 1, 'Montanha Mística');


INSERT INTO hoteis (nome, preco, estrelas, destino) VALUES
('Castelo dos Sonhos', 350.00, 5, 'Gramado'),
('Paraíso Tropical', 280.00, 4, 'Rio de Janeiro'),
('Montanha Mística', 320.00, 5, 'São Paulo'),
('Praia Azul Resort', 400.00, 4, 'Bahia');


INSERT INTO estados (nome, sigla) VALUES
('Acre', 'AC'),
('Alagoas', 'AL'),
('Amapá', 'AP'),
('Amazonas', 'AM'),
('Bahia', 'BA'),
('Ceará', 'CE'),
('Distrito Federal', 'DF'),
('Espírito Santo', 'ES'),
('Goiás', 'GO'),
('Maranhão', 'MA'),
('Mato Grosso', 'MT'),
('Mato Grosso do Sul', 'MS'),
('Minas Gerais', 'MG'),
('Pará', 'PA'),
('Paraíba', 'PB'),
('Paraná', 'PR'),
('Pernambuco', 'PE'),
('Piauí', 'PI'),
('Rio de Janeiro', 'RJ'),
('Rio Grande do Norte', 'RN'),
('Rio Grande do Sul', 'RS'),
('Rondônia', 'RO'),
('Roraima', 'RR'),
('São Paulo', 'SP'),
('Sergipe', 'SE'),
('Tocantins', 'TO');


SELECT * FROM usuarios;
SELECT * FROM reservas;
SELECT * FROM hoteis;
SELECT * FROM hospedes;
