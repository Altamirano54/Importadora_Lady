/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Tipo_documento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Amir Altamirano
 */
public class BDTipo_Documento implements ICRUD{

    @Override
    public ArrayList<Tipo_documento> listar() throws Exception {
        ArrayList<Tipo_documento> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_documento";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tipo_documento tipo_d = new Tipo_documento(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
                tipos.add(tipo_d);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar Tipos de documentos: " + e.getMessage());

            throw e;
        }

        return tipos;
    }

    @Override
    public int crear(Object object) throws SQLException {
        int i=0;
        Tipo_documento tipo_d = (Tipo_documento) object;
        String sql = "INSERT INTO tipo_documento (nombre) VALUES (?)";


        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo_d.getNombre());

           i= ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar Tipo de documento: " + e.getMessage());
        }
        return i;
    }

    @Override
    public void actualizar(int id, Object object) throws Exception {
        Tipo_documento tipo_d = (Tipo_documento) object;
        String sql = "UPDATE tipo_documento SET nombre = ? WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo_d.getNombre());
            ps.setInt(2, id); // Aquí estaba faltando el bind del ID

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el Tipo de documento: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM tipo_documento WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                System.out.println("⚠️ No se encontró un Tipo de documento con el ID: " + id);
            } else {
                System.out.println("🗑️ Tipo de documentoeliminado correctamente con ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el Tipo de documento: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Object get(int id) throws Exception {
        String sql = "SELECT * FROM tipo_documento WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Tipo_documento(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            } else {
                System.out.println("⚠️ No se encontró un Tipo de documento con ID: " + id);
                return null;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el Tipo de documento: " + e.getMessage());
            throw e;
        }
    }
    
}
