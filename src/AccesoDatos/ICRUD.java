/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package AccesoDatos;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Amir Altamirano
 */
public interface ICRUD {
    public ArrayList listar() throws Exception;
    public int crear(Object object) throws SQLException;
    public void actualizar(int id, Object object) throws Exception;
    public void eliminar() throws Exception;
    public Object get(int id) throws Exception;
}
