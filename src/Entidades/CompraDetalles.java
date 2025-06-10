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
public class CompraDetalles {
    private int id;
    private Compra compra;
    private Producto producto;
    private int cantidad;
    private float precioTotal;
    private Timestamp fecha;

    public CompraDetalles() {
    }

    public CompraDetalles(int id, Compra compra, Producto producto, int cantidad, float precioTotal, Timestamp fecha) {
        this.id = id;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CompraDetalles{" + "compra=" + compra + ", producto=" + producto + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal + '}';
    }
    
    
}
