drop database if exists poo;

create database poo;

use poo;

create table fornecedores (
    id int primary key auto_increment,
    nome varchar(64) not null,
    cnpj varchar(18) not null
);

create table fornecedor_contatos (
    id int primary key auto_increment,
    fornecedor_id int not null,
        constraint fornecedor_contatos_fornecedores_foreign foreign key(fornecedor_id) references fornecedores(id)
        on delete cascade on update cascade,
    tipo varchar(16) not null,
    contato varchar(64) not null
);

--
-- inserts
--

insert into fornecedores(nome, cnpj) values
    ('AUGUSTO A.', '08.778.473/0001-26'),
    ('BRUNO C.', '29.236.686/0001-04'),
    ('DIEGO J.', '90.173.646/0001-63');

insert into fornecedor_contatos(fornecedor_id, tipo, contato) values
    (1, 'CELULAR', '(34) 9 1111-2222'),
    (1, 'CELULAR', '(34) 9 2222-3333'),
    (1, 'CELULAR', '(34) 9 3333-4444'),
    (1, 'CELULAR', '(34) 9 4444-5555'),
    (1, 'CELULAR', '(34) 9 5555-6666'),

    (2, 'CELULAR', '(34) 9 6666-7777'),
    (2, 'CELULAR', '(34) 9 7777-8888'),
    (2, 'CELULAR', '(34) 9 8888-9999'),
    (2, 'CELULAR', '(34) 9 9999-0000'),

    (3, 'EMAIL', 'contato-abc@hotmail.com'),
    (3, 'EMAIL', 'contato-def@hotmail.com'),
    (3, 'EMAIL', 'contato-ghi@hotmail.com');

--
-- queries
--

SELECT 
    f.*, 
    fc.id AS contato_id, 
    fc.tipo AS contato_tipo, 
    fc.contato AS contato_conteudo 
FROM fornecedores AS f 
LEFT JOIN fornecedor_contatos AS fc ON fc.fornecedor_id = f.id;