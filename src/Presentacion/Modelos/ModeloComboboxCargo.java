/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Modelos;

import Entidades.Cargo;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Amir Altamirano
 */
public class ModeloComboboxCargo extends AbstractListModel implements ComboBoxModel {

    private ArrayList<Cargo> listadoCargo;
    private Cargo seleccionado = null;

    public ArrayList<Cargo> getListadoTipoCliente() {
        return listadoCargo;
    }

    public void setListadoCargo(ArrayList<Cargo> listadoCargo) {
        this.listadoCargo = listadoCargo;
    }   

    public Cargo getSeleccionado() {
        return seleccionado;
    }
    
    
    
    
    @Override
    public int getSize() {
        int cantElem = 0;
        
        if(this.listadoCargo != null) {        
            cantElem = this.listadoCargo.size();
        }
        
        return cantElem;
    }

    @Override
    public Object getElementAt(int index) {
        Object elem = "";
        if(this.listadoCargo != null) { 
            elem = this.listadoCargo.get(index);
        }
        return elem;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.seleccionado = null;
        if(this.listadoCargo != null && anItem != null) {
            for (Cargo cargo : this.listadoCargo) {
                if (cargo.equals(anItem) || cargo.toString().equals(anItem.toString())){
                    this.seleccionado = cargo;
                    return;
                }
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        Object elem = "";
        if(this.seleccionado != null) {
            elem = this.seleccionado;
        }
        return elem;
    }
    
    
    public int getSelectCargo(Cargo c){
        int seleccionado = -1;
        if(this.listadoCargo != null && c != null) {
            System.err.println("td enviado if1 : "+ c.toString());
            for (int i = 0; i < listadoCargo.size(); i++) {
                Cargo cargo = listadoCargo.get(i);
                if( cargo.toString().equals(c.toString()) ) {
                    seleccionado=i;
                    return seleccionado;
                }
                
            }
        }
        return seleccionado;
    }
    
}
