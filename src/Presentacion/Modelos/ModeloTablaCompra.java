/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

import Entidades.Compra;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class ModeloTablaCompra extends AbstractTableModel {

    private final String[] columnas = {
        "Proveedor", "Estado", "Total", "Fecha"
    };

    private ArrayList<Compra> listaCompra;

    public ModeloTablaCompra() {
        this.listaCompra = new ArrayList<>();
    }

    public void setListaCompra(ArrayList<Compra> listaCompra) {
        this.listaCompra = listaCompra;
        fireTableDataChanged();
    }

    public ArrayList<Compra> getListaCompra() {
        return listaCompra;
    }

    public Compra getCompraEn(int fila) {
        if (fila >= 0 && fila < listaCompra.size()) {
            return listaCompra.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listaCompra.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Compra compra = listaCompra.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return (compra.getProveedor() != null) ? compra.getProveedor().getNombre() : "Sin proveedor";
            case 1:
                return (compra.getEstadoSolicitud() != null) ? compra.getEstadoSolicitud().getNombre() : "Sin estado";
            case 2:
                return compra.getTotal();
            case 3:
                return compra.getFecha();
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
        switch (columnIndex) {
            case 0: return String.class;  // Proveedor
            case 1: return String.class;  // Estado
            case 2: return Float.class;   // Total
            case 3: return java.sql.Timestamp.class; // Fecha
            default: return Object.class;
        }
    }
}