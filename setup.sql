-- Execute no Supabase: SQL Editor > New query > Run
-- Tabela usada pela API (o Hibernate também criaria com ddl-auto=update,
-- mas criando aqui já deixamos o RLS ativado).

CREATE TABLE IF NOT EXISTS public.bvagas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    salario DOUBLE PRECISION NOT NULL,
    data_criacao TIMESTAMP DEFAULT now()
);

-- Segurança: bloqueia acesso pela Data API pública do Supabase (chave anon).
-- A API Spring conecta direto via JDBC com o usuário do banco, que não é afetado.
ALTER TABLE public.bvagas ENABLE ROW LEVEL SECURITY;

-- 20 registros de teste
INSERT INTO public.bvagas (id, titulo, descricao, salario, data_criacao) VALUES
(gen_random_uuid(), 'Desenvolvedor Java Júnior', 'Desenvolvimento de APIs com Spring Boot e Java 17.', 4500.00, now()),
(gen_random_uuid(), 'Desenvolvedor Java Pleno', 'Experiência em arquitetura de microsserviços e Cloud.', 8500.00, now()),
(gen_random_uuid(), 'Desenvolvedor Java Sênior', 'Liderança técnica, Spring Boot, Kafka e AWS.', 14000.00, now()),
(gen_random_uuid(), 'Desenvolvedor Front-end React', 'React, Next.js e TypeScript.', 6000.00, now()),
(gen_random_uuid(), 'Desenvolvedor Back-end Node.js', 'APIs REST com Node.js, Express e PostgreSQL.', 7000.00, now()),
(gen_random_uuid(), 'Analista de Banco de Dados', 'Modelagem e otimização de dados em MySQL e PostgreSQL.', 7200.00, now()),
(gen_random_uuid(), 'Engenheiro de Dados', 'Pipelines ETL com Python e Spark.', 11000.00, now()),
(gen_random_uuid(), 'Analista de Qualidade (QA)', 'Testes automatizados com JUnit, Selenium e Cypress.', 5500.00, now()),
(gen_random_uuid(), 'DevOps Engineer', 'CI/CD, Docker, Kubernetes e Terraform.', 12000.00, now()),
(gen_random_uuid(), 'Analista de Suporte N1', 'Atendimento e suporte técnico a usuários.', 2800.00, now()),
(gen_random_uuid(), 'Analista de Suporte N2', 'Suporte avançado, redes e sistemas operacionais.', 3800.00, now()),
(gen_random_uuid(), 'Arquiteto de Software', 'Definição de arquitetura e boas práticas.', 18000.00, now()),
(gen_random_uuid(), 'Product Owner', 'Gestão de backlog e priorização com Scrum.', 9500.00, now()),
(gen_random_uuid(), 'Scrum Master', 'Facilitação de times ágeis.', 8000.00, now()),
(gen_random_uuid(), 'UX/UI Designer', 'Design de interfaces com Figma.', 6500.00, now()),
(gen_random_uuid(), 'Desenvolvedor Mobile Flutter', 'Aplicativos multiplataforma com Flutter.', 7500.00, now()),
(gen_random_uuid(), 'Analista de Segurança da Informação', 'Pentest, SIEM e resposta a incidentes.', 10000.00, now()),
(gen_random_uuid(), 'Cientista de Dados', 'Modelos de machine learning com Python.', 12500.00, now()),
(gen_random_uuid(), 'Estagiário de Desenvolvimento', 'Apoio ao time de desenvolvimento backend.', 1800.00, now()),
(gen_random_uuid(), 'Desenvolvedor Full Stack', 'Java no backend e React no frontend.', 9000.00, now());

-- Verificação
SELECT id, titulo, salario FROM public.bvagas ORDER BY titulo;
