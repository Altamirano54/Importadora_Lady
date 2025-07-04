/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;
import Entidades.VentaDetalles;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author LENOVO
 */
public class ModeloTablaVentaDetalle extends AbstractTableModel {

    private final String[] columnas = {
        "Producto", "Cantidad", "Precio Total"
    };

    private ArrayList<VentaDetalles> listaDetalle;

    public ModeloTablaVentaDetalle() {
        this.listaDetalle = new ArrayList<>();
    }

    public void setListaDetalle(ArrayList<VentaDetalles> listaDetalle) {
        this.listaDetalle = listaDetalle;
        fireTableDataChanged();
    }

    public ArrayList<VentaDetalles> getListaDetalle() {
        return listaDetalle;
    }

    public VentaDetalles getVentaDetalleEn(int fila) {
        if (fila >= 0 && fila < listaDetalle.size()) {
            return listaDetalle.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listaDetalle.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VentaDetalles detalle = listaDetalle.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return (detalle.getProducto() != null) ? detalle.getProducto().getNombre() : "Sin producto";
            case 1:
                return detalle.getCantidad();
            case 2:
                return detalle.getPrecioTotal();
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
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Float.class;
            default: return Object.class;
        }
    }
}