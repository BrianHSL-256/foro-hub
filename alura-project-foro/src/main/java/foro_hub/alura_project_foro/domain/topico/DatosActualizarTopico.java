package foro_hub.alura_project_foro.domain.topico;

import foro_hub.alura_project_foro.domain.curso.Curso;
import foro_hub.alura_project_foro.domain.respuesta.Respuesta;
import foro_hub.alura_project_foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosActualizarTopico(
                                    @NotBlank
                                    String titulo,
                                    @NotBlank
                                    String mensaje,
                                    @NotBlank
                                    String status


) {
}
