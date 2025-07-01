/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import AccesoDatos.BDProveedor;
import java.util.ArrayList;
import Entidades.Proveedor;
import java.sql.SQLException;
/**
 *
 * @author Redstone
 */
public class ProveeedorManager {
    private BDProveedor bDProveedor;

    public ProveeedorManager() {
        bDProveedor=new BDProveedor();
    }
    
    public ArrayList<Proveedor> lista() throws Exception{
        ArrayList<Proveedor> proveedores=bDProveedor.listar();
        if(proveedores.size()==0){
            throw new Exception("no se encontraron proveedores");
        }
        return proveedores;
    }
    
    public int crear(Proveedor proveedor) throws SQLException{
        if(validarDatos(proveedor)){
          int idProveedor= bDProveedor.crear(proveedor); 
          return idProveedor;
        }
        return -1;
        
    }
    
    public void actualizar(Proveedor proveedor) throws Exception{
        if(validarDatos(proveedor)){
          bDProveedor.actualizar(proveedor.getId(), proveedor);  
        }
        
    }
    
    public void eliminar(Proveedor proveedor) throws Exception{
        if(validarDatos(proveedor)){
          bDProveedor.eliminar(proveedor.getId());  
        }
    }
    
    public boolean validarDatos(Proveedor proveedor){
        try {
            String ruc = proveedor.getRuc();
            if(ruc.length()!=11){
                return false;
            }
            
            Long.parseLong(ruc);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e){
            return false;
        }

    }
    
    
}
