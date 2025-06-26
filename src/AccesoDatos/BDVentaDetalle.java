/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;
import Entidades.Producto;
import Entidades.Venta;
import Entidades.VentaDetalles;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Redstone
 */
public class BDVentaDetalle implements ICRUD{
    @Override
    public ArrayList<VentaDetalles> listar() throws Exception {
        ArrayList<VentaDetalles> detalles = new ArrayList<>();
        String sql = "SELECT * FROM ventadetalles AS vd " +
                     "INNER JOIN producto AS p ON vd.id_producto = p.id " +
                     "INNER JOIN venta AS v ON vd.id_venta = v.id";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VentaDetalles detalle = new VentaDetalles();
                detalle.setId(rs.getInt("vd.id"));
                detalle.setCantidad(rs.getInt("vd.Cantidad"));
                detalle.setPrecioTotal(rs.getFloat("vd.precioTotal"));
                detalle.setFechaModificacion(rs.getTimestamp("vd.fecha_modificacion"));

                // Producto asociado
                Producto producto = new Producto();
                producto.setId(rs.getInt("p.id"));
                producto.setNombre(rs.getString("p.nombre"));
                detalle.setProducto(producto);

                // Venta asociada
                Venta venta = new Venta();
                venta.setId(rs.getInt("v.id"));
                detalle.setVenta(venta);

                detalles.add(detalle);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar detalles de Venta: " + e.getMessage());
            throw new SQLException("Error al Listar detalles de Venta: " + e.getMessage());
        }

        return detalles;
    }

    @Override
    public int crear(Object object) throws SQLException {
        VentaDetalles detalle = (VentaDetalles) object;
        String sql = "INSERT INTO ventadetalles (id_venta, id_producto, Cantidad, precioTotal, fecha_modificacion) " +
                     "VALUES (?, ?, ?, ?, ?)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        detalle.setFechaModificacion(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, detalle.getVenta().getId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecioTotal());
            ps.setTimestamp(5, detalle.getFechaModificacion());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }catch(SQLException e){
            System.err.println("Error al Crear detalles de Venta: " + e.getMessage());
            throw new SQLException("Error al Crear detalles de Venta: " + e.getMessage());
        }

        return -1;
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        VentaDetalles detalle = (VentaDetalles) object;
        String sql = "UPDATE ventadetalles SET id_venta = ?, id_producto = ?, Cantidad = ?, precioTotal = ?, fecha_modificacion = ? " +
                     "WHERE id = ?";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        detalle.setFechaModificacion(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getVenta().getId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecioTotal());
            ps.setTimestamp(5, detalle.getFechaModificacion());
            ps.setInt(6, id);

            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Actualizar detalles de Venta: " + e.getMessage());
            throw new SQLException("Error al Actualizar detalles de Venta: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM ventadetalles WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Eliminar detalles de Venta: " + e.getMessage());
            throw new SQLException("Error al Eliminar detalles de Venta: " + e.getMessage());
        }
    }

    @Override
    public VentaDetalles get(int id) throws Exception {
        VentaDetalles detalle = null;
        String sql = "SELECT * FROM ventadetalles AS vd " +
                     "INNER JOIN producto AS p ON vd.id_producto = p.id " +
                     "INNER JOIN venta AS v ON vd.id_venta = v.id " +
                     "WHERE vd.id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detalle = new VentaDetalles();
                    detalle.setId(rs.getInt("vd.id"));
                    detalle.setCantidad(rs.getInt("vd.Cantidad"));
                    detalle.setPrecioTotal(rs.getFloat("vd.precioTotal"));
                    detalle.setFechaModificacion(rs.getTimestamp("vd.fecha_modificacion"));

                    Producto producto = new Producto();
                    producto.setId(rs.getInt("p.id"));
                    producto.setNombre(rs.getString("p.nombre"));
                    detalle.setProducto(producto);

                    Venta venta = new Venta();
                    venta.setId(rs.getInt("v.id"));
                    detalle.setVenta(venta);
                }
            }
        }catch(SQLException e){
            System.err.println("Error al Obtener detalles de Venta id("+id+"): " + e.getMessage());
            throw new SQLException("Error al Obtener detalles de Venta id("+id+"): "+ e.getMessage());
        }

        return detalle;
    }
    
}
