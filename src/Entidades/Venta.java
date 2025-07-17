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
    private Timestamp fecha_Actualizacion;
    private Timestamp fecha_estadoEnPoceso;
    private Timestamp fecha_estadoCompletado;
    private Timestamp fecha_cancelacion;

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

    public Timestamp getFecha_Actualizacion() {
        return fecha_Actualizacion;
    }

    public void setFecha_Actualizacion(Timestamp fecha_Actualizacion) {
        this.fecha_Actualizacion = fecha_Actualizacion;
    }

    public Timestamp getFecha_estadoEnPoceso() {
        return fecha_estadoEnPoceso;
    }

    public void setFecha_estadoEnPoceso(Timestamp fecha_estadoEnPoceso) {
        this.fecha_estadoEnPoceso = fecha_estadoEnPoceso;
    }

    public Timestamp getFecha_estadoCompletado() {
        return fecha_estadoCompletado;
    }

    public void setFecha_estadoCompletado(Timestamp fecha_estadoCompletado) {
        this.fecha_estadoCompletado = fecha_estadoCompletado;
    }

    public Timestamp getFecha_cancelacion() {
        return fecha_cancelacion;
    }

    public void setFecha_cancelacion(Timestamp fecha_cancelacion) {
        this.fecha_cancelacion = fecha_cancelacion;
    }
    
    

    @Override
    public String toString() {
        return "Venta{" + "empleado=" + empleado + ", cliente=" + cliente + ", estadoSolicitud=" + estadoSolicitud + ", total=" + total + ", fech=" + fech + '}';
    }
    
    
}
