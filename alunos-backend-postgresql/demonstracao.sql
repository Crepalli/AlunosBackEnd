
CREATE DATABASE alunos_db;

SELECT id, nome, email FROM alunos ORDER BY id;
SELECT * FROM cursos ORDER BY id;
SELECT a.id, a.nome, a.email, c.materia
FROM alunos a
LEFT JOIN aluno_curso ac ON ac.aluno_id = a.id
LEFT JOIN cursos c ON c.id = ac.curso_id
ORDER BY a.id, c.id;
