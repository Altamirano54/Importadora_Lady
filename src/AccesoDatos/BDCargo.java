/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Timestamp;

/**
 *
 * @author jheff
 */
public class BDCargo implements ICRUD{

    @Override
    public ArrayList<Cargo> listar() throws Exception {
        ArrayList<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargo";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getTimestamp("fecha_creacion"), 
                    rs.getBoolean("estado")
                );
                cargos.add(cargo);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar cargos: " + e.getMessage());

            throw e;
        }

        return cargos;
    }


@Override
public int crear(Object object)throws SQLException {
     int i=0;
    Cargo cargo = (Cargo) object;
    String sql = "INSERT INTO cargo (nombre, fechaCreacion, estado) VALUES (?, ?, ?)";
    Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
    cargo.setFechaCreacion(fechaActual);

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, cargo.getNombre());
        ps.setTimestamp(2, cargo.getFechaCreacion());
        ps.setBoolean(3, cargo.isEstado());

        
       i= ps.executeUpdate();

    } catch (SQLException e) {
        System.err.println("Error al insertar el cargo: " + e.getMessage());
    }
    return i;
}


   @Override
public void actualizar(int id, Object object) throws Exception {
    Cargo cargo = (Cargo) object;
    String sql = "UPDATE cargo SET nombre = ?, fechaCreacion = ?, estado = ? WHERE id = ?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, cargo.getNombre());
        ps.setTimestamp(2, cargo.getFechaCreacion());
        ps.setBoolean(3, cargo.isEstado());
        ps.setInt(4, id); // Aquí estaba faltando el bind del ID

        ps.executeUpdate();

    } catch (SQLException e) {
        System.err.println("❌ Error al actualizar el cargo: " + e.getMessage());
        throw e;
    }
}


   @Override
public void eliminar(int id) throws Exception {
    String sql = "DELETE FROM cargo WHERE id = ?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas == 0) {
            System.out.println("⚠️ No se encontró un cargo con el ID: " + id);
        } else {
            System.out.println("🗑️ Cargo eliminado correctamente con ID: " + id);
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al eliminar cargo: " + e.getMessage());
        throw e;
    }
}

 @Override
public Object get(int id) throws Exception {
    String sql = "SELECT * FROM cargo WHERE id = ?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Cargo(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getTimestamp("fecha_creacion"), // Asegúrate que el nombre coincida con tu DB
                rs.getBoolean("estado")
            );
        } else {
            System.out.println("⚠️ No se encontró un cargo con ID: " + id);
            return null;
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al obtener el cargo: " + e.getMessage());
        throw e;
    }
}
}

    