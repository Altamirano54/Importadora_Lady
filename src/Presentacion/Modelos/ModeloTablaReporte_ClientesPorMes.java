package Presentacion.Modelos;

import Entidades.ReporteClientesPorMes;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla para mostrar la cantidad de clientes registrados por mes.
 */
public class ModeloTablaReporte_ClientesPorMes extends AbstractTableModel {

    private final String[] columnas = {
        "Mes de Registro", "Cantidad de Clientes"
    };

    private ArrayList<ReporteClientesPorMes> listado;

    public ModeloTablaReporte_ClientesPorMes() {
        this.listado = new ArrayList<>();
    }

    public void setListado(ArrayList<ReporteClientesPorMes> listado) {
        this.listado = listado;
        fireTableDataChanged();
    }

    public ReporteClientesPorMes getItem(int fila) {
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
        ReporteClientesPorMes r = listado.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> r.getMes();
            case 1 -> r.getCantidad();
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
            default -> Object.class;
        };
    }
}
