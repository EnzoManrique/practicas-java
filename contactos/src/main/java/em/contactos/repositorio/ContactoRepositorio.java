package em.contactos.repositorio;

import em.contactos.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepositorio extends JpaRepository<Contacto, Integer>{
}
