
package AccesoDatos;

import Entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BDCliente implements ICRUD{
    
    
    
     @Override
    public ArrayList listar() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getTimestamp("fecha_creacion"),
                            rs.getTimestamp("fecha_modificacion"),
                            rs.getBoolean("estado")
                           
                );
                cliente.add(cliente);
            }
        }

        return clientes;
    }
    
    
    
    
    @Override
    public int crear(Object object) throws SQLException {
        Cliente cliente = (Cliente) object;
        String sql = "INSERT INTO cliente (nombre, telefono, fecha_creacion, fecha_modificacion,estado)"+
                    " VALUES (?, ?, ?, ?, ?,?)";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        cliente.setFechaCreacion(fechaActual);
        cliente.setFechaModificacion(fechaActual);
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setTimestamp(3, cliente.getFechaCreacion());
            ps.setTimestamp(4, cliente.getFechaModificacion());
            ps.setBoolean(5, cliente.getEstado());


            return ps.executeUpdate();
        }
    }
@Override
    public void actualizar(int id, Object object) throws Exception {
        Cliente cliente = (Cliente) object;
        String sql = "UPDATE cliente SET  nombre = ?, telefono = ?, fecha_modificacion = ?,estado = ?"+
                " WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        cliente.setFechaModificacion(fechaActual);
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setTimestamp(3, cliente.getFechaModificacion());
            ps.setBoolean(4, cliente.getEstado());
            ps.setInt(5, cliente.getId());

            ////
            
            
            
            ps.executeUpdate();
        }
    }
@Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE cliente SET estado = 0, fecha_modificacion = ? WHERE id = ?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, fechaActual);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al eliminar cliente: " + e.getMessage(), e);
        }
    }
@Override
    public Cliente get(int id) throws Exception {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE id = ? AND estado = 1";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getTimestamp("fecha_creacion"),
                            rs.getTimestamp("fecha_modificacion"),
                            rs.getBoolean("estado")
                    );
                }
            }
        }

        return cliente;
    }
}
