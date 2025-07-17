/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Amir Altamirano
 */
import java.sql.Timestamp;

public class SeguimientoEstado {
    private String nombreEstado;
    private Timestamp fecha;

    public SeguimientoEstado(String nombreEstado, Timestamp fecha) {
        this.nombreEstado = nombreEstado;
        this.fecha = fecha;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return nombreEstado + " - " + fecha;
    }
}