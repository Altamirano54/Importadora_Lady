/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

import Entidades.Tipo_documento;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Yenni
 */
public class ModeloComboboxTipoDocumento extends AbstractListModel implements ComboBoxModel {

   
    private ArrayList<Tipo_documento> listadoTipoDocumento;
    
    
    private Tipo_documento seleccionado = null;


    public ModeloComboboxTipoDocumento() {
        this.listadoTipoDocumento = new ArrayList<>();
    }
    
    /**
     * Constructor que inicializa el modelo con una lista de tipos de documento.
     * @param listado La lista inicial de tipos de documento.
     */
    public ModeloComboboxTipoDocumento(ArrayList<Tipo_documento> listado) {
        this.listadoTipoDocumento = listado;
    }

    public ArrayList<Tipo_documento> getListadoTipoDocumento() {
        return listadoTipoDocumento;
    }

    public void setListadoTipoDocumento(ArrayList<Tipo_documento> listadoTipoDocumento) {
        this.listadoTipoDocumento = listadoTipoDocumento;
    }

    public Tipo_documento getSeleccionado() {
        return seleccionado;
    }

    /**
     * Devuelve el número de elementos en la lista.
     * El JComboBox usa este método para saber cuántos ítems mostrar.
     * @return El tamaño de la lista de tipos de documento.
     */
    @Override
    public int getSize() {
        return (listadoTipoDocumento != null) ? listadoTipoDocumento.size() : 0;
    }

    /**
     * Devuelve el elemento en una posición específica (índice).
     * El JComboBox usa este método para obtener el texto que debe mostrar para cada ítem.
     * @param index La posición del elemento.
     * @return El resultado del método toString() del objeto Tipo_documento en esa posición.
     */
    @Override
    public Object getElementAt(int index) {
        return (listadoTipoDocumento != null) ? listadoTipoDocumento.get(index) : null;
    }

    /**
     * Se ejecuta cuando el usuario selecciona un ítem en el ComboBox.
     * Busca el objeto Tipo_documento correspondiente al texto seleccionado y lo guarda.
     * @param anItem El ítem seleccionado (generalmente un String).
     */
    @Override
    public void setSelectedItem(Object anItem) {
        this.seleccionado = null;
        if (this.listadoTipoDocumento != null && anItem != null) {
            for (Tipo_documento tipoDoc : this.listadoTipoDocumento) {
                // Compara el texto del objeto con el texto seleccionado.
                if (tipoDoc.toString().equals(anItem.toString())) {
                    this.seleccionado = tipoDoc; // Guarda el objeto completo.
                    return;
                }
            }
        }
    }

    /**
     * Devuelve el objeto completo que está seleccionado.
     * Es muy útil para obtener el ID y el nombre del tipo de documento seleccionado.
     * @return El objeto Tipo_documento seleccionado.
     */
    @Override
    public Object getSelectedItem() {
        return this.seleccionado;
    }
    
    /**
     * Busca un objeto Tipo_documento en la lista y devuelve su índice.
     * Es útil para establecer la selección del ComboBox programáticamente.
     * @param td El objeto Tipo_documento a buscar.
     * @return El índice del objeto en la lista, o -1 si no se encuentra.
     */
    public int getSelectTipoDocumento(Tipo_documento td) {
        if (this.listadoTipoDocumento != null && td != null) {
            for (int i = 0; i < listadoTipoDocumento.size(); i++) {
                Tipo_documento tipoActual = listadoTipoDocumento.get(i);
                // Compara los IDs para una coincidencia exacta.
                if (tipoActual.getId() == td.getId()) {
                    return i;
                }
            }
        }
        return -1;
    }
}