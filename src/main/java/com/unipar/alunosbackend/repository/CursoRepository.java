package com.unipar.alunosbackend.repository;
import com.unipar.alunosbackend.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
