/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

import Entidades.Producto;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class ModeloTablaProducto extends AbstractTableModel {

    private final String[] columnas = {
        "ID", "Nombre", "Precio Venta", "Precio Compra", "Proveedor"
    };

    private ArrayList<Producto> listadoProducto;

    public ModeloTablaProducto() {
        this.listadoProducto = new ArrayList<>();
    }

    public void setListadoProducto(ArrayList<Producto> listadoProducto) {
        this.listadoProducto = listadoProducto;
        fireTableDataChanged(); // Notifica a la tabla que debe actualizarse
    }

    public ArrayList<Producto> getListadoProducto() {
        return listadoProducto;
    }

    public Producto getProducto(int fila) {
        if (fila >= 0 && fila < listadoProducto.size()) {
            return listadoProducto.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return (listadoProducto != null) ? listadoProducto.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = listadoProducto.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNombre();
            case 2:
                return p.getPrecioVenta();
            case 3:
                return p.getPrecioCompra();
            case 4:
                return (p.getProveedor() != null) ? p.getProveedor().getNombre() : "Sin proveedor";
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
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Float.class;
            case 3:
                return Float.class;
            case 4:
                return String.class;
            default:
                return Object.class;
        }
    }

    public Producto getProductoEn(int fila) {
        return this.listadoProducto.get(fila); // 'productos' es tu lista interna
    }

}
