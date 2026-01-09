package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

    String nl = System.lineSeparator();

	public static void main(String[] args) {
        logger.info("Iniciando ZonaFitApplication");
        SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("Aplicacion Finalizada!!!!");
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info(nl +"*** Zona Fit GYM ***"+nl);
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        while (!salir) {
            int opcion = mostrarMenu(sc);
            salir = ejecutarOpciones(sc,opcion);
            logger.info(nl);
        }
    }

    private int mostrarMenu(Scanner sc) {
        logger.info("""
                1.Listar Clientes
                2.Buscar Clientes
                3.Agregar Clientes
                4.Modificar Clientes
                5.Eliminar Clientes
                6.Salir
                Elge una opcion: \s""");
        int opcion = Integer.parseInt(sc.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(Scanner sc, int opcion) {
        boolean salir = false;
        switch (opcion) {
             case 1 -> {
                 logger.info(nl +"--- Listando clientes ----"+nl);
                 List<Cliente> clientes = clienteServicio.listarClientes();
                 clientes.forEach(cliente -> logger.info(cliente.toString()+nl));
             }
             case 2 -> {
                 logger.info(nl +"--- Buscando cliente por id ----"+nl);
                 logger.info("Ingrese id del cliente a buscar."+nl);
                 Integer id = Integer.parseInt(sc.nextLine());
                 Cliente cliente = clienteServicio.buscarClientePorId(id);
                 if(cliente != null) {
                     logger.info("Cliente encontrado: "+cliente +nl);
                 }else{
                     logger.info("Cliente no encontrado: "+cliente +nl);
                 }
             }
             case 3 -> {
                 logger.info(nl +"--- Agregar cliente ----"+nl);
                 logger.info("Ingrese el nombre del cliente que desea agregar"+nl);
                 String nombre = sc.nextLine();
                 logger.info("Ingrese apellido del cliente que desea agregar"+nl);
                 String apellido = sc.nextLine();
                 logger.info("Ingre el numero de membresia del cliente que desea agregar"+nl);
                 Integer membresia = Integer.parseInt(sc.nextLine());
                 Cliente cliente = new Cliente();
                 cliente.setNombre(nombre);
                 cliente.setApellido(apellido);
                 cliente.setMembresia(membresia);
                 clienteServicio.guardarCliente(cliente);
                 logger.info("Cliente guardado: "+cliente +nl);

             }
             case 4 -> {
                 logger.info(nl +"--- Modificar cliente ----"+nl);
                 logger.info("Ingrese id del cliente a buscar."+nl);
                 Integer id = Integer.parseInt(sc.nextLine());
                 Cliente cliente = clienteServicio.buscarClientePorId(id);
                 if(cliente != null) {
                     logger.info("Ingrese el nuevo nombre del cliente: "+nl);
                     cliente.setNombre(sc.nextLine());
                     logger.info("Ingrese el nuevo apellido del cliente: "+nl);
                     cliente.setApellido(sc.nextLine());
                     logger.info("Ingrese el numero de membresia del cliente: "+nl);
                     cliente.setMembresia(Integer.parseInt(sc.nextLine()));
                     clienteServicio.guardarCliente(cliente);
                     logger.info("Cliente guardado: "+cliente +nl);
                 }else{
                  logger.info("Cliente no encontrado: "+cliente +nl);
                 }
             }
             case 5 -> {
                 logger.info(nl +"--- Eliminar cliente ----"+nl);
                 logger.info("Ingrese id del cliente a eliminar."+nl);
                 Integer id = Integer.parseInt(sc.nextLine());
                 Cliente cliente = clienteServicio.buscarClientePorId(id);
                 if(cliente != null) {
                     clienteServicio.eliminarCliente(cliente);
                     logger.info("Cliente eliminado: "+cliente +nl);
                 }else{
                     logger.info("Cliente no encontrado: "+cliente +nl);
                 }
             }
             case 6 -> {
                 logger.info(nl +"--- Hasta Pronto ----"+nl);
                 salir = true;
             }
            default -> logger.info("--- Opcion no valida ---");
        }
        return salir;
    }

}
