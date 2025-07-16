package Presentacion.Modelos;

import Entidades.ReporteVentaPorDia;
import Entidades.ReporteVentaPorMes;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo para la tabla del reporte de ventas por día.
 * Cada fila representa la fecha, la cantidad de ventas y los ingresos totales de ese día.
 * Author: Jheff
 */
public class ModeloTablaReporte_VentaPorDia extends AbstractTableModel {

    private final String[] columnas = {
        "Fecha", "Cantidad Ventas", "Ingresos Totales"
    };

    private ArrayList<ReporteVentaPorDia> listado;

    public ModeloTablaReporte_VentaPorDia() {
        this.listado = new ArrayList<>();
    }

    /**
     * Actualiza el listado de datos y notifica a la vista para refrescar la tabla.
     */
    public void setListado(ArrayList<ReporteVentaPorDia> listado) {
        this.listado = listado;
        fireTableDataChanged();
    }

    public ArrayList<ReporteVentaPorDia> getListado() {
        return listado;
    }

    /**
     * Devuelve el objeto ReporteVentaPorDia en la fila indicada.
     */
    public ReporteVentaPorDia getItem(int fila) {
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
        ReporteVentaPorDia r = listado.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getFecha();
            case 1:
                return r.getCantidadVentas();
            case 2:
                // Formato de moneda, puedes ajustar prefijo o decimales
                return "S/ " + r.getIngresosTotales();
            default:
                return null;
        }
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
            case 2 -> String.class;
            default -> Object.class;
        };
    }
}
