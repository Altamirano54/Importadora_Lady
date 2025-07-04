/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

import Entidades.Proveedor;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Amir Altamirano
 */
public class ModeloComboboxProveedores extends AbstractListModel implements ComboBoxModel{
    private ArrayList<Proveedor> listadoProveedor;
    private Proveedor seleccionado = null;

    public ArrayList<Proveedor> getListadoProveedor() {
        return listadoProveedor;
    }

    public void setListadoProveedor(ArrayList<Proveedor> listadoProveedor) {
        this.listadoProveedor = listadoProveedor;
    }

    public Proveedor getSeleccionado() {
        return seleccionado;
    }

    @Override
    public int getSize() {
        return (listadoProveedor != null) ? listadoProveedor.size() : 0;
    }

    @Override
    public String getElementAt(int index) {
        if (listadoProveedor != null) {
            return listadoProveedor.get(index).toString(); // asegúrate que `Proveedor` tenga un toString() informativo
        }
        return "";
    }

    @Override
    public void setSelectedItem(Object anItem) {
        seleccionado = null;
        if (listadoProveedor != null && anItem != null) {
            for (Proveedor p : listadoProveedor) {
                if (p.toString().equals(anItem.toString())) {
                    seleccionado = p;
                    return;
                }
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        return (seleccionado != null) ? seleccionado : "";
    }

    public int getSelectProveedor(Proveedor p) {
        if (listadoProveedor != null && p != null) {
            for (int i = 0; i < listadoProveedor.size(); i++) {
                if (listadoProveedor.get(i).toString().equals(p.toString())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
