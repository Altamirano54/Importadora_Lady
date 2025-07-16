package Presentacion.Modelos;

import Entidades.ReporteVentaPorCliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla para mostrar el reporte de ventas por cliente.
 * Cada fila representa un cliente, la cantidad de ventas y los ingresos generados.
 * Autor: Jheff
 */
public class ModeloTablaReporte_VentaPorCliente extends AbstractTableModel {

    private final String[] columnas = {
        "Cliente", "Cantidad Ventas", "Ingresos Totales"
    };

    private ArrayList<ReporteVentaPorCliente> listado;

    public ModeloTablaReporte_VentaPorCliente() {
        this.listado = new ArrayList<>();
    }
  /**
     * Actualiza el listado de datos y notifica a la vista para refrescar la tabla.
     * @param listado
     */
    public void setListado(ArrayList<ReporteVentaPorCliente> listado) {
        this.listado = listado;
        fireTableDataChanged();
    }
 
    public ArrayList<ReporteVentaPorCliente> getListado() {
        return listado;
    }

    public ReporteVentaPorCliente getItem(int fila) {
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
        ReporteVentaPorCliente r = listado.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> r.getCliente();
            case 1 -> r.getCantidadVentas();
            case 2 -> String.format("S/ %.2f", r.getIngresosTotales());
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> Integer.class;
            case 2 -> String.class; // Mostramos como texto con formato de moneda
            default -> Object.class;
        };
    }
}
