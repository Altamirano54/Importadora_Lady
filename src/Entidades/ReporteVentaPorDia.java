// src/Entidades/ReporteVentaPorDia.java
package Entidades;

/**
 * Entidad que representa un registro del reporte de ventas por día.
 * Cada instancia contiene la fecha, la cantidad de ventas y los ingresos totales.
 * Author: Jheff
 */
public class ReporteVentaPorDia {
    private String fecha;          // Formato "yyyy-MM-dd" o similar
    private int cantidadVentas;    
    private float ingresosTotales;

    public ReporteVentaPorDia() { }

    public ReporteVentaPorDia(String fecha, int cantidadVentas, float ingresosTotales) {
        this.fecha = fecha;
        this.cantidadVentas = cantidadVentas;
        this.ingresosTotales = ingresosTotales;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public float getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(float ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    @Override
    public String toString() {
        return fecha + " → " + cantidadVentas + " ventas, S/ " + ingresosTotales;
    }
}
