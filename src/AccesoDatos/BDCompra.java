/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Compra;
import Entidades.Empleado;
import Entidades.EstadoSolicitud;
import Entidades.Proveedor;
import Entidades.SeguimientoEstado;
import java.beans.Statement;
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
        String sql = "SELECT * FROM compra AS c INNER JOIN proveedor AS p ON c.id_proveedor= p.id JOIN empleado AS e ON c.id_empleado = e.id "+
                    "JOIN estadosolicitud AS es ON c.id_estadoSolicitud= es.id WHERE c.id_estadoSolicitud = 2 OR c.id_estadoSolicitud = 3";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("c.id_empleado"));
                empleado.setNombre(rs.getString("e.nombre"));
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("c.id_proveedor"));
                proveedor.setNombre(rs.getString("p.nombre"));
                EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                estadoSolicitud.setId(rs.getInt("c.id_estadoSolicitud"));
                estadoSolicitud.setNombre(rs.getString("es.nombre"));
                Compra compra = new Compra(
                        rs.getInt("c.id"),
                        empleado,
                        proveedor,
                        estadoSolicitud,
                        rs.getFloat("c.Total"),
                        rs.getTimestamp("c.fecha")
                );
                compra.setFecha_Actualizacion(rs.getTimestamp("c.fecha_Actualizacion"));
                    compra.setFecha_cancelacion(rs.getTimestamp("c.fecha_cancelacion"));
                    compra.setFecha_estadoCompletado(rs.getTimestamp("c.fecha_estadoCompletado"));
                    compra.setFecha_estadoEnPoceso(rs.getTimestamp("c.fecha_estadoEnProseso"));
                compras.add(compra);
            }
        }catch(SQLException e){
            System.err.println("Error al Listar Compra: " + e.getMessage());
        }
        return compras;
    }
    
    public ArrayList<Compra> listarCompletado() throws Exception {
        ArrayList<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra AS c INNER JOIN proveedor AS p ON c.id_proveedor= p.id JOIN empleado AS e ON c.id_empleado = e.id "+
                    "JOIN estadosolicitud AS es ON c.id_estadoSolicitud= es.id WHERE c.id_estadoSolicitud = 4";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("c.id_empleado"));
                empleado.setNombre(rs.getString("e.nombre"));
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("c.id_proveedor"));
                proveedor.setNombre(rs.getString("p.nombre"));
                EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                estadoSolicitud.setId(rs.getInt("c.id_estadoSolicitud"));
                estadoSolicitud.setNombre(rs.getString("es.nombre"));
                Compra compra = new Compra(
                        rs.getInt("c.id"),
                        empleado,
                        proveedor,
                        estadoSolicitud,
                        rs.getFloat("c.Total"),
                        rs.getTimestamp("c.fecha")
                );
                compra.setFecha_Actualizacion(rs.getTimestamp("c.fecha_Actualizacion"));
                    compra.setFecha_cancelacion(rs.getTimestamp("c.fecha_cancelacion"));
                    compra.setFecha_estadoCompletado(rs.getTimestamp("c.fecha_estadoCompletado"));
                    compra.setFecha_estadoEnPoceso(rs.getTimestamp("c.fecha_estadoEnProseso"));
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
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);) {
            System.out.println("idEmpleado c: "+ compra.getEmpleado().getId());
            ps.setInt(1, compra.getEmpleado().getId());
            ps.setInt(2, compra.getProveedor().getId());
            ps.setFloat(3, 2);
            ps.setFloat(4, compra.getTotal());
            ps.setTimestamp(5, compra.getFecha());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        id= rs.getInt(1); // Devuelve el ID generado
                        registrarFechaEstado(id, "en proceso");
                        return id;
                    }
                }
            }
            
            
        
        }catch(SQLException e){
            System.err.println("Error al Crear una Compra: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Compra compra = (Compra) object;
        String sql = "UPDATE compra SET id_empleado = ?, id_proveedor = ?, id_estadoSolicitud = ?, Total = ?, fecha_Actualizacion = ? "
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
            
            registrarFechaEstado(id, compra.getEstadoSolicitud().toString());

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
            registrarFechaEstado(id, "cancelado");
        } catch (Exception e) {
            throw new Exception("Error al eliminar compra: " + e.getMessage(), e);
        }
    }

    @Override
    public Object get(int id) throws Exception {
        Compra compra = null;
        String sql = "SELECT * FROM compra INNER JOIN proveedor AS p JOIN empleado AS e ON c.id_empleado = e.id \n" +
"                    \"JOIN estadosolicitud AS es ON c.id_estadoSolicitud= e.id WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado empleado = new Empleado();
                    Proveedor proveedor = new Proveedor();
                    EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                    empleado.setId(rs.getInt("c.id_empleado"));
                    empleado.setNombre(rs.getString("e.nombre"));
                    proveedor.setNombre(rs.getString("p.nombre"));
                    proveedor.setId(rs.getInt("c.id_proveedor"));
                    estadoSolicitud.setId(rs.getInt("c.id_estadoSolicitud"));
                    estadoSolicitud.setNombre(rs.getString("es.nombre"));
                    compra = new Compra(
                            rs.getInt("c.id"),
                            empleado,
                            proveedor,
                            estadoSolicitud,
                            rs.getFloat("c.Total"),
                            rs.getTimestamp("c.fecha")
                    );
                    compra.setFecha_Actualizacion(rs.getTimestamp("c.fecha_Actualizacion"));
                    compra.setFecha_cancelacion(rs.getTimestamp("c.fecha_cancelacion"));
                    compra.setFecha_estadoCompletado(rs.getTimestamp("c.fecha_estadoCompletado"));
                    compra.setFecha_estadoEnPoceso(rs.getTimestamp("c.fecha_estadoEnProseso"));
                }
            }
        }catch(SQLException e){
                System.err.println("Error al Obtener Compra: " + e.getMessage());
        }

        return compra;
    }
    
    public void registrarFechaEstado(int idCompra, String estado) throws SQLException {
        String columnaFecha = switch (estado.toLowerCase()) {
            case "completado" -> "fecha_estadoCompletado";
            case "en proceso" -> "fecha_estadoEnProseso";
            case "cancelado" -> "fecha_cancelacion";
            // Agrega más estados según tu diseño
            default -> throw new IllegalArgumentException("Estado desconocido: " + estado);
        };

        String sql = "UPDATE compra SET " + columnaFecha + " = ? WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, idCompra);
            ps.executeUpdate();
            System.out.println("Fecha registrada para estado: " + estado);
        } catch (SQLException e) {
            System.err.println("Error al registrar fecha de estado: " + e.getMessage());
            throw e;
        }
    }
    
    public  ArrayList<SeguimientoEstado> obtenerSeguimientoEstados(int idCompra) {
        ArrayList<SeguimientoEstado> lista = new ArrayList<>();
        String sql = "SELECT fecha, fecha_estadoEnProseso, fecha_estadoCompletado, fecha_cancelacion FROM compra WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCompra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp fRegistrado = rs.getTimestamp("fecha");
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
