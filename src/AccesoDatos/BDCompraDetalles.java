package AccesoDatos;

import Entidades.Compra;
import Entidades.CompraDetalles;
import Entidades.Producto;
import Entidades.Proveedor;
import java.sql.*;
import java.util.ArrayList;

public class BDCompraDetalles implements ICRUD {

    @Override
    public ArrayList<CompraDetalles> listar() throws Exception {
        ArrayList<CompraDetalles> detalles = new ArrayList<>();
        String sql = "SELECT * FROM compradetalles AS cd INNER JOIN producto AS p ON cd.id_producto = p.id";

        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra();
                Producto producto = new Producto();

                compra.setId(rs.getInt("cd.id_compra"));
                producto.setId(rs.getInt("cd.id_producto"));
                producto.setNombre(rs.getString("p.nombre"));

                CompraDetalles detalle = new CompraDetalles(
                        rs.getInt("cd.id"),
                        compra,
                        producto,
                        rs.getInt("cd.cantidad"),
                        rs.getFloat("cd.precioTotal"),
                        rs.getTimestamp("cd.fecha")
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
        String sql = "INSERT INTO compradetalles (id_compra, id_producto, cantidad, precioTotal, fecha) VALUES (?, ?, ?, ?, ?)";

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
            System.out.println("error al rear compra detalles+ e.getMessage(): "+ e.getMessage());
            throw new SQLException("Error al crear detalle de compra: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        CompraDetalles detalle = (CompraDetalles) object;
        String sql = "UPDATE compradetalles SET id_compra = ?, id_producto = ?, cantidad = ?, precioTotal = ?, fecha = ? WHERE id = ?";

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
    public ArrayList<CompraDetalles> get(int id) throws Exception {
        ArrayList<CompraDetalles> detalles = new ArrayList<>();
        String sql = "SELECT * FROM compradetalles AS cd INNER JOIN producto AS p ON cd.id_producto = p.id WHERE cd.id = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Compra compra = new Compra();
                    Producto producto = new Producto();

                    compra.setId(rs.getInt("cd.id_compra"));
                    producto.setId(rs.getInt("cd.id_producto"));
                    producto.setNombre(rs.getString("p.nombre"));

                    CompraDetalles detalle = new CompraDetalles(
                            rs.getInt("cd.id"),
                            compra,
                            producto,
                            rs.getInt("cd.cantidad"),
                            rs.getFloat("cd.precioTotal"),
                            rs.getTimestamp("cd.fecha")
                    );
                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener detalle de compra con ID " + id + ": " + e.getMessage(), e);
        }

        return detalles;
    }
    
    public ArrayList<CompraDetalles> obtenerProductosDeVentasPendientes() {
        ArrayList<CompraDetalles> listaDetalles = new ArrayList<>();
        String sql = """
            SELECT vd.id_producto, p.nombre, p.id_proveedor,pv.nombre, SUM(vd.cantidad) AS cantidad_total, p.precio_compra
            FROM ventadetalles vd
            JOIN producto p ON vd.id_producto = p.id
            JOIN venta v ON vd.id_venta = v.id
            JOIN proveedor pv ON p.id_proveedor = pv.id
            WHERE v.id_estadoSolicitud = 3
            GROUP BY vd.id_producto, p.id_proveedor
        """;

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("p.nombre"));
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("p.id_proveedor"));
                proveedor.setNombre(rs.getString("pv.nombre"));
                producto.setProveedor(proveedor);

                Compra compraFicticia = new Compra(); // aún no se guarda
                compraFicticia.setProveedor(proveedor); // útil para agrupar luego si quieres

                CompraDetalles detalle = new CompraDetalles();
                detalle.setCompra(compraFicticia); // se asignará el ID real más adelante
                detalle.setProducto(producto);
                detalle.setCantidad(rs.getInt("cantidad_total"));
                float total = rs.getInt("cantidad_total") * rs.getFloat("precio_compra");
                System.out.println("total calculado: " +total);
                detalle.setPrecioTotal(total);
                System.out.println("precio total bd: "+ detalle.getPrecioTotal());
                detalle.setFecha(new Timestamp(System.currentTimeMillis()));

                listaDetalles.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos de ventas pendientes: " + e.getMessage());
        }

        return listaDetalles;
    }
}
