create schema meucliente;

create table meucliente.cliente (
	cpf char(11) not null, 
	nome varchar(255), 
    cep char(8),
	logradouro varchar(120), 
	bairro varchar(120), 
	cidade varchar(120), 
    uf char(2),
	complemento varchar(255), 
    primary key (cpf)
);

create table meucliente.email (
	id SERIAL not null, 
	descricao varchar(255),
	cpf char(11) not null,
    primary key (id),
    foreign key (cpf) references meucliente.cliente(cpf)
);

create table meucliente.telefone (
	id SERIAL not null, 
	tipo char(1),
	numero varchar(9),
	cpf char(11) not null,
    primary key (id),
    foreign key (cpf) references meucliente.cliente(cpf)
);