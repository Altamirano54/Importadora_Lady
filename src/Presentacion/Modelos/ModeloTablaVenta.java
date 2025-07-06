/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;
import Entidades.Venta;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class ModeloTablaVenta extends AbstractTableModel {

    private final String[] columnas = {
        "Cliente", "Total", "Estado", "Fecha"
    };

    private ArrayList<Venta> listaVenta;

    public ModeloTablaVenta() {
        this.listaVenta = new ArrayList<>();
    }

    public void setListaVenta(ArrayList<Venta> listaVenta) {
        this.listaVenta = listaVenta;
        fireTableDataChanged();
    }

    public ArrayList<Venta> getListaVenta() {
        return listaVenta;
    }

    public Venta getVentaEn(int fila) {
        if (fila >= 0 && fila < listaVenta.size()) {
            return listaVenta.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listaVenta.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = listaVenta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return (venta.getCliente() != null) ? venta.getCliente().getNombre() : "Sin cliente";
            case 1:
                return venta.getTotal();
            case 2:
                return (venta.getEstadoSolicitud() != null) ? venta.getEstadoSolicitud().getNombre() : "Sin estado";
            case 3:
                return venta.getFech();
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
            case 0: return String.class;              // Cliente
            case 1: return Float.class;               // Total
            case 2: return String.class;              // Estado
            case 3: return java.sql.Timestamp.class;  // Fecha
            default: return Object.class;
        }
    }
}