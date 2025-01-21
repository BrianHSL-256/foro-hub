package foro_hub.alura_project_foro.domain.topico.validaciones;


import foro_hub.alura_project_foro.domain.curso.Curso;
import foro_hub.alura_project_foro.domain.curso.CursoRepository;
import foro_hub.alura_project_foro.domain.topico.DatosDetalleTopico;
import foro_hub.alura_project_foro.domain.topico.DatosRegistroTopico;
import foro_hub.alura_project_foro.domain.topico.Topico;
import foro_hub.alura_project_foro.domain.topico.TopicoRepository;
import foro_hub.alura_project_foro.domain.usuario.Usuario;
import foro_hub.alura_project_foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    public DatosDetalleTopico registrarTopico(DatosRegistroTopico datosRegistroTopico){

        var titulo = datosRegistroTopico.titulo();
        var mensaje = datosRegistroTopico.mensaje();

        if(topicoRepository.existsByTitulo(titulo) && topicoRepository.existsByMensaje(mensaje)){
            throw new IllegalArgumentException("Ya hay un topico similar");
        }

        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autor())
                .orElseThrow(() -> new EntityNotFoundException("El autor no fue encontrado"));

        Curso curso = cursoRepository.findById(datosRegistroTopico.curso())
                .orElseThrow(() -> new EntityNotFoundException("El curso no fue encontrado"));

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, autor,curso));

        return new DatosDetalleTopico(topico);

    }









}
