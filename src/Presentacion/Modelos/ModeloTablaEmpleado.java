package Presentacion.Modelos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Entidades.Empleado;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Amir Altamirano
 */
public class ModeloTablaEmpleado extends AbstractTableModel{
    private final String[] columnas = {
        "Nombre", "Usuario", "Cargo", "Tipo Documento", "N° Documento",
        "Correo", "Teléfono", "Estado"
    };

    private ArrayList<Empleado> listaEmpleado;

    public ModeloTablaEmpleado() {
        this.listaEmpleado = new ArrayList<>();
    }

    public void setListaEmpleado(ArrayList<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
        fireTableDataChanged();
    }

    public ArrayList<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public Empleado getEmpleadoEn(int fila) {
        if (fila >= 0 && fila < listaEmpleado.size()) {
            return listaEmpleado.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return listaEmpleado.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado empleado = listaEmpleado.get(rowIndex);
        switch (columnIndex) {
            case 0: return empleado.getNombre();
            case 1: return empleado.getUsuario();
            case 2: return (empleado.getCargo() != null) ? empleado.getCargo().getNombre() : "Sin cargo";
            case 3: return (empleado.getTipoDocumento() != null) ? empleado.getTipoDocumento().getNombre() : "Sin tipo";
            case 4: return empleado.getNroDocumento();
            case 5: return empleado.getCorreo();
            case 6: return empleado.getTelefono();
            case 7: return empleado.isEstado() ? "Activo" : "Inactivo";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 7: return String.class; // Estado como "Activo"/"Inactivo"
            default: return String.class;
        }
    }
}
