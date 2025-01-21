package foro_hub.alura_project_foro.domain.topico;

import foro_hub.alura_project_foro.domain.curso.Curso;
import foro_hub.alura_project_foro.domain.respuesta.Respuesta;
import foro_hub.alura_project_foro.domain.usuario.DatosDetallesUsuario;
import foro_hub.alura_project_foro.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetalleTopico(Long id,
                                 String titulo,
                                 String mensaje,
                                 LocalDateTime fechaCreacion,
                                 String status,
                                 DatosDetallesUsuario autor,
                                 Curso curso

                                   ) {

    public DatosDetalleTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new DatosDetallesUsuario(
                        topico.getAutor().getId(),
                        topico.getAutor().getNombre()
                ),
                topico.getCurso()

        );



    }

}
