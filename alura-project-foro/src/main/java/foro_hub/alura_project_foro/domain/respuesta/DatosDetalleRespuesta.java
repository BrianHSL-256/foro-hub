package foro_hub.alura_project_foro.domain.respuesta;

import foro_hub.alura_project_foro.domain.topico.DatosDetalleTopico;
import foro_hub.alura_project_foro.domain.usuario.DatosDetallesUsuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(Long id,
                                    LocalDateTime fechaCreacion,
                                    DatosDetalleTopico idTopico,
                                    DatosDetallesUsuario idUsuario,
                                    Boolean solucion
                                    ) {


    public DatosDetalleRespuesta(Respuesta respuesta){
        this(
          respuesta.getId(),
          respuesta.getFechaCreacion(),
          new DatosDetalleTopico(
                  respuesta.getTopico().getId(),
                  respuesta.getTopico().getTitulo(),
                  respuesta.getTopico().getMensaje(),
                  respuesta.getTopico().getFechaCreacion(),
                  respuesta.getTopico().getStatus(),

                  new DatosDetallesUsuario(
                          respuesta.getAutor().getId(),
                          respuesta.getAutor().getNombre()
                  ),
                  respuesta.getTopico().getCurso()
          ),
                new DatosDetallesUsuario(
                        respuesta.getAutor().getId(),
                        respuesta.getAutor().getNombre()
                ),
                respuesta.getSolucion()
        );
    }


}
