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

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author Amir Altamirano
 */
public class LoginManager {
    public String codigo="";

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

    public Empleado autenticar(String usuario, String contraseña, String cargo) {
        try {
            ArrayList<Empleado> empleadosArrayList = bEmpleado.listar();
            System.out.println(empleadosArrayList.size());
            for (Empleado object : empleadosArrayList) {
                if (object.getUsuario().equals(usuario) && object.getContrasena().equals(contraseña) && object.getCargo().toString().equals(cargo)) {
                    System.out.println("id: "+object.getId());
                    return object;
                }
            }

        } catch (Exception e) {
            System.err.println("Error durante la autenticación: " + e.getMessage());
        }

        return null;
    }
    
    public void enviarCodigo(String correoDestino) {
        codigo = generarCodigoAleatorio();
        
        // Credenciales del remitente
        String correoEnvia = "amiraltamirano54@gmail.com";
        String contrasena = "dfey mfge ibma ofhw"; // usa contraseña de aplicación si es Gmail

        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        propiedad.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session sesion = Session.getInstance(propiedad, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnvia, contrasena);
            }
        });

        try {
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoEnvia));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            mensaje.setSubject("Código de verificación");
            mensaje.setText("Tu código de verificación es: " + codigo);

            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
                System.out.println("Error al enviar correo: " + e.getMessage());
                e.printStackTrace();
            }
        
    }
    
    public String generarCodigoAleatorio() {
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int digito = random.nextInt(10); // genera un número entre 0 y 9
            codigo.append(digito);
        }

        return codigo.toString();
    }
    
    public Empleado buscarEmpleado(String usuario, String nroDocumento){
        Empleado empleado=null;
        try {
            ArrayList<Empleado> empleadosArrayList = bEmpleado.listar();
            System.out.println(empleadosArrayList.size());
            for (Empleado object : empleadosArrayList) {
                System.out.println(object.toString());
                if (object.getUsuario().equals(usuario) && object.getNroDocumento().equals(nroDocumento) ) {
                    return object;
                }
            }

        } catch (Exception e) {
            System.err.println("Error durante la autenticación: " + e.getMessage());
        }
        return empleado;
    }
    
    public void CambiarContraseña(Empleado e, String nuevaContraseña){
        try {
            bEmpleado.cambiarContraseña(e.getId(), nuevaContraseña);
        } catch (Exception ex) {
           System.err.println("Error Al cambiar contraseña: " + ex.getMessage());
        }
    }
}
