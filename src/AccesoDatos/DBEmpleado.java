/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Cargo;
import Entidades.Empleado;
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
        String sql = "SELECT * FROM empleado";

        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id_cargo"));
                Empleado empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña"),
                        cargo,
                        rs.getTimestamp("fecha_creacion"),
                        rs.getBoolean("estado")
                );
                empleados.add(empleado);
            }
        }

        return empleados;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Empleado empleado = (Empleado) object;
        String sql = "INSERT INTO empleado (nombre, contraseña, id_cargo, fecha_creacion, estado) "
                + "VALUES (?, ?, ?, ?,1)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        empleado.setFechaCreacion(fechaActual);

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getContrasena());
            ps.setInt(3, empleado.getCargo().getId());
            ps.setTimestamp(4, empleado.getFechaCreacion());

            return ps.executeUpdate();
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Empleado empleado = (Empleado) object;
        String sql = "UPDATE empleado SET nombre = ?, contraseña = ?, id_cargo = ?"
                + "WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getContrasena());
            ps.setInt(3, empleado.getCargo().getId());
            ps.setInt(4, id);

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE empleado SET estado = 0 WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al eliminar empleado: " + e.getMessage(), e);
        }
    }

    @Override
    public Object get(int id) throws Exception {
        Empleado empleado = null;
        String sql = "SELECT * FROM empleado WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setId(rs.getInt("id_cargo"));
                    empleado = new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("contraseña"),
                            cargo,
                            rs.getTimestamp("fecha_creacion"),
                            rs.getBoolean("estado")
                    );
                }
            }
        }

        return empleado;
    }

}
