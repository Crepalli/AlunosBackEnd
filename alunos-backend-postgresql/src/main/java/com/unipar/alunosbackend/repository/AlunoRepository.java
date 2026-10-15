package com.unipar.alunosbackend.repository;

import com.unipar.alunosbackend.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("""
        select a from Aluno a
        where (:nome = '' or locate(:nome, lower(replace(a.nome, ' ', ''))) > 0)
          and (:email = '' or locate(:email, lower(replace(a.email, ' ', ''))) > 0)
          and (:nomeCurso = '' or exists (
              select c.id from Aluno a2 join a2.cursos c
              where a2.id = a.id
                and locate(:nomeCurso, lower(replace(c.materia, ' ', ''))) > 0
          ))
        order by a.id
        """)
    List<Aluno> filtrar(@Param("nome") String nome,
                       @Param("email") String email,
                       @Param("nomeCurso") String nomeCurso);
}
