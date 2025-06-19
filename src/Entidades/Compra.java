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
public class Compra {

    private int id;
    private Empleado empleado;
    private Proveedor proveedor;
    private EstadoSolicitud estadoSolicitud;
    private float total;
    private Timestamp fecha;

    public Compra() {
    }

    public Compra(int id, Empleado empleado, Proveedor proveedor, EstadoSolicitud estadoSolicitud, float total, Timestamp fecha) {
        this.id = id;
        this.empleado = empleado;
        this.proveedor = proveedor;
        this.estadoSolicitud = estadoSolicitud;
        this.total = total;
        this.fecha = fecha;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Compra{" + "empleado=" + empleado + ", proveedor=" + proveedor + ", estadoSolicitud=" + estadoSolicitud + ", total=" + total + '}';
    }

}
