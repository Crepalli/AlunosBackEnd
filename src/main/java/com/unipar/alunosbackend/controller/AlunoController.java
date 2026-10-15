package com.unipar.alunosbackend.controller;

import com.unipar.alunosbackend.model.Aluno;
import com.unipar.alunosbackend.model.Curso;
import com.unipar.alunosbackend.repository.AlunoRepository;
import com.unipar.alunosbackend.repository.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/aluno")
@Transactional
public class AlunoController {
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoController(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/filtrar")
    public List<Aluno> buscaFiltro(@RequestParam(required = false) String nome,
                                   @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String nomeCurso) {
        return alunoRepository.filtrar(normalizar(nome), normalizar(email), normalizar(nomeCurso));
    }

    private String normalizar(String valor) {
        return valor == null || valor.isBlank() ? "" : valor.toLowerCase(Locale.ROOT).replace(" ", "");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Aluno> listarAluno(@PathVariable Long id) {
        return ResponseEntity.ok(buscarAluno(id));
    }

    @PostMapping("/gravar")
    public ResponseEntity<String> gravarAluno(@RequestBody Aluno aluno) {
        aluno.setId(null);
        aluno.setCursos(buscarCursos(aluno.getCursos()));
        Aluno salvo = alunoRepository.save(aluno);
        return ResponseEntity.ok("Adicionado " + salvo.getNome());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Aluno> editarAluno(@PathVariable Long id,
                                            @RequestBody(required = false) Aluno aluno) {
        if (aluno == null) {
            return ResponseEntity.noContent().build();
        }
        Aluno existente = buscarAluno(id);
        existente.setNome(aluno.getNome());
        existente.setEmail(aluno.getEmail());
        existente.setCursos(buscarCursos(aluno.getCursos()));
        return ResponseEntity.ok(alunoRepository.save(existente));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAluno(@PathVariable Long id) {
        alunoRepository.delete(buscarAluno(id));
        return ResponseEntity.ok("Aluno " + id + " deletado");
    }

    private Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    private List<Curso> buscarCursos(List<Curso> recebidos) {
        List<Curso> cursos = new ArrayList<>();
        if (recebidos == null) {
            return cursos;
        }
        for (Curso recebido : recebidos) {
            if (recebido == null || recebido.getId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o ID de cada curso");
            }
            Curso curso = cursoRepository.findById(recebido.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso não encontrado"));
            if (!cursos.contains(curso)) {
                cursos.add(curso);
            }
        }
        return cursos;
    }
}
