/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BDProveedor implements ICRUD {

    @Override
    public ArrayList<Proveedor> listar() throws Exception {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE estado = 1";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("id"),
                        rs.getString("ruc"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getString("contacto"),
                        rs.getTimestamp("fecha_modificacion"),
                        rs.getTimestamp("fecha_creacion"),
                        true
                );
                proveedores.add(proveedor);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Proveedor: " + e.getMessage());
            throw new SQLException("Error al Listar Proveedor: " + e.getMessage());
        }

        return proveedores;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Proveedor proveedor = (Proveedor) object;
        String sql = "INSERT INTO proveedor (nombre, direccion, correo, contacto, estado, fecha_creacion, fecha_modificacion, ruc) " +
                     "VALUES (?, ?, ?, ?, 1, ?, ?,?)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        proveedor.setFechaCreacion(fechaActual);
        proveedor.setFechaModificacion(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getContacto());
            ps.setTimestamp(5, proveedor.getFechaCreacion());
            ps.setTimestamp(6, proveedor.getFechaModificacion());
            ps.setString(7, proveedor.getRuc());

            return ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Crear Proveedor: " + e.getMessage());
            throw new SQLException("Error al Crear Proveedor: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Proveedor proveedor = (Proveedor) object;
        String sql = "UPDATE proveedor SET nombre = ?, direccion = ?, correo = ?, contacto = ?, fecha_modificacion = ?, ruc=? " +
                     "WHERE id = ?";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        proveedor.setFechaModificacion(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getContacto());
            ps.setTimestamp(5, proveedor.getFechaModificacion());
            ps.setString(6, proveedor.getRuc());
            ps.setInt(7, id);

            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Actualizar Proveedor: " + e.getMessage());
            throw new SQLException("Error al Actualizar Proveedor: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE proveedor SET estado = 0, fecha_modificacion = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, fechaActual);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch(SQLException e){
            System.err.println("Error al Eliminar Proveedor: " + e.getMessage());
            throw new SQLException("Error al Eliminar Proveedor: " + e.getMessage());
        }
    }

    @Override
    public Proveedor get(int id) throws Exception {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM proveedor WHERE id = ? AND estado = 1";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor(
                            rs.getInt("id"),
                            rs.getString("ruc"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("correo"),
                            rs.getString("contacto"),
                            rs.getTimestamp("fecha_modificacion"),
                            rs.getTimestamp("fecha_creacion"),
                            true
                    );
                }
            }
        }catch(SQLException e){
            System.err.println("Error al Obtener Proveedor id("+id+"): " + e.getMessage());
            throw new SQLException("Error al Obtener Proveedor id("+id+"): " + e.getMessage());
        }

        return proveedor;
    }
}
