package foro_hub.alura_project_foro.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(@NotBlank
                                 String nombre,
                                 @NotBlank
                                 String categoria ) {
}
