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

public class BDCompra implements ICRUD {

    @Override
    public ArrayList<Compra> listar() throws Exception {
        ArrayList<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
                        rs.getTimestamp("fecha")
                );
                compras.add(compra);
            }

        } catch (SQLException e) {
            throw new Exception("Error al listar compras: " + e.getMessage(), e);
        }

        return compras;
    }

    @Override
    public int crear(Object object) throws SQLException {
        Compra compra = (Compra) object;
        String sql = "INSERT INTO compra (id_empleado, id_proveedor, id_estadoSolicitud, Total, fecha) VALUES (?, ?, ?, ?, ?)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        compra.setFecha(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, compra.getEmpleado().getId());
            ps.setInt(2, compra.getProveedor().getId());
            ps.setInt(3, compra.getEstadoSolicitud().getId());
            ps.setFloat(4, compra.getTotal());
            ps.setTimestamp(5, compra.getFecha());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Error al crear compra: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Compra compra = (Compra) object;
        String sql = "UPDATE compra SET id_empleado = ?, id_proveedor = ?, id_estadoSolicitud = ?, Total = ?, fecha = ? WHERE id = ?";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        compra.setFecha(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, compra.getEmpleado().getId());
            ps.setInt(2, compra.getProveedor().getId());
            ps.setInt(3, compra.getEstadoSolicitud().getId());
            ps.setFloat(4, compra.getTotal());
            ps.setTimestamp(5, compra.getFecha());
            ps.setInt(6, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error al actualizar compra: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM compra WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
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
                    empleado.setId(rs.getInt("id_empleado"));

                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(rs.getInt("id_proveedor"));

                    EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                    estadoSolicitud.setId(rs.getInt("id_estadoSolicitud"));

                    compra = new Compra(
                            rs.getInt("id"),
                            empleado,
                            proveedor,
                            estadoSolicitud,
                            rs.getFloat("Total"),
                            rs.getTimestamp("fecha")
                    );
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener compra: " + e.getMessage(), e);
        }

        return compra;
    }
}
