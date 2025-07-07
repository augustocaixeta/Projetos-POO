drop database if exists bd_chat_conversa;

create database bd_chat_conversa;

use bd_chat_conversa;

create table usuarios (
    id int primary key auto_increment,
    nome varchar(64) not null,
    senha varchar(64) not null,
    email varchar(128) not null,
    criado_em datetime not null default now()
);

create table contatos (
    id int primary key auto_increment,
    titular_id int not null,
        constraint contatos_contato_foreign foreign key(titular_id) references usuarios(id)
        on delete cascade on update cascade,
    contato_id int not null,
        constraint contatos_titular_foreign foreign key(contato_id) references usuarios(id)
        on delete cascade on update cascade,
    adicionado_em datetime not null default now()
);

create table conversas (
    id int primary key auto_increment,
    tipo varchar(16) not null,
    ultima_mensagem_em datetime not null,
    criada_em datetime not null default now()
);

create table participantes (
    conversa_id int not null,
        constraint part_conversa_foreign foreign key(conversa_id) references conversas(id)
        on delete cascade on update cascade,
    usuario_id int not null,
        constraint part_usuario_foreign foreign key(usuario_id) references usuarios(id)
        on delete cascade on update cascade,
    papel enum('Nenhum', 'Membro', 'Administrador') default 'Nenhum',
    entrou_em datetime not null default now(),
    primary key(conversa_id, usuario_id)
);

create table mensagens (
    id int primary key auto_increment,
    conversa_id int not null,
        constraint mensagens_conversas_foreign foreign key(conversa_id) references conversas(id)
        on delete no action on update no action,
    autor_id int not null,
        constraint mensagens_usuarios_foreign foreign key(autor_id) references usuarios(id)
        on delete no action on update no action,
    conteudo text not null,
    enviada_em datetime not null default now()
);

delimiter $$

create trigger criar_conversa_pessoal
after insert on usuarios
for each row
begin
    insert into conversas(tipo) VALUES ('Pessoal');
    insert into participantes(conversa_id, usuario_id) values (last_insert_id(), new.id);
end $$

delimiter ;

delimiter $$

create trigger atualizar_ultima_mensagem
after insert on mensagens
for each row
begin
    update conversas set ultima_mensagem_em = new.enviada_em where id = new.conversa_id;
end $$

delimiter ;

insert into usuarios(nome, senha, email) values
	('usuario_a', '123', 'email_a@email.com'), ('usuario_b', '123', 'email_b@email.com'),
    ('usuario_c', '123', 'email_c@email.com'), ('usuario_d', '123', 'email_d@email.com'),
    ('usuario_e', '123', 'email_e@email.com'), ('usuario_f', '123', 'email_f@email.com'),
	('usuario_g', '123', 'email_g@email.com'), ('usuario_h', '123', 'email_h@email.com'),
    ('usuario_i', '123', 'email_i@email.com'), ('usuario_j', '123', 'email_i@email.com');

insert into conversas(tipo) values
	('Individual'), -- 11
    ('Grupo'),      -- 12
    ('Grupo'),      -- 13
    ('Individual'); -- 14

insert into participantes(conversa_id, usuario_id, papel) values
	(12, 1, 'administrador'), -- conversa 2 usuario 1 (GRUPO)
    (12, 2, 'membro'), -- conversa 2 usuario 3 (GRUPO)
    (12, 3, 'membro'), -- conversa 2 usuario 3 (GRUPO)
    (12, 4, 'membro'), -- conversa 2 usuario 4 (GRUPO)
    (12, 5, 'membro'), -- conversa 2 usuario 3 (GRUPO)
    
    (13, 5, 'administrador'), -- conversa 3 usuario 5 (GRUPO)
    (13, 6, 'membro'), -- conversa 3 usuario 6 (GRUPO)
    (13, 7, 'membro'), -- conversa 3 usuario 7 (GRUPO)
    
    (11, 8, 'nenhum'), -- conversa 1 usuario 8 (INDIVIDUAL)
    (11, 9, 'nenhum'), -- conversa 1 usuario 9 (INDIVIDUAL)
    
    (14, 5, 'nenhum'), -- conversa 5 usuario 5 (INDIVIDUAL)
    (14, 9, 'nenhum'); -- conversa 5 usuario 9 (INDIVIDUAL)

insert into mensagens(conversa_id, autor_id, conteudo) values
	(12, 2, 'mensagem do usuario 2 para o grupo 1'),
    (12, 2, 'outra mensagem do usuario 2 para o grupo 1'),
    (12, 4, 'mensagem do usuario 4 para o grupo 1'),
    (12, 3, 'mensagem do usuario 3 para o grupo 1'),
    (12, 2, 'mensagem do usuario 2 para o grupo 1'),
    (12, 1, 'mensagem do usuario 1 para o grupo 1'),
    
    (13, 5, 'mensagem do usuario 6 para o grupo 2'),
    (13, 6, 'mensagem do usuario 6 para o grupo 2'),
    (13, 7, 'mensagem do usuario 7 para o grupo 2'),
    
    (11, 8, 'Bom dia, usuario 8'),
    (11, 9, 'Bom dia'),
    (11, 8, 'Apenas uma mensagem teste para o chat de conversa privada.');

SELECT
    c.*
FROM
    conversas AS c
JOIN
    participantes AS p ON p.conversa_id = c.id
WHERE
    p.usuario_id = ?
ORDER BY
    c.ultima_mensagem_em DESC,
    c.id;

SELECT
    u.*,
    c.apelido
FROM
    contatos AS c
JOIN
    usuarios AS u ON u.id = c.contato_id
WHERE
    c.titular_id = 9
ORDER BY
    u.nome;