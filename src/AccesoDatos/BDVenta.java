/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;
import Entidades.Venta;
import Entidades.Cliente;
import Entidades.Empleado;
import Entidades.EstadoSolicitud;
import Entidades.SeguimientoEstado;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Redstone
 */
public class BDVenta implements ICRUD {

    @Override
    public ArrayList<Venta> listar() throws Exception {
        ArrayList<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM venta AS v " +
                     "INNER JOIN empleado AS e ON v.id_empleado = e.id " +
                     "INNER JOIN cliente AS c ON v.id_cliente = c.id " +
                     "INNER JOIN estadosolicitud AS es ON v.id_estadosolicitud = es.id "+
                     "WHERE v.id_estadosolicitud = 2 OR v.id_estadosolicitud = 3";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("v.id"));
                venta.setTotal(rs.getFloat("v.Total"));
                venta.setFech(rs.getTimestamp("v.fech"));

                // Relacionado: empleado
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("e.id"));
                empleado.setNombre(rs.getString("e.nombre"));
                venta.setEmpleado(empleado);

                // Relacionado: cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("c.id"));
                cliente.setNombre(rs.getString("c.nombre"));
                cliente.setDireccion(rs.getString("c.direccion"));
                venta.setCliente(cliente);

                // Relacionado: estado
                EstadoSolicitud estado = new EstadoSolicitud();
                estado.setId(rs.getInt("es.id"));
                estado.setNombre(rs.getString("es.nombre"));
                venta.setEstadoSolicitud(estado);
                
                venta.setFecha_Actualizacion(rs.getTimestamp("v.fecha_Actualizacion"));
                    venta.setFecha_cancelacion(rs.getTimestamp("v.fecha_cancelacion"));
                    venta.setFecha_estadoCompletado(rs.getTimestamp("v.fecha_estadoCompletado"));
                    venta.setFecha_estadoEnPoceso(rs.getTimestamp("v.fecha_estadoEnProseso"));

                ventas.add(venta);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Venta: " + e.getMessage());
            throw new SQLException("Error al Listar Venta: " + e.getMessage());
        }

        return ventas;
    }
    
    public ArrayList<Venta> listarCompletados() throws Exception {
        ArrayList<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM venta AS v " +
                     "INNER JOIN empleado AS e ON v.id_empleado = e.id " +
                     "INNER JOIN cliente AS c ON v.id_cliente = c.id " +
                     "INNER JOIN estadosolicitud AS es ON v.id_estadosolicitud = es.id "+
                     "WHERE v.id_estadosolicitud = 4";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("v.id"));
                venta.setTotal(rs.getFloat("v.Total"));
                venta.setFech(rs.getTimestamp("v.fech"));

                // Relacionado: empleado
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("e.id"));
                empleado.setNombre(rs.getString("e.nombre"));
                venta.setEmpleado(empleado);

                // Relacionado: cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("c.id"));
                cliente.setNombre(rs.getString("c.nombre"));
                cliente.setDireccion(rs.getString("c.direccion"));
                venta.setCliente(cliente);

                // Relacionado: estado
                EstadoSolicitud estado = new EstadoSolicitud();
                estado.setId(rs.getInt("es.id"));
                estado.setNombre(rs.getString("es.nombre"));
                venta.setEstadoSolicitud(estado);
                
                venta.setFecha_Actualizacion(rs.getTimestamp("v.fecha_Actualizacion"));
                    venta.setFecha_cancelacion(rs.getTimestamp("v.fecha_cancelacion"));
                    venta.setFecha_estadoCompletado(rs.getTimestamp("v.fecha_estadoCompletado"));
                    venta.setFecha_estadoEnPoceso(rs.getTimestamp("v.fecha_estadoEnProseso"));

                ventas.add(venta);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Venta: " + e.getMessage());
            throw new SQLException("Error al Listar Venta: " + e.getMessage());
        }

        return ventas;
    }
    
    

    @Override
    public int crear(Object object) throws SQLException {
        Venta venta = (Venta) object;
        String sql = "INSERT INTO venta (id_empleado, id_cliente, id_estadosolicitud, Total, fech) " +
                     "VALUES (?, ?, ?, ?, ?)";

        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        venta.setFech(fechaActual);

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, venta.getEmpleado().getId());
            ps.setInt(2, venta.getCliente().getId());
            ps.setInt(3, 3/*venta.getEstadoSolicitud().getId()*/);
            ps.setFloat(4, venta.getTotal());
            ps.setTimestamp(5, venta.getFech());
            

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id= rs.getInt(1); // Devuelve el ID generado
                        return id;
                    }
                }
            }
        }catch(SQLException e){
            System.err.println("Error al Crear Venta: " + e.getMessage());
            throw new SQLException("Error al Crear Venta: " + e.getMessage());
        }

        return -1;
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Venta venta = (Venta) object;
        String sql = "UPDATE venta SET id_empleado = ?, id_cliente = ?, id_estadosolicitud = ?, Total = ?, fecha_Actualizacion = ? " +
                     "WHERE id = ?";

        venta.setFech(new Timestamp(System.currentTimeMillis()));

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getEmpleado().getId());
            ps.setInt(2, venta.getCliente().getId());
            ps.setInt(3, venta.getEstadoSolicitud().getId());
            ps.setFloat(4, venta.getTotal());
            ps.setTimestamp(5, venta.getFech());
            ps.setInt(6, id);
            
            registrarFechaEstado(id, venta.getEstadoSolicitud().toString());

            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al Actualizar Venta: " + e.getMessage());
            throw new SQLException("Error al Actualizar Venta: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        // Eliminación lógica, si tienes campo "estado" deberías hacer:
        // String sql = "UPDATE venta SET estado = 0 WHERE id = ?";
        String sql = "UPDATE venta SET id_estadosolicitud = 1 WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            registrarFechaEstado(id, "cancelado");
        }catch(SQLException e){
            System.err.println("Error al Eliminar Venta: " + e.getMessage());
            throw new SQLException("Error al Eliminar Venta: " + e.getMessage());
        }
    }

    @Override
    public Venta get(int id) throws Exception {
        Venta venta = null;
        String sql = "SELECT * FROM venta AS v " +
                     "INNER JOIN empleado AS e ON v.id_empleado = e.id " +
                     "INNER JOIN cliente AS c ON v.id_cliente = c.id " +
                     "INNER JOIN estadosolicitud AS es ON v.id_estadosolicitud = es.id " +
                     "WHERE v.id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    venta = new Venta();
                    venta.setId(rs.getInt("v.id"));
                    venta.setTotal(rs.getFloat("v.Total"));
                    venta.setFech(rs.getTimestamp("v.fech"));

                    Empleado empleado = new Empleado();
                    empleado.setId(rs.getInt("e.id"));
                    empleado.setNombre(rs.getString("e.nombre"));
                    venta.setEmpleado(empleado);

                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("c.id"));
                    cliente.setNombre(rs.getString("c.nombre"));
                    cliente.setDireccion(rs.getString("c.direccion"));
                    venta.setCliente(cliente);

                    EstadoSolicitud estado = new EstadoSolicitud();
                    estado.setId(rs.getInt("es.id"));
                    estado.setNombre(rs.getString("es.nombre"));
                    venta.setEstadoSolicitud(estado);
                    
                    venta.setFecha_Actualizacion(rs.getTimestamp("v.fecha_Actualizacion"));
                    venta.setFecha_cancelacion(rs.getTimestamp("v.fecha_cancelacion"));
                    venta.setFecha_estadoCompletado(rs.getTimestamp("v.fecha_estadoCompletado"));
                    venta.setFecha_estadoEnPoceso(rs.getTimestamp("v.fecha_estadoEnProseso"));
                }
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Ventaid id("+id+"): " + e.getMessage());
            throw new SQLException("Error al Listar Venta id("+id+"): " + e.getMessage());
        }

        return venta;
    }
    
    public void registrarFechaEstado(int idVenta, String estado) throws SQLException {
        String columnaFecha = switch (estado.toLowerCase()) {
            case "completado" -> "fecha_estadoCompletado";
            case "en proceso" -> "fecha_estadoEnProseso";
            case "cancelado" -> "fecha_cancelacion";
            // Agrega más estados según tu diseño
            default -> throw new IllegalArgumentException("Estado desconocido: " + estado);
        };

        String sql = "UPDATE venta SET " + columnaFecha + " = ? WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, idVenta);
            ps.executeUpdate();
            System.out.println("Fecha registrada para estado: " + estado);
        } catch (SQLException e) {
            System.err.println("Error al registrar fecha de estado: " + e.getMessage());
            throw e;
        }
    }
    
    public ArrayList<SeguimientoEstado> obtenerSeguimientoEstados(int idVenta) {
        ArrayList<SeguimientoEstado> lista = new ArrayList<>();
        String sql = "SELECT fech, fecha_estadoEnProseso, fecha_estadoCompletado, fecha_cancelacion FROM venta WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp fRegistrado = rs.getTimestamp("fech");
                    Timestamp fProceso = rs.getTimestamp("fecha_estadoEnProseso");
                    Timestamp fCompletado = rs.getTimestamp("fecha_estadoCompletado");
                    Timestamp fCancelado = rs.getTimestamp("fecha_cancelacion");

                    if (fRegistrado != null) {
                        lista.add(new SeguimientoEstado("Registrado", fRegistrado));
                    }
                    if (fProceso != null) {
                        lista.add(new SeguimientoEstado("En proceso", fProceso));
                    }
                    if (fCompletado != null) {
                        lista.add(new SeguimientoEstado("Completado", fCompletado));
                    }
                    if (fCancelado != null) {
                        lista.add(new SeguimientoEstado("Cancelado", fCancelado));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener seguimiento de estado: " + e.getMessage());
        }

        return lista;
    }
    
}