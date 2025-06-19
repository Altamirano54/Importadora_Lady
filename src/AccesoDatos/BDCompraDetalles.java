package AccesoDatos;

import Entidades.Compra;
import Entidades.CompraDetalles;
import Entidades.Producto;
import java.sql.*;
import java.util.ArrayList;

public class BDCompraDetalles implements ICRUD {

    @Override
    public ArrayList<CompraDetalles> listar() throws Exception {
        ArrayList<CompraDetalles> detalles = new ArrayList<>();
        String sql = "SELECT * FROM compra_detalle";

        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra();
                Producto producto = new Producto();

                compra.setId(rs.getInt("id_compra"));
                producto.setId(rs.getInt("id_producto"));

                CompraDetalles detalle = new CompraDetalles(
                        rs.getInt("id"),
                        compra,
                        producto,
                        rs.getInt("cantidad"),
                        rs.getFloat("precio_total"),
                        rs.getTimestamp("fecha")
                );
                detalles.add(detalle);
            }

        } catch (SQLException e) {
            throw new Exception("Error al listar los detalles de compra: " + e.getMessage(), e);
        }

        return detalles;
    }

    @Override
    public int crear(Object object) throws SQLException {
        CompraDetalles detalle = (CompraDetalles) object;
        String sql = "INSERT INTO compra_detalle (id_compra, id_producto, cantidad, precio_total, fecha) VALUES (?, ?, ?, ?, ?)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        detalle.setFecha(fechaActual);

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, detalle.getCompra().getId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecioTotal());
            ps.setTimestamp(5, detalle.getFecha());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al crear detalle de compra: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        CompraDetalles detalle = (CompraDetalles) object;
        String sql = "UPDATE compra_detalle SET id_compra = ?, id_producto = ?, cantidad = ?, precio_total = ?, fecha = ? WHERE id = ?";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        detalle.setFecha(fechaActual);

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, detalle.getCompra().getId());
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecioTotal());
            ps.setTimestamp(5, detalle.getFecha());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al actualizar detalle de compra: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM compra_detalle WHERE id = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al eliminar detalle de compra: " + e.getMessage(), e);
        }
    }

    @Override
    public Object get(int id) throws Exception {
        CompraDetalles detalle = null;
        String sql = "SELECT * FROM compra_detalle WHERE id = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Compra compra = new Compra();
                    Producto producto = new Producto();

                    compra.setId(rs.getInt("id_compra"));
                    producto.setId(rs.getInt("id_producto"));

                    detalle = new CompraDetalles(
                            rs.getInt("id"),
                            compra,
                            producto,
                            rs.getInt("cantidad"),
                            rs.getFloat("precio_total"),
                            rs.getTimestamp("fecha")
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener detalle de compra con ID " + id + ": " + e.getMessage(), e);
        }

        return detalle;
    }
}
