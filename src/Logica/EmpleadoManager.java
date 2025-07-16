/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import AccesoDatos.DBEmpleado;
import Entidades.Cargo;
import Entidades.Empleado;
import Entidades.Tipo_documento;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author Amir Altamirano
 */
public class EmpleadoManager {
    private DBEmpleado bDEmpleado=new DBEmpleado();
    
    public ArrayList<Empleado> Listar() throws Exception{
        return bDEmpleado.listar();
    }
    
    public boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    public boolean validarFechaNacimiento(Date fecha) {
        if (fecha == null) return false;
        long edadEnMilis = System.currentTimeMillis() - fecha.getTime();
        long edadEnAnios = edadEnMilis / (1000L * 60 * 60 * 24 * 365);
        return edadEnAnios >= 18;
    }

    public boolean validarDocumento(String nroDocumento) {
        return nroDocumento != null && nroDocumento.matches("\\d{8,16}");
    }

    public boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }

    public boolean validarCorreo(String correo) {
        if (correo == null) return false;
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, correo);
    }

    public boolean validarDireccion(String direccion) {
        return direccion != null && !direccion.trim().isEmpty();
    }

    public boolean validarUsuario(String usuario) {
        return usuario != null && usuario.length() >= 4;
    }

    public boolean validarContrasena(String contrasena) {
        return contrasena != null && contrasena.length() >= 6;
    }

    public boolean validarContrasenasIguales(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    public boolean validarGenero(char genero) {
        return genero == 'M' || genero == 'F';
    }

    public boolean validarSeleccionCombo(int idSeleccionado) {
        return idSeleccionado >= 0; // Asumiendo que el índice 0 es "Seleccione..."
    }

    // Validación general con mensajes opcionales
    public boolean validarTodo(String nombre, Date fechaNacimiento, String nroDocumento,
            char genero, String telefono, String direccion, String correo,
            String usuario, String contrasena, String confirmarContrasena,
            int idTipoDoc, int idCargo) {

        if (!validarNombre(nombre)) {
            mostrarError("El nombre no puede estar vacío.");
            return false;
        }
        if (!validarFechaNacimiento(fechaNacimiento)) {
            mostrarError("Debe ser mayor de edad.");
            return false;
        }
        if (!validarDocumento(nroDocumento)) {
            mostrarError("Número de documento inválido (mínimo 8 dígitos).");
            return false;
        }
        if (!validarGenero(genero)) {
            mostrarError("Seleccione un género.");
            return false;
        }
        if (!validarTelefono(telefono)) {
            mostrarError("Teléfono inválido (9 dígitos).");
            return false;
        }
        if (!validarDireccion(direccion)) {
            mostrarError("La dirección no puede estar vacía.");
            return false;
        }
        if (!validarCorreo(correo)) {
            mostrarError("Correo electrónico inválido.");
            return false;
        }
        if (!validarUsuario(usuario)) {
            mostrarError("Usuario muy corto (mínimo 4 caracteres).");
            return false;
        }
        if (!validarContrasena(contrasena)) {
            mostrarError("Contraseña muy corta (mínimo 6 caracteres).");
            return false;
        }
        if (!validarContrasenasIguales(contrasena, confirmarContrasena)) {
            mostrarError("Las contraseñas no coinciden.");
            return false;
        }
        if (!validarSeleccionCombo(idTipoDoc)) {
            mostrarError("Seleccione un tipo de documento válido.");
            return false;
        }
        if (!validarSeleccionCombo(idCargo)) {
            mostrarError("Seleccione un cargo válido.");
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Validación", JOptionPane.ERROR_MESSAGE);
    }
    
    public void crearEmpleadoDesdeFormulario(
            String nombre,
            String usuario,
            Tipo_documento tipoDoc,
            String nroDocumento,
            Date fechaNacimiento,
            char genero,
            String contrasena,
            Cargo cargo,
            String direccion,
            String telefono,
            String correo,
            String url
    ) throws SQLException {
        Timestamp ahora = new Timestamp(System.currentTimeMillis());

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setUsuario(usuario);
        empleado.setTipoDocumento(tipoDoc);
        empleado.setNroDocumento(nroDocumento);
        empleado.setFechaNacimiento(fechaNacimiento);
        empleado.setGenero(genero);
        empleado.setContrasena(contrasena);
        empleado.setCargo(cargo);
        empleado.setDireccion(direccion);
        empleado.setTelefono(telefono);
        empleado.setCorreo(correo);
        empleado.setFechaContratacion(ahora);
        empleado.setFechaUltimaActualizacion(ahora);
        empleado.setEstado(true); // por defecto activo
        empleado.setFechaTerminacion(null); // aún no ha terminado
        empleado.setUrl(url);

        bDEmpleado.crear(empleado);
    }
    
    public void actualizarEmpleadoDesdeFormulario(int id, String nombre, Tipo_documento tipoDoc, String nroDoc,
        java.sql.Date fechaNacimiento, char genero, Cargo cargo, String direccion, String telefono, 
        String correo, String rutaImagen) throws SQLException, Exception {

        Empleado emp = new Empleado();
        emp.setId(id);
        emp.setNombre(nombre);
        emp.setTipoDocumento(tipoDoc);
        emp.setNroDocumento(nroDoc);
        emp.setFechaNacimiento(fechaNacimiento);
        emp.setGenero(genero);
        emp.setCargo(cargo);
        emp.setDireccion(direccion);
        emp.setTelefono(telefono);
        emp.setCorreo(correo);
        emp.setUrl(rutaImagen);
        emp.setEstado(true); // Puedes actualizar según tu lógica

        bDEmpleado.actualizar(emp.getId(),emp); // Este método lo defines tú
    }
    
    public String guardarImagen(BufferedImage imagen) {
        // Crear la carpeta si no existe
        String carpeta = "imagenes_Empleados";
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Usar java.sql.Date pero convertirlo explícitamente
        Date fechaSQL = new Date(System.currentTimeMillis());
        java.util.Date fechaUtil = new java.util.Date(fechaSQL.getTime()); // conversión segura

        // Crear nombre de archivo con timestamp
        String nombreArchivo = "img_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(fechaUtil) + ".png";
        File archivo = new File(directorio, nombreArchivo);

        try {
            ImageIO.write(imagen, "png", archivo);
            return archivo.getAbsolutePath(); // Retornar ruta absoluta
        } catch (IOException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
            return null;
        }
    }
}
