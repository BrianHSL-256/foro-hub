package foro_hub.alura_project_foro.domain.curso;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;


    public Curso(){}

    public Curso(@Valid DatosRegistroCurso datos){
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
