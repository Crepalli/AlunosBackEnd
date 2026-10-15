# API de Alunos com PostgreSQL

Trabalho II de Desenvolvimento Backend — UNIPAR.

API REST para cadastro de alunos e vínculo com cursos, com persistência no PostgreSQL.

**Aluno:** João Sérgio Crepalli e Gustavo Laismann 
**RA:** 60007641 e 60009201 

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Configuração

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE alunos_db;
```

Configure a conexão em `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/alunos_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
spring.jpa.hibernate.ddl-auto=update
```

Ajuste a porta, o usuário e a senha conforme o ambiente. As variáveis `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` substituem os valores padrão quando definidas.

As tabelas são criadas na primeira inicialização. O arquivo `data.sql` cadastra os três cursos sem duplicá-los ao reiniciar.

## Execução

Na pasta que contém `pom.xml`, execute:

**Windows**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux e macOS**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

No IntelliJ, configure o JDK 17 e execute a classe `AlunosBackendApplication`.

URL base: `http://localhost:8080`.

## Rotas

| Método | Rota | Operação |
|---|---|---|
| POST | /aluno/gravar | Cadastrar aluno |
| GET | /aluno/listar | Listar alunos |
| GET | /aluno/listar/{id} | Consultar aluno por ID |
| PUT | /aluno/editar/{id} | Atualizar aluno |
| DELETE | /aluno/deletar/{id} | Excluir aluno |
| GET | /aluno/filtrar | Consultar com filtros |

O ID do aluno é gerado pelo banco. Utilize o ID retornado na listagem nas rotas de consulta, atualização e exclusão.

Evidências na EVT em anexo na atividade.

A coleção `Alunos_Postman.json` contém as requisições de cadastro, consulta, filtros, atualização e exclusão. Execute a exclusão após registrar as evidências de persistência.

## Estrutura

- `controller`: rotas REST e operações com os repositories.
- `model`: entidades Aluno e Curso.
- `repository`: acesso aos dados e consulta dos filtros.
- `application.properties`: configuração da aplicação e do banco.
- `data.sql`: cadastro inicial dos cursos.
