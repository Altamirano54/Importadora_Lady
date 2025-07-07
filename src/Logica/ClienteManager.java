package Logica;

import AccesoDatos.BDCliente;
import Entidades.Cliente;
import java.util.ArrayList;

/**
 * Lógica de negocio para la gestión de clientes
 *
 * @author jheff
 */
public class ClienteManager {

    private final BDCliente bdCliente;

    public ClienteManager() {
        bdCliente = new BDCliente();
    }

    public ArrayList<Cliente> listarClientesActivos() throws Exception {
        ArrayList<Cliente> clientes = bdCliente.listar();
        return clientes;
    }

    public Cliente obtenerPorId(int id) throws Exception {
        return bdCliente.get(id);
    }

    public boolean registrarCliente(Cliente cliente) {
        try {
            return bdCliente.crear(cliente) > 0;
        } catch (Exception e) {
            System.err.println("Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCliente(int id, Cliente cliente) {
        try {
            bdCliente.actualizar(id, cliente);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(int id) {
        try {
            bdCliente.eliminar(id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Cliente> buscarPorNombre(String nombre) throws Exception {
        return bdCliente.buscarPorNombre(nombre);
    }
    public Cliente buscarPorDocumento(String nroDocumento) throws Exception {
        return bdCliente.buscarPorDocumento(nroDocumento);
    }

}
