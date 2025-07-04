/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Compra;
import Entidades.Empleado;
import Entidades.EstadoSolicitud;
import Entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class BDCompra implements ICRUD {

    @Override
    public ArrayList<Compra> listar() throws Exception {
        ArrayList<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id_empleado"));
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id_proveedor"));
                EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                estadoSolicitud.setId(rs.getInt("id_estadoSolicitud"));
                Compra compra = new Compra(
                        rs.getInt("id"),
                        empleado,
                        proveedor,
                        estadoSolicitud,
                        rs.getFloat("Total"),
                        rs.getTimestamp("fecha"),
                        rs.getBoolean("estado")
                );
                compras.add(compra);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Compra: " + e.getMessage());
        }
        return compras;
    }

    @Override
    public int crear(Object object) throws SQLException {
        int id=-1;
        Compra compra = (Compra) object;
        String sql = "INSERT INTO compra (id_empleado, id_proveedor, id_estadoSolicitud, Total, fecha)"
                + " VALUES (?, ?, ?, ?, ?)";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        compra.setFecha(fechaActual);
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, compra.getEmpleado().getId());
            ps.setInt(2, compra.getProveedor().getId());
            ps.setFloat(3, compra.getEstadoSolicitud().getId());
            ps.setFloat(4, compra.getTotal());
            ps.setTimestamp(5, compra.getFecha());
            id= ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Crear una Compra: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Compra compra = (Compra) object;
        String sql = "UPDATE compra SET id_empleado = ?, id_proveedor = ?, id_estadoSolicitud = ?, Total = ?, fecha = ? "
                + "WHERE id = ?";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        compra.setFecha(fechaActual);

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, compra.getEmpleado().getId());
            ps.setInt(2, compra.getProveedor().getId());
            ps.setInt(3, compra.getEstadoSolicitud().getId());
            ps.setFloat(4, compra.getTotal());
            ps.setTimestamp(5, compra.getFecha());
            ps.setInt(6, id);

            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Actualizar Compra: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE compra SET id_estadoSolicitud = 1, fecha = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, fechaActual);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al eliminar compra: " + e.getMessage(), e);
        }
    }

    @Override
    public Object get(int id) throws Exception {
        Compra compra = null;
        String sql = "SELECT * FROM compra WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado empleado = new Empleado();
                    Proveedor proveedor = new Proveedor();
                    EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                    empleado.setId(rs.getInt("id_empleado"));
                    proveedor.setId(rs.getInt("id_proveedor"));
                    estadoSolicitud.setId(rs.getInt("id_estadoSolicitud"));
                    compra = new Compra(
                            rs.getInt("id"),
                            empleado,
                            proveedor,
                            estadoSolicitud,
                            rs.getFloat("Total"),
                            rs.getTimestamp("fecha"),
                            rs.getBoolean("estado")
                    );
                }
            }
        }catch(SQLException e){
                System.err.println("Error al Obtener Compra: " + e.getMessage());
        }

        return compra;
    }
}
