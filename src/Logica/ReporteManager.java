package Logica;

import Entidades.ReporteProductoMasVendido;
import java.util.ArrayList;
import AccesoDatos.Conexion;
import Entidades.ReporteClientesPorMes;
import Entidades.ReporteVentaPorCliente;
import Entidades.ReporteVentaPorDia;
import Entidades.ReporteVentaPorMes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteManager {

    public static ArrayList<ReporteProductoMasVendido> obtenerTopProductos() {
        ArrayList<ReporteProductoMasVendido> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement("""
                SELECT 
                    p.id AS id_producto,
                    p.nombre AS nombre_producto,
                    SUM(vd.cantidad) AS cantidad_vendida,
                    p.precio_venta AS precio_unitario,
                    SUM(vd.precioTotal) AS ingresos_generados
                FROM ventadetalles vd 
                JOIN producto p ON vd.id_producto = p.id
                GROUP BY p.id, p.nombre, p.precio_venta
                ORDER BY cantidad_vendida DESC
                LIMIT 10
            """);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ReporteProductoMasVendido(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getInt("cantidad_vendida"),
                    rs.getFloat("precio_unitario"),
                    rs.getFloat("ingresos_generados")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener reporte de productos: " + e.getMessage());
        }

        return lista;
    }
    
    public static ArrayList<ReporteVentaPorDia> obtenerVentasPorDia() {
    ArrayList<ReporteVentaPorDia> lista = new ArrayList<>();
    String sql = """
        SELECT 
            DATE(fech) AS fecha_venta,
            COUNT(*) AS cantidad,
            SUM(total) AS ingresos
        FROM venta
        GROUP BY DATE(fech)
        ORDER BY fecha_venta ASC
        LIMIT 30
    """;
    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            ReporteVentaPorDia r = new ReporteVentaPorDia();
            r.setFecha(rs.getString("fecha_venta")); // ← corregido
            r.setCantidadVentas(rs.getInt("cantidad"));
            r.setIngresosTotales(rs.getFloat("ingresos"));
            lista.add(r);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ventas por día: " + e.getMessage());
    }
    return lista;
}
    

    public static ArrayList<ReporteVentaPorMes> obtenerVentasPorMes() {
    ArrayList<ReporteVentaPorMes> lista = new ArrayList<>();
    String sql = """
        SELECT 
            DATE_FORMAT(fech, '%Y-%m') AS mes_venta,
            COUNT(*) AS cantidad,
            SUM(total) AS ingresos
        FROM venta
        GROUP BY mes_venta
        ORDER BY mes_venta ASC
        LIMIT 12
    """;
    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            ReporteVentaPorMes r = new ReporteVentaPorMes();
            r.setFecha(rs.getString("mes_venta")); // Ej: "2025-07"
            r.setCantidadVentas(rs.getInt("cantidad"));
            r.setIngresosTotales(rs.getFloat("ingresos"));
            lista.add(r);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ventas por mes: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}

    

public static ArrayList<ReporteVentaPorCliente> obtenerVentasPorCliente() {
    ArrayList<ReporteVentaPorCliente> lista = new ArrayList<>();

    String sql = """
        SELECT 
            c.nombre AS cliente,
            COUNT(v.id) AS cantidad,
            SUM(v.total) AS ingresos
        FROM venta v
        INNER JOIN cliente c ON v.id_cliente = c.id
        GROUP BY c.id
        ORDER BY ingresos DESC
        LIMIT 20
    """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            ReporteVentaPorCliente r = new ReporteVentaPorCliente();
            r.setCliente(rs.getString("cliente"));
            r.setCantidadVentas(rs.getInt("cantidad"));
            r.setIngresosTotales(rs.getFloat("ingresos"));
            lista.add(r);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener ventas por cliente: " + e.getMessage());
        e.printStackTrace();
    }

    return lista;
}

    
    public static ArrayList<ReporteClientesPorMes> obtenerClientesPorMes() {
    ArrayList<ReporteClientesPorMes> lista = new ArrayList<>();

    String sql = """
        SELECT 
            DATE_FORMAT(fecha_creacion, '%Y-%m') AS mes_registro,
            COUNT(*) AS cantidad
        FROM cliente
        GROUP BY mes_registro
        ORDER BY mes_registro ASC
        LIMIT 12
    """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            ReporteClientesPorMes r = new ReporteClientesPorMes();
            r.setMes(rs.getString("mes_registro"));
            r.setCantidad(rs.getInt("cantidad"));
            lista.add(r);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener clientes por mes: " + e.getMessage());
        e.printStackTrace();
    }

    return lista;
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
