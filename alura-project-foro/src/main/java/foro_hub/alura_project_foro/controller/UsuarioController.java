package foro_hub.alura_project_foro.controller;


import foro_hub.alura_project_foro.domain.usuario.DatosDetallesUsuario;
import foro_hub.alura_project_foro.domain.usuario.DatosRegistroUsuario;
import foro_hub.alura_project_foro.domain.usuario.Usuario;
import foro_hub.alura_project_foro.domain.usuario.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallesUsuario> registrarUsuario(@RequestBody
                                                                 @Valid
                                                                 DatosRegistroUsuario datosRegistroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder){



        Usuario nuevoUsuario = new Usuario(datosRegistroUsuario);
        usuarioRepository.save(nuevoUsuario);


       DatosDetallesUsuario datosDetallesUsuario = new DatosDetallesUsuario(
               nuevoUsuario.getId(), nuevoUsuario.getNombre()
       );

       URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();

       return ResponseEntity.created(url).body(datosDetallesUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetallesUsuario>> listarUsuarios(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosDetallesUsuario::new));
    }



}
