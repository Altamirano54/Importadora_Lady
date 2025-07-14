/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import java.sql.Timestamp;
/**
 *
 * @author Amir Altamirano
 */
public class Producto {
    private int id;
    private String nombre;
    private float precioVenta;
    private float precioCompra;
    private int stock;
    private Proveedor proveedor;
    private String descripcion; // nuevo campo según la BD
    private Timestamp fechaModificacion;
    private Timestamp fechaCreacion;
    private boolean estado; // int en BD, puedes usar boolean si lo prefieres
    private String url;

    public Producto() {
    }

    public Producto(int id, String nombre, float precioVenta, float precioCompra, int stock,
                    Proveedor proveedor, String descripcion, Timestamp fechaModificacion,
                    Timestamp fechaCreacion, boolean estado, String url) {
        this.id = id;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.url = url;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public float getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(float precioVenta) { this.precioVenta = precioVenta; }

    public float getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(float precioCompra) { this.precioCompra = precioCompra; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Timestamp getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(Timestamp fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public Timestamp getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Timestamp fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public boolean getEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return nombre;
    }
}