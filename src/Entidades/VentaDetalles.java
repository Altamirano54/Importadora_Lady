/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import java.sql.Timestamp;import java.sql.Timestamp;
/**
 *
 * @author Amir Altamirano
 */
public class VentaDetalles {
    private int id;
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private float precioTotal;
    private Timestamp fechaModificacion;

    public VentaDetalles() {
    }

    public VentaDetalles(int id, Venta venta, Producto producto, int cantidad, float precioTotal, Timestamp fechaModificacion) {
        this.id = id;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fechaModificacion = fechaModificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "VentaDetalles{" + "venta=" + venta + ", producto=" + producto + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal + '}';
    }
    
    
}
