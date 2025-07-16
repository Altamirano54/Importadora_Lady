package Entidades;

/**
 * Entidad que representa los datos del reporte de productos más vendidos.
 * @author Jheff
 */
public class ReporteProductoMasVendido {

    private int idProducto;
    private String nombreProducto;
    private int cantidadVendida;
    private float precioUnitario;
    private float ingresosGenerados;

    // --- Constructores ---

    public ReporteProductoMasVendido() {
    }

    public ReporteProductoMasVendido(int idProducto, String nombreProducto, int cantidadVendida, float precioUnitario, float ingresosGenerados) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
        this.precioUnitario = precioUnitario;
        this.ingresosGenerados = ingresosGenerados;
    }

    // --- Getters y Setters ---

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getIngresosGenerados() {
        return ingresosGenerados;
    }

    public void setIngresosGenerados(float ingresosGenerados) {
        this.ingresosGenerados = ingresosGenerados;
    }

    // --- Método útil (opcional) ---
    @Override
    public String toString() {
        return nombreProducto + " (" + cantidadVendida + " unidades, S/ " + ingresosGenerados + ")";
    }
}
