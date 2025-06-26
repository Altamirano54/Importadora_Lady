/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import AccesoDatos.DBEmpleado;
import AccesoDatos.BDCargo;
import Entidades.Empleado;
import Entidades.Cargo;
import java.util.ArrayList;

/**
 *
 * @author Amir Altamirano
 */
public class LoginManager {

    private DBEmpleado bEmpleado;
    private BDCargo bdCargo;

    public LoginManager() {
        this.bEmpleado = new DBEmpleado();
        this.bdCargo = new BDCargo();
    }

    /**
     * Obtiene la lista de todos los cargos activos desde la base de datos.
     *
     * @return Un ArrayList de objetos Cargo.
     * @throws Exception Si ocurre un error durante la consulta a la base de
     * datos.
     */
    public ArrayList<Cargo> cargarCargos() throws Exception {
        // Llama al método listar de la capa de acceso a datos
        return bdCargo.listar();
    }

    public boolean autenticar(String usuario, String contraseña/*, String cargo*/) {
        try {
            ArrayList<Empleado> empleadosArrayList = bEmpleado.listar();
            System.out.println(empleadosArrayList.size());
            for (Empleado object : empleadosArrayList) {
                System.out.println(object.toString());
                if (object.getNombre().equals(usuario) && object.getContrasena().equals(contraseña)) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.err.println("Error durante la autenticación: " + e.getMessage());
        }

        return false;
    }
}
