package foro_hub.alura_project_foro.controller;


import foro_hub.alura_project_foro.domain.respuesta.DatosDetalleRespuesta;
import foro_hub.alura_project_foro.domain.topico.DatosActualizarTopico;
import foro_hub.alura_project_foro.domain.topico.DatosDetalleTopico;
import foro_hub.alura_project_foro.domain.topico.DatosRegistroTopico;
import foro_hub.alura_project_foro.domain.topico.TopicoRepository;
import foro_hub.alura_project_foro.domain.topico.validaciones.ActualizarTopico;
import foro_hub.alura_project_foro.domain.topico.validaciones.RegistroTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RegistroTopico  registroTopico;

    @Autowired
    private ActualizarTopico actualizarTopico;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder){

        DatosDetalleTopico datosDetalleTopico = registroTopico.registrarTopico(datosRegistroTopico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosDetalleTopico.id()).toUri();
        return ResponseEntity.created(url).body(datosDetalleTopico);

    }


    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listarTopicos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new));
    }


    @GetMapping("/{id}")
    public ResponseEntity detallesTopico(@PathVariable Long id){

        var topico = topicoRepository.findById(id);

        if(topico.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El topico no existe");
        }

        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico.get());

        return ResponseEntity.ok(datosDetalleTopico);







    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico,
            @PathVariable Long id){

            DatosDetalleTopico datosDetalleTopico = actualizarTopico.actualizarTopico(id, datosActualizarTopico);
            return ResponseEntity.ok(datosDetalleTopico);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topico = topicoRepository.findById(id);

        if(topico.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El topico no existe");
        }

        topicoRepository.delete(topico.get());

        return  ResponseEntity.noContent().build();




    }



}
