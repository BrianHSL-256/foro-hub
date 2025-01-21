package foro_hub.alura_project_foro.domain.topico.validaciones;


import foro_hub.alura_project_foro.domain.curso.CursoRepository;
import foro_hub.alura_project_foro.domain.topico.DatosActualizarTopico;
import foro_hub.alura_project_foro.domain.topico.DatosDetalleTopico;
import foro_hub.alura_project_foro.domain.topico.TopicoRepository;
import foro_hub.alura_project_foro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizarTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizarTopico){

        var topico = topicoRepository.findById(id)
                .orElseThrow(()->{
                    return new RuntimeException("Topico no encontrado ");
                });

        var titulo = datosActualizarTopico.titulo();
        var mensaje = datosActualizarTopico.mensaje();

        if(topicoRepository.existsByTitulo(titulo) && topicoRepository.existsByMensaje(mensaje)){
            throw new IllegalArgumentException("Ya hay un topico similar");
        }

        if(datosActualizarTopico.titulo() != null){
            topico.setTitulo(datosActualizarTopico.titulo());
        }

        if(datosActualizarTopico.mensaje() != null){
            topico.setMensaje(datosActualizarTopico.mensaje());
        }

        if(datosActualizarTopico.status() != null){
            topico.setStatus(datosActualizarTopico.status());
        }

        return new DatosDetalleTopico(topico);






    }




}
