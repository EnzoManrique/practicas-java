package em.contactos.servicio;

import em.contactos.modelo.Contacto;

import java.util.List;

public interface IContactoServicio {

    public List<Contacto> listarContacto();

    public Contacto buscarContactoPorId(Integer id);

    public void guardarContacto(Contacto contacto);

    public void eliminarContacto(Integer id);

}
