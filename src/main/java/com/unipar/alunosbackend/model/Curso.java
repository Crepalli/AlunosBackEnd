package com.unipar.alunosbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    private Long id;
    private String materia;
    private String descricao;

    public Curso() { }

    public Curso(Long id, String materia, String descricao) {
        this.id = id;
        this.materia = materia;
        this.descricao = descricao;
    }
}
