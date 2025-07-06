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
public class EstadoSolicitud {
    private int id;
    private String nombre;
    private Timestamp fechaCreacion;

    public EstadoSolicitud() {
    }
    public EstadoSolicitud(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }
    public EstadoSolicitud(int id, String nombre, Timestamp fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "EstadoSolicitud{" + "nombre=" + nombre + '}';
    }
    
    
}
