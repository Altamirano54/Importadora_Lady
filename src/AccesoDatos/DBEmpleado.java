/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Cargo;
import Entidades.Empleado;
import Entidades.Tipo_documento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Yenni
 */
public class DBEmpleado implements ICRUD {

    @Override
    public ArrayList listar() throws Exception {
        ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado AS e " +
                     "INNER JOIN cargo AS c ON e.id_cargo = c.id " +
                     "INNER JOIN tipo_documento AS td ON e.id_tipo_document = td.id";

        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // CARGO
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("c.id"));
                cargo.setNombre(rs.getString("c.nombre"));

                // TIPO DE DOCUMENTO
                Tipo_documento tipoDocumento = new Tipo_documento(
                    rs.getInt("td.id"),
                    rs.getString("td.nombre")
                );

                // EMPLEADO
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("e.id"));
                empleado.setNombre(rs.getString("e.nombre"));
                empleado.setUsuario(rs.getString("e.usuario"));
                empleado.setTipoDocumento(tipoDocumento);
                empleado.setNroDocumento(rs.getString("e.nro_documento"));
                empleado.setFechaNacimiento(rs.getDate("e.fecha_nacimiento"));
                empleado.setGenero(rs.getString("e.genero").charAt(0));
                empleado.setContrasena(rs.getString("e.contraseña"));
                empleado.setCargo(cargo);
                empleado.setDireccion(rs.getString("e.direccion"));
                empleado.setTelefono(rs.getString("e.telefono"));
                empleado.setCorreo(rs.getString("e.correo"));
                empleado.setFechaContratacion(rs.getTimestamp("e.fecha_contratacion"));
                empleado.setFechaUltimaActualizacion(rs.getTimestamp("e.fecha_ultima_actualizacion"));
                empleado.setFechaTerminacion(rs.getTimestamp("e.fecha_terminacion"));
                empleado.setEstado(rs.getBoolean("e.estado") ); // Convertir tinyint a boolean
                empleado.setUrl(rs.getString("e.url"));

                empleados.add(empleado);
            }

        } catch (SQLException e) {
            System.err.println("Error al Listar Empleado: " + e.getMessage());
            throw new SQLException("Error al Listar Empleado: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Empleado empleado = (Empleado) object;
        String sql = "INSERT INTO empleado (nombre, usuario, id_tipo_document, nro_documento, fecha_nacimiento, genero, contraseña, id_cargo, direccion, telefono, correo, fecha_contratacion, fecha_ultima_actualizacion, estado, url) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Fecha actual
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        empleado.setFechaContratacion(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getUsuario());
            ps.setInt(3, empleado.getTipoDocumento().getId());
            ps.setString(4, empleado.getNroDocumento());
            ps.setDate(5, (Date) empleado.getFechaNacimiento()); // asegúrate que sea java.sql.Date
            ps.setString(6, String.valueOf(empleado.getGenero())); // char → String
            ps.setString(7, empleado.getContrasena());
            ps.setInt(8, empleado.getCargo().getId());
            ps.setString(9, empleado.getDireccion());
            ps.setString(10, empleado.getTelefono());
            ps.setString(11, empleado.getCorreo());
            ps.setTimestamp(12, empleado.getFechaContratacion());
            ps.setTimestamp(13, fechaActual); // fecha_ultima_actualizacion
            ps.setInt(14, empleado.isEstado() ? 1 : 0);
            ps.setString(15, empleado.getUrl());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al Crear Empleado: " + e.getMessage());
            throw new SQLException("Error al Crear Empleado: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Empleado empleado = (Empleado) object;
        String sql = "UPDATE empleado SET nombre = ?, usuario = ?, id_tipo_document = ?, nro_documento = ?, fecha_nacimiento = ?, genero = ?, " +
                     "contraseña = ?, id_cargo = ?, direccion = ?, telefono = ?, correo = ?, fecha_ultima_actualizacion = ?, url = ? " +
                     "WHERE id = ?";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        empleado.setFechaUltimaActualizacion(fechaActual); // si lo tienes como atributo

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getUsuario());
            ps.setInt(3, empleado.getTipoDocumento().getId());
            ps.setString(4, empleado.getNroDocumento());
            ps.setDate(5, (Date) empleado.getFechaNacimiento()); // asegúrate que sea java.sql.Date
            ps.setString(6, String.valueOf(empleado.getGenero()));
            ps.setString(7, empleado.getContrasena());
            ps.setInt(8, empleado.getCargo().getId());
            ps.setString(9, empleado.getDireccion());
            ps.setString(10, empleado.getTelefono());
            ps.setString(11, empleado.getCorreo());
            ps.setTimestamp(12, empleado.getFechaUltimaActualizacion());
            ps.setString(13, empleado.getUrl());
            ps.setInt(14, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al Actualizar Empleado: " + e.getMessage());
            throw new SQLException("Error al Actualizar Empleado: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE empleado SET estado = 0 WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(SQLException e){
            System.err.println("Error al Eliminar Empleado: " + e.getMessage());
            throw new SQLException("Error al Eliminar Empleado: " + e.getMessage());
        }
    }

    @Override
    public Empleado get(int id) throws Exception {
        Empleado empleado = null;
        String sql = "SELECT * FROM empleado AS e " +
                     "INNER JOIN cargo AS c ON e.id_cargo = c.id " +
                     "INNER JOIN tipo_documento AS td ON e.id_tipo_document = td.id " +
                     "WHERE e.id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setId(rs.getInt("e.id_cargo"));
                    cargo.setNombre(rs.getString("c.nombre"));

                    Tipo_documento tipoDoc = new Tipo_documento(
                        rs.getInt("e.id_tipo_document"),
                        rs.getString("td.nombre")
                    );

                    empleado = new Empleado();
                    empleado.setId(rs.getInt("e.id"));
                    empleado.setNombre(rs.getString("e.nombre"));
                    empleado.setUsuario(rs.getString("e.usuario"));
                    empleado.setTipoDocumento(tipoDoc);
                    empleado.setNroDocumento(rs.getString("e.nro_documento"));
                    empleado.setFechaNacimiento(rs.getDate("e.fecha_nacimiento"));
                    empleado.setGenero(rs.getString("e.genero").charAt(0));
                    empleado.setContrasena(rs.getString("e.contraseña"));
                    empleado.setCargo(cargo);
                    empleado.setDireccion(rs.getString("e.direccion"));
                    empleado.setTelefono(rs.getString("e.telefono"));
                    empleado.setCorreo(rs.getString("e.correo"));
                    empleado.setFechaContratacion(rs.getTimestamp("e.fecha_contratacion"));
                    empleado.setFechaUltimaActualizacion(rs.getTimestamp("e.fecha_ultima_actualizacion"));
                    empleado.setFechaTerminacion(rs.getTimestamp("e.fecha_terminacion")); // puede ser null
                    empleado.setEstado(rs.getBoolean("e.estado"));
                    empleado.setUrl(rs.getString("e.url"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al Obtener Empleado id(" + id + "): " + e.getMessage());
            throw new SQLException("Error al Obtener Empleado id(" + id + "): " + e.getMessage());
        }
        return empleado;
    }

}
