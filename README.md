🚗 Estoque de Veículos — API Java Spring Boot

Sistema simples para gerenciamento de estoque de veículos, desenvolvido para o trabalho da disciplina Object Oriented Programming (UNIFECAF).
Inclui cadastro de marcas, cadastro de veículos, filtros e interface web básica.

📌 Funcionalidades

Cadastro de marcas

Cadastro de veículos

Listagem completa

Filtro por marca e status

Edição de veículos

Exclusão de veículos

Front-end simples (HTML + CSS + JS)

🛠 Tecnologias

Java 25

Spring Boot 3

Spring Data JPA

MySQL

Maven

HTML, CSS, JavaScript

▶️ Como rodar
1. Criar o banco no MySQL
CREATE DATABASE estoque;

2. Ajustar application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/estoque
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Rodar o projeto

No IntelliJ:

Run → EstoqueVeiculosApplication


Servidor em:

http://localhost:8080

🌐 Acessar o Front-End
http://localhost:8080/index.html


Funções disponíveis:

cadastrar marca

cadastrar veículo

filtrar

editar

remover

📡 Endpoints principais
Marca
Método	Rota	Função
GET	/marcas	listar
POST	/marcas	cadastrar
Veículo
Método	Rota	Função
GET	/veiculos	listar
POST	/veiculos	cadastrar
PUT	/veiculos/{id}	editar
DELETE	/veiculos/{id}	remover
GET	/veiculos

👨‍🎓 Autor

Gabriel Mascarenhas
UNIFECAF — ADS
