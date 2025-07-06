/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;
import Entidades.CompraDetalles;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author LENOVO
 */
public class ModeloTablaCompraDetalle extends AbstractTableModel {

    private final String[] columnas = {
        "Producto", "Cantidad", "Precio Total"
    };

    private ArrayList<CompraDetalles> listaDetalle;

    public ModeloTablaCompraDetalle() {
        this.listaDetalle = new ArrayList<>();
    }

    public void setListaDetalle(ArrayList<CompraDetalles> listaDetalle) {
        this.listaDetalle = listaDetalle;
        fireTableDataChanged(); // Notifica a la tabla que se ha actualizado
    }

    public ArrayList<CompraDetalles> getListaDetalle() {
        return listaDetalle;
    }

    public CompraDetalles getCompraDetalleEn(int fila) {
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
        CompraDetalles detalle = listaDetalle.get(rowIndex);
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
            case 0: return String.class;   // Producto
            case 1: return Integer.class;  // Cantidad
            case 2: return Float.class;    // Precio Total
            default: return Object.class;
        }
    }
}