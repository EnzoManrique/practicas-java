package em.contactos.servicio;

import em.contactos.modelo.Contacto;
import em.contactos.repositorio.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServicio implements IContactoServicio{

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    @Override
    public List<Contacto> listarContacto() {
        return contactoRepositorio.findAll();
    }

    @Override
    public Contacto buscarContactoPorId(Integer id) {
        return contactoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarContacto(Contacto contacto) {
        contactoRepositorio.save(contacto);
    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        contactoRepositorio.delete(contacto);
    }
}
