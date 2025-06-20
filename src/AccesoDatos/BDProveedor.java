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
        }

        return proveedores;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Proveedor proveedor = (Proveedor) object;
        String sql = "INSERT INTO proveedor (nombre, direccion, correo, contacto, estado, fecha_creacion, fecha_modificacion) " +
                     "VALUES (?, ?, ?, ?, 1, ?, ?)";

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

            return ps.executeUpdate();
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Proveedor proveedor = (Proveedor) object;
        String sql = "UPDATE proveedor SET nombre = ?, direccion = ?, correo = ?, contacto = ?, fecha_modificacion = ? " +
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
            ps.setInt(6, id);

            ps.executeUpdate();
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
        } catch (Exception e) {
            throw new Exception("Error al eliminar proveedor: " + e.getMessage(), e);
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
        }

        return proveedor;
    }
}
