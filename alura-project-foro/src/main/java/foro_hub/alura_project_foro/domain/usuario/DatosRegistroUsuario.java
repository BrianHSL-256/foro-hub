package foro_hub.alura_project_foro.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(@NotBlank
                                   String nombre,
                                   @NotBlank
                                   String correoElectronico,
                                   @NotBlank
                                   String contrasena) {
}
