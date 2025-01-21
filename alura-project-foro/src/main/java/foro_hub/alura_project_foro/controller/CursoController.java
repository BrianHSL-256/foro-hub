package foro_hub.alura_project_foro.controller;


import foro_hub.alura_project_foro.domain.curso.Curso;
import foro_hub.alura_project_foro.domain.curso.CursoRepository;
import foro_hub.alura_project_foro.domain.curso.DatosDetalleCurso;
import foro_hub.alura_project_foro.domain.curso.DatosRegistroCurso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {


    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleCurso> registrarCurso(@RequestBody
                                                                @Valid
                                                                DatosRegistroCurso datosRegistroCurso,
                                                            UriComponentsBuilder uriComponentsBuilder){


        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));

        DatosDetalleCurso datosDetalleCurso = new DatosDetalleCurso(curso.getId(),
                curso.getNombre(),
                curso.getCategoria());



        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleCurso>> listarCursos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosDetalleCurso::new));
    }


}
