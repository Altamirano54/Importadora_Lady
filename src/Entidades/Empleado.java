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
public class Empleado {
    private int id;
    private String nombre;
    private String contrasena;
    private Cargo cargo;
    private Timestamp fechaCreacion;
    private boolean estado;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String contrasena, Cargo cargo, Timestamp fechaCreacion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.cargo = cargo;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", cargo=" + cargo.toString() + '}';
    }
    
    
}
