package Logica;

import AccesoDatos.BDProducto;
import Entidades.Producto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Gestión lógica de productos
 * @author jheff
 */
public class ProductoManager {

    private final BDProducto bdproducto;

    public ProductoManager() {
        bdproducto = new BDProducto();
    }

    /**
     * Cargar todos los productos activos desde la base de datos
     * @return 
     * @throws java.lang.Exception 
     */
    public ArrayList<Producto> cargarProductosActivos() throws Exception {
        return bdproducto.listar();
    }

    /**
     * Obtener un producto por su ID
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    public Producto obtenerPorId(int id) throws Exception {
        return bdproducto.get(id);
    }

    /**
     * Crear un nuevo producto
     * @param producto
     * @return 
     */
    public boolean registrarProducto(Producto producto) {
        try {
            int filas = bdproducto.crear(producto);
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualizar datos de un producto existente
     * @param id
     * @param producto
     * @return 
     */
    public boolean actualizarProducto(int id, Producto producto) {
        try {
            bdproducto.actualizar(id, producto);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Eliminar (dar de baja lógica) un producto por ID
     * @param id
     * @return 
     */
    public boolean eliminarProducto(int id) {
        try {
            bdproducto.eliminar(id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Producto> buscarPorNombre(String nombre) throws Exception {
    return bdproducto.buscarPorNombre(nombre);
}

    public ArrayList<Producto> buscarPorProveedor(int idProveedor) throws Exception {
    return bdproducto.buscarPorProveedor(idProveedor);
}

    
    public ArrayList<Producto> ordenarPor(String campo, String orden) throws Exception {
    return bdproducto.ordenarPorCampo(campo, orden);
}

    
    public int contarProductos(boolean activos) throws Exception {
    return bdproducto.contarPorEstado(activos);
}

    
}
