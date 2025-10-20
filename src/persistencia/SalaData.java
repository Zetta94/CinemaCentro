
package persistencia;

import entidades.Sala;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class SalaData {
    private Connection con = null;

    public SalaData(Conexion conexion) {
        con = conexion.getConexion();
    }

   

    public void guardarSala(Sala s) {
        String sql = "INSERT INTO sala (nroSala, apta3D, capacidad, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getNroSala());
            ps.setBoolean(2, s.isApta3D());
            ps.setInt(3, s.getCapacidad());
            ps.setBoolean(4, s.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                s.setIdSala(rs.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Sala agregada correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Sala: " + ex.getMessage());
        }
    }

    public void modificarSala(Sala s) {
        String sql = "UPDATE sala SET nroSala=?, apta3D=?, capacidad=?, estado=? WHERE idSala=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getNroSala());
            ps.setBoolean(2, s.isApta3D());
            ps.setInt(3, s.getCapacidad());
            ps.setBoolean(4, s.isEstado());
            ps.setInt(5, s.getIdSala());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sala modificada correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar Sala: " + ex.getMessage());
        }
    }

    public void eliminarSala(int id) {
        String sql = "DELETE FROM sala WHERE idSala=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sala eliminada correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Sala: " + ex.getMessage());
        }
    }

    public Sala buscarSala(int id) {
        Sala s = null;
        String sql = "SELECT * FROM sala WHERE idSala=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Sala(
                    rs.getInt("idSala"),
                    rs.getInt("nroSala"),
                    rs.getBoolean("apta3D"),
                    rs.getInt("capacidad"),
                    rs.getBoolean("estado")
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Sala: " + ex.getMessage());
        }
        return s;
    }

    public List<Sala> listarSalas() {
        List<Sala> lista = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM sala")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sala s = new Sala(
                    rs.getInt("idSala"),
                    rs.getInt("nroSala"),
                    rs.getBoolean("apta3D"),
                    rs.getInt("capacidad"),
                    rs.getBoolean("estado")
                );
                lista.add(s);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar Salas: " + ex.getMessage());
        }
        return lista;
    }
}