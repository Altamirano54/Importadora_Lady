package Presentacion.Modelos;

import Entidades.ReporteProductoMasVendido;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo para la tabla de reporte de productos más vendidos.
 * @author Jheff
 */
public class ModeloTablaReporte_ProductoMasVendido extends AbstractTableModel {

    private final String[] columnas = {
        "ID", "Producto", "Cantidad Vendida", "Precio Unitario", "Ingresos Generados"
    };

    private ArrayList<ReporteProductoMasVendido> listado;

    public ModeloTablaReporte_ProductoMasVendido() {
        this.listado = new ArrayList<>();
    }

    public void setListado(ArrayList<ReporteProductoMasVendido> listado) {
        this.listado = listado;
        fireTableDataChanged(); // Notifica que los datos han cambiado
    }

    public ArrayList<ReporteProductoMasVendido> getListado() {
        return listado;
    }

    public ReporteProductoMasVendido getItem(int fila) {
        if (fila >= 0 && fila < listado.size()) {
            return listado.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listado != null ? listado.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReporteProductoMasVendido rp = listado.get(rowIndex);
        switch (columnIndex) {
            case 0: return rp.getIdProducto();
            case 1: return rp.getNombreProducto();
            case 2: return rp.getCantidadVendida();
            case 3: return "S/ " + rp.getPrecioUnitario();
            case 4: return "S/ " + rp.getIngresosGenerados();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Integer.class;
            case 3: return String.class;
            case 4: return String.class;
            default: return Object.class;
        }
    }
  
}
