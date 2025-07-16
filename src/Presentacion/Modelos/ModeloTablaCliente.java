/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

import Entidades.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yenni 
 */
public class ModeloTablaCliente extends AbstractTableModel {

    // Define los nombres de las columnas que se mostrarán en la JTable.
    private final String[] columnas = {
        "ID","Nombre", "Tipo Doc.", "Nro. Documento", "Teléfono", "Direccion"
    };

    // Almacena la lista de objetos Cliente que se mostrarán en la tabla.
    private ArrayList<Cliente> listadoCliente;

    /**
     * Constructor por defecto. Inicializa la lista de clientes vacía.
     */
    public ModeloTablaCliente() {
        this.listadoCliente = new ArrayList<>();
    }

    /**
     * Actualiza la lista de clientes del modelo y notifica a la JTable
     * que los datos han cambiado para que se repinte.
     * @param listadoCliente La nueva lista de clientes.
     */
    public void setListadoCliente(ArrayList<Cliente> listadoCliente) {
        this.listadoCliente = listadoCliente;
        fireTableDataChanged(); // Notificación crucial para actualizar la vista.
    }

    public ArrayList<Cliente> getListadoCliente() {
        return listadoCliente;
    }

    /**
     * Devuelve el objeto Cliente completo de una fila específica.
     * Muy útil cuando el usuario selecciona una fila y necesitas todos sus datos.
     * @param fila El índice de la fila seleccionada.
     * @return El objeto Cliente en esa fila, o null si el índice es inválido.
     */
    public Cliente getCliente(int fila) {
        if (fila >= 0 && fila < listadoCliente.size()) {
            return listadoCliente.get(fila);
        }
        return null;
    }

    // --- MÉTODOS SOBREESCRITOS DE AbstractTableModel ---

    @Override
    public int getRowCount() {
        return (listadoCliente != null) ? listadoCliente.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    /**
     * Devuelve el valor a mostrar en una celda específica de la tabla.
     * La JTable llama a este método para pintar cada celda.
     * @param rowIndex El índice de la fila.
     * @param columnIndex El índice de la columna.
     * @return El dato correspondiente a la celda.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = listadoCliente.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.getId();
            case 1: return c.getNombre();
            case 2: return (c.getTipo_documento() != null) ? c.getTipo_documento().getNombre() : "N/A";
            case 3: return c.getNro_documento();
            case 4: return c.getTelefono();
            case 5: return c.getDireccion();

            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    /**
     * Devuelve la clase de datos de cada columna.
     * Ayuda a la JTable a usar los renderizadores y editores correctos.
     * Por ejemplo, los números se alinearán a la derecha.
     */
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