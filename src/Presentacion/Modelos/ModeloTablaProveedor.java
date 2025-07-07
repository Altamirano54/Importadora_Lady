package Presentacion.Modelos;

import Entidades.Proveedor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla para mostrar proveedores en un JTable.
 * 
 * Autor: LENOVO
 */
public class ModeloTablaProveedor extends AbstractTableModel {

    private final String[] columnas = {
        "ID","RUC", "Nombre", "Dirección", "Correo", "Contacto"
    };

    private ArrayList<Proveedor> listadoProveedor;

    public ModeloTablaProveedor() {
        this.listadoProveedor = new ArrayList<>();
    }

    public void setListadoProveedor(ArrayList<Proveedor> listadoProveedor) {
        this.listadoProveedor = listadoProveedor;
        fireTableDataChanged(); 
    }

    public ArrayList<Proveedor> getListadoProveedor() {
        return listadoProveedor;
    }

    public Proveedor getProveedor(int fila) {
        if (fila >= 0 && fila < listadoProveedor.size()) {
            return listadoProveedor.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return (listadoProveedor != null) ? listadoProveedor.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor p = listadoProveedor.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getId();
            case 1: return p.getRuc();
            case 2: return p.getNombre();
            case 3: return p.getDireccion();
            case 4: return p.getCorreo();
            case 5: return p.getContacto();
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
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return String.class;
            default: return Object.class;
        }
    }
}
