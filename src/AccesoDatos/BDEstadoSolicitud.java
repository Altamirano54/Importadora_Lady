package AccesoDatos;

import Entidades.EstadoSolicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase para manejar las operaciones CRUD para la tabla estadosolicitud.
 *
 * @author Yenni (implementado por Asistente de Programación)
 */
public class BDEstadoSolicitud implements ICRUD {

    /**
     * Devuelve una lista con todos los registros de la tabla estadosolicitud.
     *
     * @return ArrayList<EstadoSolicitud> con todos los estados.
     * @throws Exception Si ocurre un error en la consulta.
     */
    @Override
    public ArrayList<EstadoSolicitud> listar() throws Exception {
        ArrayList<EstadoSolicitud> estados = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_creacion FROM estadosolicitud";

        // Usamos try-with-resources para asegurar que la conexión se cierre
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EstadoSolicitud estado = new EstadoSolicitud(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getTimestamp("fecha_creacion")
                );
                estados.add(estado);
            }
        } catch (SQLException e) {
            // Lanzamos una excepción más específica para el error de SQL
            throw new Exception("Error al listar los estados de solicitud: " + e.getMessage());
        }
        return estados;
    }

    /**
     * Inserta un nuevo registro en la tabla estadosolicitud.
     *
     * @param object El objeto EstadoSolicitud a crear.
     * @return El número de filas afectadas (debería ser 1).
     * @throws SQLException Si ocurre un error en la inserción.
     */
    @Override
    public int crear(Object object) throws SQLException {
        int id=-1;
        EstadoSolicitud estado = (EstadoSolicitud) object;
        // La BD asigna el id y la fecha_creacion automáticamente.
        String sql = "INSERT INTO estadosolicitud (nombre) VALUES (?)";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.getNombre());
            id= ps.executeUpdate();
        }catch (SQLException e) {
            // Lanzamos una excepción más específica para el error de SQL
            System.err.println("Error al Actualizar Estado Solicitud: "+ e.getMessage());
            throw e;
        }
        return id;
    }

    /**
     * Actualiza el nombre de un registro existente en la tabla estadosolicitud.
     *
     * @param id El id del estado a actualizar.
     * @param object El objeto EstadoSolicitud con la nueva información.
     * @throws Exception Si ocurre un error en la actualización.
     */
    @Override
    public void actualizar(int id, Object object) throws Exception {
        EstadoSolicitud estado = (EstadoSolicitud) object;
        String sql = "UPDATE estadosolicitud SET nombre = ? WHERE id = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.getNombre());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al actualizar el estado de solicitud: " + e.getMessage());
        }
    }

    /**
     * Elimina un registro de la tabla estadosolicitud de forma permanente.
     *
     * @param id El id del estado a eliminar.
     * @throws Exception Si ocurre un error en la eliminación.
     */
    @Override
    public void eliminar(int id) throws Exception {
        // A diferencia de Compra, la tabla estadosolicitud no tiene un campo 'estado',
        // por lo que realizamos un borrado físico (DELETE).
        String sql = "DELETE FROM estadosolicitud WHERE id = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al eliminar el estado de solicitud: " + e.getMessage());
        }
    }

    /**
     * Obtiene un único estado de solicitud basado en su id.
     *
     * @param id El id del estado a buscar.
     * @return Un objeto EstadoSolicitud si se encuentra, de lo contrario null.
     * @throws Exception Si ocurre un error en la búsqueda.
     */
    @Override
    public Object get(int id) throws Exception {
        EstadoSolicitud estado = null;
        String sql = "SELECT id, nombre, fecha_creacion FROM estadosolicitud WHERE id = ?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estado = new EstadoSolicitud(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getTimestamp("fecha_creacion")
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener el estado de solicitud: " + e.getMessage());
        }
        return estado;
    }
}
