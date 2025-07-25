-- Limpa as tabelas na ordem inversa das dependências
DELETE FROM vaga;
DELETE FROM estudante_area_interesse;
DELETE FROM empresa_area_atuacao;
DELETE FROM estudante;
DELETE FROM empresa;
DELETE FROM usuario;
DELETE FROM area_interesse;

-- Inserção de Áreas de Interesse
INSERT INTO area_interesse (id, nome) VALUES (1, 'Desenvolvimento Web');
INSERT INTO area_interesse (id, nome) VALUES (2, 'Ciência de Dados');
INSERT INTO area_interesse (id, nome) VALUES (3, 'Inteligência Artificial');
INSERT INTO area_interesse (id, nome) VALUES (4, 'Engenharia de Software');
INSERT INTO area_interesse (id, nome) VALUES (5, 'Segurança da Informação');
INSERT INTO area_interesse (id, nome) VALUES (6, 'Design Gráfico');
INSERT INTO area_interesse (id, nome) VALUES (7, 'Redes de Computadores');
INSERT INTO area_interesse (id, nome) VALUES (8, 'Marketing Digital');
INSERT INTO area_interesse (id, nome) VALUES (9, 'Recursos Humanos');
INSERT INTO area_interesse (id, nome) VALUES (10, 'Administração');

-- Inserção de Usuários (Superclasse) com senhas encodadas
-- IMPORTANTE: Use o hash real gerado pelo seu BCryptPasswordEncoder para '123456'
-- Exemplo de hash (substitua por um gerado por você): $2a$10$YOUR_GENERATED_HASH_HERE
INSERT INTO usuario (id, nome, email, senha, perfil) VALUES (101, 'Admin Geral', 'admin@portal.com', '$2a$10$9VISI/JGGkrkt91uouSeOOy57GTC.UPgeH0Jg5HXfrhf0hYirfs2G', 'ADMIN');
INSERT INTO usuario (id, nome, email, senha, perfil) VALUES (102, 'João Estudante', 'joao@estudante.com', '$2a$10$9VISI/JGGkrkt91uouSeOOy57GTC.UPgeH0Jg5HXfrhf0hYirfs2G', 'ESTUDANTE');
INSERT INTO usuario (id, nome, email, senha, perfil) VALUES (103, 'Maria Estudante', 'maria@estudante.com', '$2a$10$9VISI/JGGkrkt91uouSeOOy57GTC.UPgeH0Jg5HXfrhf0hYirfs2G', 'ESTUDANTE');
INSERT INTO usuario (id, nome, email, senha, perfil) VALUES (104, 'Tech Solutions Ltda.', 'techsolutions@empresa.com', '$2a$10$9VISI/JGGkrkt91uouSeOOy57GTC.UPgeH0Jg5HXfrhf0hYirfs2G', 'EMPRESA');
INSERT INTO usuario (id, nome, email, senha, perfil) VALUES (105, 'Innovate Corp.', 'innovatecorp@empresa.com', '$2a$10$9VISI/JGGkrkt91uouSeOOy57GTC.UPgeH0Jg5HXfrhf0hYirfs2G', 'EMPRESA');
INSERT INTO usuario (id, nome, email, senha, perfil) VALUES (106, 'Pedro Estudante', 'pedro@estudante.com', '$2a$10$9VISI/JGGkrkt91uouSeOOy57GTC.UPgeH0Jg5HXfrhf0hYirfs2G', 'ESTUDANTE');


-- Inserção de Estudantes (Subclasse de Usuario)
INSERT INTO estudante (id, cpf, curso) VALUES (102, '111.111.111-11', 'Ciência da Computação');
INSERT INTO estudante (id, cpf, curso) VALUES (103, '222.222.222-22', 'Engenharia de Software');
INSERT INTO estudante (id, cpf, curso) VALUES (106, '333.333.333-33', 'Análise e Desenvolvimento de Sistemas');


-- Inserção de Empresas (Subclasse de Usuario)
INSERT INTO empresa (id, cnpj, telefone, endereco) VALUES (104, '00.000.000/0001-00', '11987654321', 'Rua Alfa, 123 - São Paulo');
INSERT INTO empresa (id, cnpj, telefone, endereco) VALUES (105, '99.999.999/0001-99', '21998765432', 'Av. Beta, 456 - Rio de Janeiro');


