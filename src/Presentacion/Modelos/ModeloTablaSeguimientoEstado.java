/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

/**
 *
 * @author Amir Altamirano
 */
import Entidades.SeguimientoEstado;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.Timestamp;

public class ModeloTablaSeguimientoEstado extends AbstractTableModel {

    private final String[] columnas = {"Estado", "Fecha"};
    private ArrayList<SeguimientoEstado> listaSeguimiento;

    public ModeloTablaSeguimientoEstado() {
        this.listaSeguimiento = new ArrayList<>();
    }

    public void setListaSeguimiento(ArrayList<SeguimientoEstado> listaSeguimiento) {
        this.listaSeguimiento = listaSeguimiento;
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }

    public ArrayList<SeguimientoEstado> getListaSeguimiento() {
        return listaSeguimiento;
    }

    public SeguimientoEstado getSeguimientoEn(int fila) {
        if (fila >= 0 && fila < listaSeguimiento.size()) {
            return listaSeguimiento.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listaSeguimiento.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SeguimientoEstado seguimiento = listaSeguimiento.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return seguimiento.getNombreEstado();
            case 1:
                return seguimiento.getFecha().toString();
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
            case 0: return String.class;      // Estado
            case 1: return String.class;   // Fecha
            default: return Object.class;
        }
    }
}