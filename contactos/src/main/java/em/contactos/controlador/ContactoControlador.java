package em.contactos.controlador;

import em.contactos.modelo.Contacto;
import em.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        return "agregar";//agregar.html
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForma") Contacto contacto){
        logger.info("Agregando contacto: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value="id") int idContacto, ModelMap modelo) {
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Mostrando contacto a editar: " + contacto);
        modelo.put("contacto", contacto);
        return "editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto){
        logger.info("Editando contacto: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") int idContacto){
        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);
        contactoServicio.eliminarContacto(contacto);
        return "redirect:/";
    }

}