-- Relacionamento Estudante-AreaInteresse (Tabela de Junção)
INSERT INTO estudante_area_interesse (estudante_id, area_id) VALUES (102, 1); -- João: Desenvolvimento Web
INSERT INTO estudante_area_interesse (estudante_id, area_id) VALUES (102, 2); -- João: Ciência de Dados
INSERT INTO estudante_area_interesse (estudante_id, area_id) VALUES (103, 4); -- Maria: Engenharia de Software
INSERT INTO estudante_area_interesse (estudante_id, area_id) VALUES (103, 3); -- Maria: Inteligência Artificial
INSERT INTO estudante_area_interesse (estudante_id, area_id) VALUES (106, 1); -- Pedro: Desenvolvimento Web
INSERT INTO estudante_area_interesse (estudante_id, area_id) VALUES (106, 7); -- Pedro: Redes de Computadores


-- Relacionamento Empresa-AreaAtuacao (Tabela de Junção)
INSERT INTO empresa_area_atuacao (empresa_id, area_id) VALUES (104, 1); -- Tech Solutions: Desenvolvimento Web
INSERT INTO empresa_area_atuacao (empresa_id, area_id) VALUES (104, 4); -- Tech Solutions: Engenharia de Software
INSERT INTO empresa_area_atuacao (empresa_id, area_id) VALUES (105, 2); -- Innovate Corp.: Ciência de Dados
INSERT INTO empresa_area_atuacao (empresa_id, area_id) VALUES (105, 3); -- Innovate Corp.: Inteligência Artificial


-- Inserção de Vagas
INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (1, 'Estágio Desenvolvedor Front-end', 'Desenvolvimento de interfaces web com React.js.', 'São Paulo', 'REMOTO', 'ABERTA', '6 horas/dia', 'Conhecimento em HTML, CSS, JavaScript, React.js', 104, 1); -- Aberta, Desenvolvimento Web (Tech Solutions)

INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (2, 'Estágio Engenheiro de Software', 'Atuação em projetos de backend com Java e Spring Boot.', 'São Paulo', 'HIBRIDO', 'ABERTA', '6 horas/dia', 'Java, Spring Boot, SQL, Git', 104, 4); -- Aberta, Engenharia de Software (Tech Solutions)

INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (3, 'Estágio Analista de Dados', 'Análise de dados para insights de negócio com Python.', 'Rio de Janeiro', 'PRESENCIAL', 'ABERTA', '6 horas/dia', 'Python, SQL, Pandas, Matplotlib', 105, 2); -- Aberta, Ciência de Dados (Innovate Corp.)

INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (4, 'Estágio em IA/Machine Learning', 'Desenvolvimento e treinamento de modelos de Machine Learning.', 'Rio de Janeiro', 'REMOTO', 'ENCERRADA', '6 horas/dia', 'Python, TensorFlow/PyTorch, Estatística', 105, 3); -- Encerrada, Inteligência Artificial (Innovate Corp.)

INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (5, 'Estágio em Redes de Computadores', 'Suporte e manutenção de infraestrutura de redes.', 'Belo Horizonte', 'PRESENCIAL', 'ABERTA', '6 horas/dia', 'Conhecimento em TCP/IP, Roteadores, Switches', 104, 7); -- Aberta, Redes de Computadores (Tech Solutions)

INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (6, 'Estágio UX/UI Designer', 'Criação de wireframes e protótipos para aplicações.', 'São Paulo', 'REMOTO', 'ABERTA', '6 horas/dia', 'Figma, Adobe XD, princípios de UX/UI', 104, 6); -- Aberta, Design Gráfico (Tech Solutions)

INSERT INTO vaga (id, titulo, descricao, localizacao, modalidade, situacao, carga_horaria, requisitos, empresa_id, area_id)
VALUES (7, 'Estágio em Segurança Cibernética', 'Análise de vulnerabilidades e pentesting.', 'Curitiba', 'HIBRIDO', 'ABERTA', '6 horas/dia', 'Conhecimento em OWASP, ferramentas de segurança', 105, 5); -- Aberta, Segurança da Informação (Innovate Corp.)