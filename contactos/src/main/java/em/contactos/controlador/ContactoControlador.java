package em.contactos.controlador;

import em.contactos.modelo.Contacto;
import em.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactoControlador {

    @Autowired
    ContactoServicio contactoServicio;

    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @GetMapping("/")
    public String inicio(ModelMap modelo) {
        List<Contacto> contactos = contactoServicio.listarContacto();
        contactos.forEach(contacto -> {logger.info(contacto.toString());});
        modelo.put("contactos", contactos);
        return "index"; //index.html
    }

}
