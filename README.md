# API de Gestão de Vagas — Spring Boot 4 + Supabase (PostgreSQL)

API RESTful (CRUD + paginação) em camadas: Controller → Service → Repository, com DTOs (records), MapStruct, Bean Validation, tratamento global de erros e Swagger UI. Banco de dados: **Supabase**. Os testes efetuados estarão no "Prints de teste mat5 LV.pdf"

## Integrantes

- Lorran Nascimento Dias
- Vinicius Pacheco Marques Ribeiro

## Requisitos
JDK 17+, Maven e um projeto no Supabase.

## 1. Preparar o Supabase
1. Crie um projeto em https://supabase.com e guarde a senha do banco.
2. Abra **SQL Editor** e execute o conteúdo de `setup.sql` (cria a tabela `bvagas`, ativa RLS e insere 20 vagas).
3. Clique em **Connect** no painel e copie os dados da **Session pooler** (host, porta 5432 e usuário `postgres.<project-ref>`).
   Use o pooler: a conexão direta do Supabase costuma ser só IPv6 e falha em muitas redes.

## 2. Configurar o `.env`
A aplicação lê as credenciais do banco de um arquivo `.env` na raiz do projeto (não há senha no código). Crie-o a partir do modelo:

```bash
cp .env.example .env      # Windows (PowerShell): Copy-Item .env.example .env
```

Preencha com os dados de **Connect > Session pooler** do Supabase:

```properties
SUPABASE_DB_URL=jdbc:postgresql://<host-do-pooler>:5432/postgres?sslmode=require
SUPABASE_DB_USER=postgres.<project-ref>
SUPABASE_DB_PASSWORD=<senha-do-banco>
```

- O `.env` está no `.gitignore` e **não deve ser commitado**. Só o `.env.example` (sem segredos) vai para o repositório.
- Rode a aplicação a partir da raiz do projeto (onde está o `.env`). Na IDE, confira que o *working directory* é a raiz.
- Variáveis de ambiente reais do sistema (`export`/`$env:`) têm prioridade sobre o `.env`.
- Se o `.env` não for encontrado, o erro aparece como `Driver org.postgresql.Driver claims to not accept jdbcUrl, ${SUPABASE_DB_URL}`. Isso significa que o diretório de trabalho está errado ou o arquivo não existe.
- Se der erro de conexão ("tenant or user not found"), confira o host em **Connect > Session pooler**. Projetos mais antigos usam `aws-0-sa-east-1` no lugar de `aws-1-sa-east-1`.

## 3. Rodar
```bash
mvn spring-boot:run
```
Swagger UI: http://localhost:8080/swagger-ui.html

## Endpoints
| Verbo | Rota | Descrição | Status |
|---|---|---|---|
| POST | /api/vagas | Criar vaga | 201 |
| GET | /api/vagas?page=0&size=5&sort=titulo,asc | Listar paginado | 200 |
| GET | /api/vagas/{id} | Buscar por UUID | 200 / 404 |
| PUT | /api/vagas/{id} | Atualizar | 200 / 404 |
| DELETE | /api/vagas/{id} | Remover | 204 / 404 |

Erros de validação, UUID inválido ou JSON malformado retornam 400 no formato `{timestamp, status, error, message, path}`.

## Testes (curl)
```bash
curl -X POST http://localhost:8080/api/vagas -H "Content-Type: application/json" \
  -d '{"titulo":"Desenvolvedor Java Sênior","descricao":"Spring Boot e Cloud","salario":14000.00}'

curl "http://localhost:8080/api/vagas?page=0&size=5&sort=salario,desc"

curl -X POST http://localhost:8080/api/vagas -H "Content-Type: application/json" \
  -d '{"titulo":"","salario":-500}'
```

## Observações
- Se algum dia a senha foi commitada ou compartilhada, troque-a no Supabase (Project Settings > Database).
- O `id` é `UUID` nativo do PostgreSQL (no MySQL do tutorial seria `BINARY(16)`).
