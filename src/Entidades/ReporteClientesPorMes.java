package Entidades;

public class ReporteClientesPorMes {
    private String mes;
    private int cantidad;

    public ReporteClientesPorMes() {}

    public ReporteClientesPorMes(String mes, int cantidad) {
        this.mes = mes;
        this.cantidad = cantidad;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return mes + " → " + cantidad + " clientes";
    }
}
