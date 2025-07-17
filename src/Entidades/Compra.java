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
    private Timestamp fecha_Actualizacion;
    private Timestamp fecha_estadoEnPoceso;
    private Timestamp fecha_estadoCompletado;
    private Timestamp fecha_cancelacion;

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
        return "empleado:" + empleado.getNombre()+", \n" + 
                "proveedor:" + proveedor.getNombre()+", \n"+
                "estadoSolicitud:" + estadoSolicitud.getNombre()+", \n"  + 
                "total:" + total + '}';
    }

}
