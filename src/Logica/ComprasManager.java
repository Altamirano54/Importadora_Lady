/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import AccesoDatos.BDCompra;
import AccesoDatos.BDCompraDetalles;
import Entidades.Compra;
import Entidades.CompraDetalles;
import Entidades.Empleado;
import Entidades.EstadoSolicitud;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author Amir Altamirano
 */
public class ComprasManager {
    
    BDCompra bdCompra;
    BDCompraDetalles bdDetalle;

    public ComprasManager() {
        bdCompra = new BDCompra();
        bdDetalle = new BDCompraDetalles();
    }
    
    public ArrayList<Compra> listar() throws Exception{
        return bdCompra.listar();
    }
    
    public ArrayList<CompraDetalles> obtenerProductosDeVentasPendientes(){
        return bdDetalle.obtenerProductosDeVentasPendientes();
    }
    
    
    public ArrayList<Compra> generarComprasPropuestas(ArrayList<CompraDetalles> detallesAgrupados, Empleado empleadoActual) {
        ArrayList<Compra> comprasPropuestas = new ArrayList<>();

        if (detallesAgrupados.isEmpty()) return comprasPropuestas;


        Compra compraActual = null;
        int idProveedorAnterior = -1;

        for (CompraDetalles detalle : detallesAgrupados) {
            int idProveedorActual = detalle.getProducto().getProveedor().getId();

            
            if (idProveedorActual != idProveedorAnterior) {
                if (compraActual != null) {
                    
                    comprasPropuestas.add(compraActual);
                }

                compraActual = new Compra();
                compraActual.setProveedor(detalle.getProducto().getProveedor());
                compraActual.setEmpleado(empleadoActual);
                compraActual.setEstadoSolicitud(new EstadoSolicitud(0,"Propuesto")); // Propuesta
                compraActual.setFecha(new Timestamp(System.currentTimeMillis()));
                compraActual.setTotal(0); 
                compraActual.setEstado(true); 
                compraActual.setId(-1); 
            }

            // Agregar detalle a la compra actual
            compraActual.setTotal(compraActual.getTotal() + detalle.getPrecioTotal());
            detalle.setCompra(compraActual); // aún no tiene ID real

            comprasPropuestas.add(detalle.getCompra()); // Opcional: si quieres registrar las compras sin repetir
            idProveedorAnterior = idProveedorActual;
        }

        // Agregar última compra
        if (compraActual != null && !comprasPropuestas.contains(compraActual)) {
            comprasPropuestas.add(compraActual);
        }

        return comprasPropuestas;
    }

    // Método adicional: Confirmar y guardar en BD
    public void confirmarCompra(Compra compra, ArrayList<CompraDetalles> detalles) throws Exception {


        int idGenerado = bdCompra.crear(compra);
        if (idGenerado <= 0) throw new Exception("No se pudo crear la compra.");

        // Asignar ID generado a la compra
        compra.setId(idGenerado);

        for (CompraDetalles detalle : detalles) {
            if (detalle.getCompra().getProveedor().getId() == compra.getProveedor().getId()) {
                detalle.setCompra(compra); // asignar ID real
                bdDetalle.crear(detalle);
            }
        }
    }
    
    public float CalcularTotal(ArrayList<CompraDetalles> detalles){
        float total=0;
        System.out.println("cantidad detalles cm: "+ detalles.size());
        for (CompraDetalles detalle : detalles) {
            total+=detalle.getPrecioTotal();
            System.out.println("total calculado cm: " +total);
        }
        return total;
    }
}
