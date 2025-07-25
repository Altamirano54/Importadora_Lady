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
public class Cliente {
    private int id;
    private String nombre;
    private Tipo_documento tipo_documento;
    private String nro_documento;
    private String telefono;
    private String direccion;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;
    private boolean estado;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String telefono, Timestamp fechaCreacion, Timestamp fechaModificacion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Tipo_documento getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Tipo_documento tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(String nro_documento) {
        this.nro_documento = nro_documento;
    }
    
    

    @Override
    public String toString() {
        return nombre ;
    }

    
    
    
    
}
