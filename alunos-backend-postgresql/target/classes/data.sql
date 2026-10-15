INSERT INTO cursos (id, materia, descricao) VALUES
(1, 'Desenvolvimento Backend', 'Java, Springboot, Hibernate'),
(2, 'Desenvolvimento Frontend', 'Angular, Typescript, SPA'),
(3, 'Banco de Dados', 'Modelagem e consultas SQL')
ON CONFLICT (id) DO NOTHING;
