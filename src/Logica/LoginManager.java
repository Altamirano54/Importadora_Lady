/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Amir Altamirano
 */
public class LoginManager {
    public static boolean autenticar(String usuario, String contraseña/*, String cargo*/) {
        try {
            // Simulación de una autenticación (puedes cambiar por base de datos)
            if (usuario.equalsIgnoreCase("admin") && contraseña.equals("1234") /*&& cargo.equalsIgnoreCase("Administrador")*/) {
                return true;
            }

            if (usuario.equalsIgnoreCase("empleado") && contraseña.equals("abcd") /*&& cargo.equalsIgnoreCase("Empleado")*/) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("Error durante la autenticación: " + e.getMessage());
        }

        return false;
    }
}
