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
public class Venta {
    private int id;
    private Empleado empleado;
    private Cliente cliente;
    private EstadoSolicitud estadoSolicitud;
    private float total;
    private Timestamp fech;

    public Venta() {
    }

    public Venta(int id, Empleado empleado, Cliente cliente, EstadoSolicitud estadoSolicitud, float total, Timestamp fech) {
        this.id = id;
        this.empleado = empleado;
        this.cliente = cliente;
        this.estadoSolicitud = estadoSolicitud;
        this.total = total;
        this.fech = fech;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Timestamp getFech() {
        return fech;
    }

    public void setFech(Timestamp fech) {
        this.fech = fech;
    }

    @Override
    public String toString() {
        return "Venta{" + "empleado=" + empleado + ", cliente=" + cliente + ", estadoSolicitud=" + estadoSolicitud + ", total=" + total + ", fech=" + fech + '}';
    }
    
    
}
