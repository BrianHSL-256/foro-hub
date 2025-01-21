package foro_hub.alura_project_foro.domain.usuario;

public record DatosDetallesUsuario(Long id,
                                   String nombre) {

    public DatosDetallesUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre());
    }
}
