/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.DBEmpleado;
import Entidades.Empleado;
import java.util.ArrayList;
/**
 *
 * @author Amir Altamirano
 */
public class LoginManager {
    private DBEmpleado bEmpleado;

    public LoginManager() {
        this.bEmpleado=new DBEmpleado();
    }
    
    public boolean autenticar(String usuario, String contraseña/*, String cargo*/) {
        try {
            ArrayList<Empleado> empleadosArrayList=bEmpleado.listar();
            System.out.println(empleadosArrayList.size());
            for (Empleado object : empleadosArrayList) {
                System.out.println(object.toString());
                if (object.getNombre().equals(usuario) && object.getContrasena().equals(contraseña) ) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println("Error durante la autenticación: " + e.getMessage());
        }

        return false;
    }
}
