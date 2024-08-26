# Desafio Técnico Move

## Visão Geral
Este projeto é uma implementação de um sistema simples de transações financeiras entre usuários, desenvolvido como parte de um desafio técnico. O sistema utiliza Spring Boot, Docker, MySQL e RabbitMQ para fornecer funcionalidades de cadastro de usuários e realização de transações financeiras.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 2.6.3
- MySQL 8.0
- RabbitMQ 3
- Docker e Docker Compose
- Maven
- Flyway para migrações de banco de dados
- Swagger para documentação da API

## Pré-requisitos
- JDK 17
- Docker e Docker Compose
- Maven

## Configuração e Execução


### Configuração do Ambiente
1. Certifique-se de que o Docker está instalado e em execução em sua máquina.
2. Verifique se o JDK 17 está instalado e configurado corretamente.

### Executando a Aplicação
1. Build do projeto:
   ```bash
   mvn clean package -DskipTests
   ```

2. Iniciar os serviços com Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. A aplicação estará disponível em `http://localhost:8080`

### Acessando a Documentação da API
A documentação Swagger da API estará disponível em:
- http://localhost:8080/swagger-ui.html

## Estrutura do Projeto
- `src/main/java/com/rapaix/movE`: Código-fonte principal
    - `config`: Configurações do Spring e outros componentes
    - `controller`: Controladores REST
    - `dto`: Objetos de Transferência de Dados
    - `entity`: Entidades JPA
    - `exception`: Exceções personalizadas e manipulador global de exceções
    - `repository`: Interfaces de repositório Spring Data
    - `service`: Lógica de negócios
    - `messaging`: Componentes relacionados ao RabbitMQ
- `src/main/resources`:
    - `db/migration`: Scripts Flyway para migração do banco de dados
    - `application.properties`: Configurações da aplicação

## Funcionalidades Principais
1. Cadastro de Usuários
    - Endpoint para criar novos usuários
    - Endpoint para listar todos os usuários
2. Transações Financeiras
    - Realização de transações entre usuários via RabbitMQ
    - Endpoint para listar transações

## Testes
Para executar os testes:
```bash
mvn test
```

## Contribuindo
Contribuições são bem-vindas! Por favor, siga estas etapas:
1. Fork o projeto
2. Crie sua branch de feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença
Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.



