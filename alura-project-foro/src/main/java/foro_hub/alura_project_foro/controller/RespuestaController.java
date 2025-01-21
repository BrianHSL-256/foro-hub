package foro_hub.alura_project_foro.controller;

import foro_hub.alura_project_foro.domain.curso.DatosDetalleCurso;
import foro_hub.alura_project_foro.domain.respuesta.DatosDetalleRespuesta;
import foro_hub.alura_project_foro.domain.respuesta.DatosRegistroRespuesta;
import foro_hub.alura_project_foro.domain.respuesta.Respuesta;
import foro_hub.alura_project_foro.domain.respuesta.RespuestaRepository;
import foro_hub.alura_project_foro.domain.topico.Topico;
import foro_hub.alura_project_foro.domain.topico.TopicoRepository;
import foro_hub.alura_project_foro.domain.usuario.DatosDetallesUsuario;
import foro_hub.alura_project_foro.domain.usuario.Usuario;
import foro_hub.alura_project_foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
            UriComponentsBuilder uriComponentsBuilder
            ){


        Topico topico = topicoRepository.findById(datosRegistroRespuesta.idTopico())
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado"));

        Usuario usuario = usuarioRepository.findById(datosRegistroRespuesta.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Usuario No encontrado"));


        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta, topico, usuario));

        DatosDetalleRespuesta datosDetalleRespuesta = new DatosDetalleRespuesta(respuesta);

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleRespuesta);

    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestas(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosDetalleRespuesta::new));
    }








}
