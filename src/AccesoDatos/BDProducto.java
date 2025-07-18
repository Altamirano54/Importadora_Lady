package AccesoDatos;

import Entidades.CompraDetalles;
import Entidades.Producto;
import Entidades.Proveedor;
import Entidades.VentaDetalles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BDProducto implements ICRUD {

    @Override
    public ArrayList<Producto> listar() throws Exception {
        ArrayList<Producto> arrProductos = new ArrayList<>();
        String sql = "SELECT * FROM producto AS pr INNER JOIN proveedor AS pv" +
                     " ON pr.id_proveedor = pv.id WHERE pr.estado = 1";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("pr.id"));
                producto.setNombre(rs.getString("pr.nombre"));
                producto.setPrecioVenta(rs.getFloat("pr.precio_venta"));
                producto.setPrecioCompra(rs.getFloat("pr.precio_compra"));
                producto.setStock(rs.getInt("pr.stock"));
                producto.setDescripcion(rs.getString("pr.descripcion"));
                producto.setFechaCreacion(rs.getTimestamp("pr.fecha_creacion"));
                producto.setFechaModificacion(rs.getTimestamp("pr.fecha_modificacion"));
                producto.setUrl(rs.getString("pr.url"));
                producto.setEstado(true);

                Proveedor proveedor = new Proveedor(
                        rs.getInt("pv.id"),
                        rs.getString("pv.ruc"),
                        rs.getString("pv.nombre"),
                        rs.getString("pv.direccion"),
                        rs.getString("pv.correo"),
                        rs.getString("pv.telefono_contacto"),
                        rs.getTimestamp("pv.fecha_modificacion"),
                        rs.getTimestamp("pv.fecha_creacion"),
                        true);
                producto.setProveedor(proveedor);

                arrProductos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al Listar Producto: " + e.getMessage());
            throw new SQLException("Error al Listar Producto: " + e.getMessage());
        }
        return arrProductos;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Producto producto = (Producto) object;
        String sql = "INSERT INTO producto (nombre, precio_venta, precio_compra, stock, id_proveedor, descripcion, fecha_creacion, fecha_modificacion, url, estado)" +
                     " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        producto.setFechaCreacion(fechaActual);
        producto.setFechaModificacion(fechaActual);
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecioVenta());
            ps.setFloat(3, producto.getPrecioCompra());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getProveedor().getId());
            ps.setString(6, producto.getDescripcion());
            ps.setTimestamp(7, producto.getFechaCreacion());
            ps.setTimestamp(8, producto.getFechaModificacion());
            ps.setString(9, producto.getUrl());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al Crear Producto: " + e.getMessage());
            throw new SQLException("Error al Crear Producto: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Producto producto = (Producto) object;
        String sql = "UPDATE producto SET nombre = ?, precio_venta = ?, precio_compra = ?, stock = ?, id_proveedor = ?, descripcion = ?, fecha_modificacion = ?, url = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        producto.setFechaModificacion(fechaActual);
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecioVenta());
            ps.setFloat(3, producto.getPrecioCompra());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getProveedor().getId());
            ps.setString(6, producto.getDescripcion());
            ps.setTimestamp(7, producto.getFechaModificacion());
            ps.setString(8, producto.getUrl());
            ps.setInt(9, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al Actualizar Producto: " + e.getMessage());
            throw new SQLException("Error al Actualizar Producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE producto SET estado = 0, fecha_modificacion = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, fechaActual);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al Eliminar Producto id(" + id + "): " + e.getMessage());
            throw new SQLException("Error al Eliminar Producto id(" + id + "): " + e.getMessage());
        }
    }

    @Override
    public Producto get(int id) throws Exception {
        Producto producto = null;
        String sql = "SELECT * FROM producto AS pr INNER JOIN proveedor AS pv ON pr.id_proveedor = pv.id WHERE pr.id = ? AND pr.estado = 1";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = construirProductoDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al Obtener Producto: " + e.getMessage());
            throw new SQLException("Error al Obtener Producto: " + e.getMessage());
        }
        return producto;
    }

    private Producto construirProductoDesdeResultSet(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("pr.id"));
        producto.setNombre(rs.getString("pr.nombre"));
        producto.setPrecioVenta(rs.getFloat("pr.precio_venta"));
        producto.setPrecioCompra(rs.getFloat("pr.precio_compra"));
        producto.setStock(rs.getInt("pr.stock"));
        producto.setDescripcion(rs.getString("pr.descripcion"));
        producto.setFechaCreacion(rs.getTimestamp("pr.fecha_creacion"));
        producto.setFechaModificacion(rs.getTimestamp("pr.fecha_modificacion"));
        producto.setUrl(rs.getString("pr.url"));
        producto.setEstado(true);

        Proveedor proveedor = new Proveedor(
            rs.getInt("pv.id"),
            rs.getString("pv.ruc"),
            rs.getString("pv.nombre"),
            rs.getString("pv.direccion"),
            rs.getString("pv.correo"),
            rs.getString("pv.telefono_contacto"),
            rs.getTimestamp("pv.fecha_modificacion"),
            rs.getTimestamp("pv.fecha_creacion"),
            true
        );
        producto.setProveedor(proveedor);
        return producto;
    }

    public ArrayList<Producto> buscarPorNombre(String nombre) throws Exception {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto AS pr " +
                     "INNER JOIN proveedor AS pv ON pr.id_proveedor = pv.id " +
                     "WHERE pr.nombre LIKE ? AND pr.estado = 1";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = construirProductoDesdeResultSet(rs);
                    lista.add(producto);
                }
            }
        }
        return lista;
    }

    public ArrayList<Producto> buscarPorProveedor(int idProveedor) throws Exception {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto AS pr " +
                     "INNER JOIN proveedor AS pv ON pr.id_proveedor = pv.id " +
                     "WHERE pr.id_proveedor = ? AND pr.estado = 1";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = construirProductoDesdeResultSet(rs);
                    lista.add(producto);
                }
            }
        }
        return lista;
    }

    public ArrayList<Producto> ordenarPorCampo(String campo, String orden) throws Exception {
        if (!campo.matches("precio_venta|precio_compra|fecha_creacion|fecha_modificacion") ||
            !orden.matches("ASC|DESC")) {
            throw new IllegalArgumentException("Parámetros inválidos para ordenamiento");
        }
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto AS pr " +
                     "INNER JOIN proveedor AS pv ON pr.id_proveedor = pv.id " +
                     "WHERE pr.estado = 1 ORDER BY pr." + campo + " " + orden;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto producto = construirProductoDesdeResultSet(rs);
                lista.add(producto);
            }
        }
        return lista;
    }

    public int contarPorEstado(boolean activos) throws Exception {
        String sql = "SELECT COUNT(*) FROM producto WHERE estado = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, activos ? 1 : 0);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public void actualizarStockPorVenta(ArrayList<VentaDetalles> detalles) throws SQLException {
        String sql = "UPDATE producto SET stock = stock - ?, fecha_modificacion = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (VentaDetalles detalle : detalles) {
                int idProducto = detalle.getProducto().getId();
                int cantidadVendida = detalle.getCantidad();
                ps.setInt(1, cantidadVendida);
                ps.setTimestamp(2, fechaActual);
                ps.setInt(3, idProducto);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar stock por venta: " + e.getMessage());
            throw new SQLException("Error al actualizar stock por venta: " + e.getMessage(), e);
        }
    }

    public void actualizarStockPorCompra(ArrayList<CompraDetalles> detalles) throws SQLException {
        String sql = "UPDATE producto SET stock = stock + ?, fecha_modificacion = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (CompraDetalles detalle : detalles) {
                int idProducto = detalle.getProducto().getId();
                int cantidadComprada = detalle.getCantidad();
                ps.setInt(1, cantidadComprada);
                ps.setTimestamp(2, fechaActual);
                ps.setInt(3, idProducto);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar stock por compra: " + e.getMessage());
            throw new SQLException("Error al actualizar stock por compra: " + e.getMessage(), e);
        }
    }

}
