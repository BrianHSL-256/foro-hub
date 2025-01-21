package foro_hub.alura_project_foro.domain.respuesta;

import foro_hub.alura_project_foro.domain.topico.Topico;
import foro_hub.alura_project_foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;





@Table(name = "respuestas")
@Entity(name = "Respuesta")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")



public class Respuesta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;



    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;



    private Boolean solucion;


    public Respuesta(){}

    public Respuesta(DatosRegistroRespuesta datos, Topico datosTopico, Usuario datosUsuario){

        this.mensaje = datos.mensaje();
        this.topico = datosTopico;
        this.autor = datosUsuario;

        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;




    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Boolean getSolucion() {
        return solucion;
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }
}
