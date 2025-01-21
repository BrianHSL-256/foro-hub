package foro_hub.alura_project_foro.domain.topico;

import foro_hub.alura_project_foro.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;



public record DatosRegistroTopico(

                                  @NotBlank
                                  String titulo,

                                  @NotBlank
                                  String mensaje,

                                  @NotNull
                                  Long autor,
                                  @NotNull
                                  Long curso

                                  ){




}
