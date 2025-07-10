/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.BDEstadoSolicitud;
import Entidades.EstadoSolicitud;

import java.util.ArrayList;

/**
 *
 * @author Amir Altamirano
 */
public class EstadoSolicitudManager {
    private BDEstadoSolicitud bDEstadoSolicitud;

    public EstadoSolicitudManager() {
        bDEstadoSolicitud=new BDEstadoSolicitud();
    }
    
    public ArrayList<EstadoSolicitud> listar() throws Exception{
        return bDEstadoSolicitud.listar();
    }
    
    public EstadoSolicitud EstadoSiguiente(EstadoSolicitud es) throws Exception{
        EstadoSolicitud estado=es;
        if(es.getNombre().equals("En proceso")){
            estado = bDEstadoSolicitud.get(4);
        }else if(es.getNombre().equals("En espera")){
            estado= bDEstadoSolicitud.get(2);
        }
        return estado;
    }
    
    public EstadoSolicitud EstadoSiguienteCompra(EstadoSolicitud es) throws Exception{
        EstadoSolicitud estado=es;
        if(es.getNombre().equals("En proceso")){
            estado = bDEstadoSolicitud.get(5);
        }else if(es.getNombre().equals("En espera")){
            estado= bDEstadoSolicitud.get(2);
        }
        return estado;
    }
}

