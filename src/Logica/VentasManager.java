/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import AccesoDatos.BDProducto;
import AccesoDatos.BDVenta;
import AccesoDatos.BDVentaDetalle;
import Entidades.EstadoSolicitud;
import Entidades.Producto;
import Entidades.SeguimientoEstado;
import Entidades.Venta;
import Entidades.VentaDetalles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amir Altamirano
 */
public class VentasManager {
    private BDProducto bDProducto;
    private BDVenta bDVenta;
    private BDVentaDetalle bDVentaDetalle;
     
    public VentasManager(){
        bDProducto=new BDProducto();
        this.bDVenta=new BDVenta();
        this.bDVentaDetalle = new BDVentaDetalle();
    }
    
    public Venta ObtenerVenta(int id){
        try {
            return bDVenta.get(id);
        } catch (Exception ex) {
            Logger.getLogger(VentasManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public float CalcularTotal(ArrayList<VentaDetalles> detalles){
        float Total=0;
        if(detalles.size()>0){
            for (VentaDetalles detalle : detalles) {
                Total+= detalle.getPrecioTotal();
            }
        }
        return Total;
    }
     
    public ArrayList<Venta> ListarVentas() throws Exception{
        return bDVenta.listar();
    }
    
    public ArrayList<Venta> ListarVentasCompletas() throws Exception{
        return bDVenta.listarCompletados();
    }
    
    public ArrayList<VentaDetalles> ListarDetallesVenta(int id) throws Exception{
        return bDVentaDetalle.get(id);
    }
    
    public boolean RegistrarVenta(Venta v, ArrayList<VentaDetalles> detalles) throws SQLException{
        int idGenerado = bDVenta.crear(v); 
        if (idGenerado != -1) {
            v.setId(idGenerado);

            for (VentaDetalles detalle : detalles) {
                detalle.setVenta(v); 
                bDVentaDetalle.crear(detalle);
            }

            return true;
        }

        return false;
    }
    
    public void CancelarVenta(Venta venta) throws Exception{
        bDVenta.eliminar(venta.getId());
    }
    
    public void ActualizarVenta(Venta venta) throws Exception{
        bDVenta.actualizar(venta.getId(), venta);
    }
    
    public void actualizarStockPorVenta(ArrayList<VentaDetalles> detalles) throws SQLException{
        bDProducto.actualizarStockPorVenta(detalles);
    }
    
    public ArrayList<String> verificarStockInsuficiente(ArrayList<VentaDetalles> detalles) {
        ArrayList<String> errores = new ArrayList<>();

        for (VentaDetalles detalle : detalles) {
            try {
                Producto p = bDProducto.get(detalle.getProducto().getId());
                if (p.getStock() < detalle.getCantidad()) {
                    errores.add(p.getNombre() + " (Stock: " + p.getStock() + ", Requiere: " + detalle.getCantidad() + ")");
                }
            } catch (Exception ex) {
                errores.add("Error al verificar stock de producto ID: " + detalle.getProducto().getId());
            }
        }

        return errores;
    }
    
    public ArrayList<SeguimientoEstado> obtenerSeguimientoEstados(int idVenta){
        return bDVenta.obtenerSeguimientoEstados(idVenta);
    }

    public boolean cancelarVenta(int id) throws Exception {
        try {
            bDVenta.eliminar(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
        
       
    }

}
