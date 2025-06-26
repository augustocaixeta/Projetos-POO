drop database if exists bd_chat_conversa;

create database bd_chat_conversa;

use bd_chat_conversa;

create table usuarios (
    id_usuario int primary key auto_increment,
    nome varchar(64) not null,
    email varchar(128) not null,
    data_criacao datetime not null default now()
);

create table contatos (
    id int primary key auto_increment,
    id_fk_titular int not null,
        constraint contatos_contato_foreign foreign key(id_fk_titular) references usuarios(id_usuario)
        on delete cascade on update cascade,
    id_fk_contato int not null,
        constraint contatos_titular_foreign foreign key(id_fk_contato) references usuarios(id_usuario)
        on delete cascade on update cascade,
    apelido varchar(64) not null,
    data_adicao datetime not null default now()
);

create table conversas (
    id_conversa int primary key auto_increment,
    tipo varchar(16) not null,
    data_criacao datetime not null default now()
);

create table salas (
	id_sala int primary key auto_increment,
    id_fk_usuario int not null,
        constraint salas_usuarios_foreign foreign key(id_fk_usuario) references usuarios(id_usuario)
        on delete cascade on update cascade,
    id_fk_conversa int not null,
        constraint salas_conversas_foreign foreign key(id_fk_conversa) references conversas(id_conversa)
        on delete cascade on update cascade,
    administrador boolean not null default false,
    saiu boolean not null default false,
    data_entrada datetime not null default now()
);

create table mensagens (
    id_mensagem int primary key auto_increment,
    id_fk_autor int not null,
        constraint mensagens_usuarios_foreign foreign key(id_fk_autor) references usuarios(id_usuario)
        on delete no action on update no action,
    id_fk_conversa int not null,
        constraint mensagens_conversas_foreign foreign key(id_fk_conversa) references conversas(id_conversa)
        on delete no action on update no action,
    conteudo longtext not null,
    data_envio datetime not null default now()
);

create table grupos (
    id_grupo int primary key auto_increment,
    nome varchar(64) not null,
    id_fk_criador int not null,
        constraint grupos_usuarios_foreign foreign key(id_fk_criador) references usuarios(id_usuario)
        on delete no action on update no action,
    id_fk_sala int not null,
        constraint grupos_salas_foreign foreign key(id_fk_sala) references salas(id_sala)
        on delete no action on update no action,
    data_criacao datetime not null default now()
);

insert into usuarios(nome, email) values
	('usuario_a', 'email_a@email.com'), ('usuario_b', 'email_b@email.com'),
    ('usuario_c', 'email_c@email.com'), ('usuario_d', 'email_d@email.com'),
    ('usuario_e', 'email_e@email.com'), ('usuario_f', 'email_f@email.com'),
	('usuario_g', 'email_g@email.com'), ('usuario_h', 'email_h@email.com'),
    ('usuario_i', 'email_i@email.com'), ('usuario_j', 'email_i@email.com');

insert into conversas(tipo) values
	('privada'), -- 1
    ('grupo'), -- 2
    ('grupo'), -- 3
    ('pessoal'); -- 4

insert into salas(id_fk_usuario, id_fk_conversa, administrador, saiu) values
	(1, 2, true , false), -- grupo conversa 2
    (2, 2, false, false), -- grupo conversa 2
    (3, 2, false, false), -- grupo conversa 2
    (4, 2, false, false), -- grupo conversa 2
    
    (5, 3, true , false), -- grupo conversa 3
    (6, 3, false, false), -- grupo conversa 3
    (7, 3, false, false), -- grupo conversa 3
    
    (8, 1, false, false), -- privada conversa 1
    (9, 1, false, false), -- privada conversa 1
	
    (10, 4, false, false);

insert into grupos(nome, id_fk_criador, id_fk_sala) values
	('Grupo um', 1, 1),
    ('Grupo dois', 5, 2);

insert into mensagens(id_fk_autor, id_fk_conversa, conteudo) values
	(2, 2, 'mensagem do usuario 2 para o grupo 1'),
    (2, 2, 'outra mensagem do usuario 2 para o grupo 1'),
    (4, 2, 'mensagem do usuario 4 para o grupo 1'),
    (3, 2, 'mensagem do usuario 3 para o grupo 1'),
    (2, 2, 'mensagem do usuario 2 para o grupo 1'),
    (1, 2, 'mensagem do usuario 1 para o grupo 1'),
    
    (5, 3, 'mensagem do usuario 6 para o grupo 2'),
    (6, 3, 'mensagem do usuario 6 para o grupo 2'),
    (7, 3, 'mensagem do usuario 7 para o grupo 2'),
    
    (8, 1, 'Bom dia, usuario 8'),
    (9, 1, 'Bom dia'),
    (8, 1, 'Apenas uma mensagem teste para o chat de conversa privada.');

SELECT 
        m.id_mensagem, m.conteudo, m.data_envio, 
        u.id_usuario, u.nome, u.email, u.data_criacao, 
        c.id_conversa, c.tipo, c.data_criacao AS data_conversa 
		FROM grupos AS g 
		JOIN salas AS s ON s.id_sala = g.id_fk_sala 
        JOIN conversas AS c ON c.id_conversa = s.id_fk_conversa 
        JOIN mensagens AS m ON m.id_fk_conversa = c.id_conversa 
        JOIN usuarios AS u ON u.id_usuario = m.id_fk_autor 
        WHERE g.id_grupo = 1 
        ORDER BY m.data_envio;