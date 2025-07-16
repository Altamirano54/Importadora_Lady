package Entidades;

/**
 * Entidad que representa un resumen de ventas realizadas por cliente.
 * Incluye el nombre del cliente, la cantidad total de compras realizadas y el total gastado.
 * Author: Jheff
 */
public class ReporteVentaPorCliente {
    private String cliente;
    private int cantidadVentas;
    private float ingresosTotales;

    // Constructor vacío
    public ReporteVentaPorCliente() {}

    // Constructor con parámetros
    public ReporteVentaPorCliente(String cliente, int cantidadVentas, float ingresosTotales) {
        this.cliente = cliente;
        this.cantidadVentas = cantidadVentas;
        this.ingresosTotales = ingresosTotales;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
        return String.format("%s → %d compras, S/ %,.2f", cliente, cantidadVentas, ingresosTotales);
    }
}
