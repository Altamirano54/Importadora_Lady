/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Cargo;
import Entidades.Empleado;
import Entidades.Tipo_documento;
import java.sql.Connection;
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
        String sql = "SELECT * FROM empleado AS e INNER JOIN cargo AS c ON e.id_cargo=c.id"+
                " JOIN tipo_documento AS td ON e.id_tipo_document=td.id";

        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("e.id_cargo"));
                cargo.setNombre(rs.getString("c.nombre"));
                Empleado empleado = new Empleado(
                        rs.getInt("e.id"),
                        rs.getString("e.nombre"),
                        rs.getString("e.contraseña"),
                        cargo,
                        rs.getTimestamp("e.fecha_creacion"),
                        rs.getBoolean("e.estado")
                    );
                    
                    empleado.setTipo_documento(new Tipo_documento(rs.getInt("e.id_tipo_document"),rs.getString("td.nombre")));
                    empleado.setNro_documento(rs.getString("e.nro_documento"));
                    empleado.setUrl(rs.getString("url"));
                   empleados.add(empleado);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Empleado: " + e.getMessage());
            throw new SQLException("Error al Listar Empleado: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Empleado empleado = (Empleado) object;
        String sql = "INSERT INTO empleado (nombre, contraseña, id_cargo, fecha_creacion, estado, id_tipo_document, nro_documento, url) "
                + "VALUES (?, ?, ?, ?,1, ?, ?)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        empleado.setFechaCreacion(fechaActual);

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getContrasena());
            ps.setInt(3, empleado.getCargo().getId());
            ps.setTimestamp(4, empleado.getFechaCreacion());
            ps.setInt(5, empleado.getTipo_documento().getId());
            ps.setString(6, empleado.getNro_documento());
            ps.setString(7, empleado.getUrl());

            return ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Crear Empleado: " + e.getMessage());
            throw new SQLException("Error al Crear Empleado: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Empleado empleado = (Empleado) object;
        String sql = "UPDATE empleado SET nombre = ?, contraseña = ?, id_cargo = ? id_tipo_document=?, nro_documento=?, url = ? "
                + "WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getContrasena());
            ps.setInt(3, empleado.getCargo().getId());
            ps.setInt(4, empleado.getTipo_documento().getId());
            ps.setString(5, empleado.getNro_documento());
            ps.setString(6, empleado.getUrl());
            ps.setInt(7, id);

            ps.executeUpdate();
        }catch(SQLException e){
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
    public Object get(int id) throws Exception {
        Empleado empleado = null;
        String sql = "SELECT * FROM empleado AS e INNER JOIN cargo AS c ON e.id_cargo=c.id"+
                " JOIN tipo_documento AS td ON e.id_tipo_document=td.id"+
                " WHERE e.id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setId(rs.getInt("e.id_cargo"));
                    cargo.setNombre(rs.getString("c.nombre"));
                    empleado = new Empleado(
                            rs.getInt("e.id"),
                            rs.getString("e.nombre"),
                            rs.getString("e.contraseña"),
                            cargo,
                            rs.getTimestamp("e.fecha_creacion"),
                            rs.getBoolean("e.estado")
                    );
                    
                    empleado.setTipo_documento(new Tipo_documento(rs.getInt("e.id_tipo_document"),rs.getString("td.nombre")));
                    empleado.setNro_documento(rs.getString("e.nro_documento"));
                    empleado.setUrl(rs.getString("url"));
                }
            }
        }catch(SQLException e){
            System.err.println("Error al Obtener Empleado id("+id+"): "+ e.getMessage());
            throw new SQLException("Error al Obtener Empleado id("+id+"): "+ e.getMessage());
        }

        return empleado;
    }

}
