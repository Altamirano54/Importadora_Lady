/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author Amir Altamirano
 */
public class Empleado {

    private int id;
    private String nombre;
    private String usuario;
    private Tipo_documento tipoDocumento;
    private String nroDocumento;
    private Date fechaNacimiento;
    private char genero;
    private String contrasena;
    private Cargo cargo;
    private String direccion;
    private String telefono;
    private String correo;
    private Timestamp fechaContratacion;
    private Timestamp fechaUltimaActualizacion;
    private boolean estado;
    private Timestamp fechaTerminacion;
    private String url;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String usuario, Tipo_documento tipoDocumento, String nroDocumento,
                    Date fechaNacimiento, char genero, String contrasena, Cargo cargo, String direccion,
                    String telefono, String correo, Timestamp fechaContratacion, Timestamp fechaUltimaActualizacion,
                    boolean estado, Timestamp fechaTerminacion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.contrasena = contrasena;
        this.cargo = cargo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaContratacion = fechaContratacion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.estado = estado;
        this.fechaTerminacion = fechaTerminacion;
        this.url = url;
    }

    // Getters y Setters

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public Tipo_documento getTipoDocumento() { return tipoDocumento; }

    public void setTipoDocumento(Tipo_documento tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNroDocumento() { return nroDocumento; }

    public void setNroDocumento(String nroDocumento) { this.nroDocumento = nroDocumento; }

    public Date getFechaNacimiento() { return fechaNacimiento; }

    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public char getGenero() { return genero; }

    public void setGenero(char genero) { this.genero = genero; }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Cargo getCargo() { return cargo; }

    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }

    public Timestamp getFechaContratacion() { return fechaContratacion; }

    public void setFechaContratacion(Timestamp fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public Timestamp getFechaUltimaActualizacion() { return fechaUltimaActualizacion; }

    public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public boolean isEstado() { return estado; }

    public void setEstado(boolean estado) { this.estado = estado; }

    public Timestamp getFechaTerminacion() { return fechaTerminacion; }

    public void setFechaTerminacion(Timestamp fechaTerminacion) { this.fechaTerminacion = fechaTerminacion; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return nombre ;
    }
}