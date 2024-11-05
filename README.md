# Projeto Meus Clientes: Back-End
Este é um projeto que exemplifica como trabalhar com "React + Spring Boot". A arquitetura deste foi montada conforme o seguinte esquema:

![Tela Inicial](FigArquitetura.png)

Ativar o Front acessar a aplicação em:
http://localhost:3000

Mais Detalhes do Front: https://github.com/fernandoans/meusclientes-front

## Ferramentas Utilizadas
Para esta parte foram utilizadas as seguintes tecnologias:
* Java - versão 21
* Sprint Tool Suite 4
* Spring Boot - versão 3.3.5
* SpringDoc - versão 2.1.0
* Lombok - versão 1.18.34
* Chamada ao serviço de CEP da viacep.com.br com o WebFlux
* Banco Postgres (detalhes abaixo)

Para a parte do front-end (MeusClientesFront) foram utilizadas as seguintes tecnologias:
* React.js 18.3.1
* Node.js 18.20.4
* npm 18.3.1
* Bootstrap 5.3.3
* Axios 1.7.7

## Compilar
Este projeto utiliza o Maven disponível no Spring Tool Suite 4 na versão 3.9.9, assim basta executar o processo:
`mvn clean install

Para ter o JAR executável do projeto, classes de teste unitário são executadas nesse processo, todas devem passar sem problemas indicando que o projeto está integro.
```
[INFO] Results:
[INFO] 
[INFO] Tests run: 29, Failures: 0, Errors: 0, Skipped: 0
```

Ativar o ambiente produtivo
`$ java jar -Dspring.profiles.active=prod meucliente-0.1.jar

Ativar o ambiente não produtivo
`$ java jar -Dspring.profiles.active=dev meucliente-0.1.jar

## Detalhes do Projeto
O primeiro serviço que deve ser chamado é o de login:
`http://localhost:8080/meusclientes/login/

Este retornará um TOKEN válido para acesso aos serviços do cliente, existem 2 usuários: padrão e admin, a senha deve ser passada com Base64. O usuário padrão só tem acesso a visualizar os dados enquanto que o admin pode manipulá-los.

Corpo para o usuário admin:
```
{
    "login": "admin",
    "senha": "MTIzcXdlIUAj"
}
```

E para o usuário padrão:
```
{
    "login": "padrão",
    "senha": "MTIzcXdlMTIz"
}
```

Os outros serviços do Cliente podem ser conhecidos acessando a documentaçã do Swagger. Ativar o BACK, e chamar o seguinte endereço:
http://localhost:8080/meusclientes/swagger-ui/index.html#/

![Tela Inicial](FigSwagger.png)

## Modelagem do Banco
Para o banco de dados foi utilizado o PostgreSQL, conforme o seguinte M&R (Modelo de Entidade e Relacionamento):

![M&E - Modelo de Entidade e Relacionamento](FigMER.png)

Conforme o seguinte Script de criação:

```
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
```


