/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauricio
 */
public class LugaresData {

    private Connection connection = null;

    public LugaresData(Conexion conexion) {
        this.connection = conexion.establishConnection();
    }

    public int guardarLugar(Lugar lugar) {
        String sql = "INSERT INTO lugares (fila, numero, ocupado, idProyeccion) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, lugar.getFila());
            ps.setInt(2, lugar.getNumero());
            ps.setBoolean(3, true);
            ps.setInt(4, lugar.getIdProyeccion());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el lugar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return -1;
        }
    }

    public int ObtenerLugaresDisponibles(int idProyeccion) {
        String sql
                = "SELECT s.capacidad - ("
                + "   SELECT COUNT(*) FROM lugares WHERE idProyeccion = ?"
                + ") AS disponibles "
                + "FROM proyeccion p "
                + "JOIN salas s ON s.idSala = p.idSala "
                + "WHERE p.idProyeccion = ?";
        int disponibles = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProyeccion);
            ps.setInt(2, idProyeccion);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    disponibles = rs.getInt("disponibles");
                }
            }
            return disponibles;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al obtener lugares disponibles",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return 0;
        }
    }

    public List<Lugar> obtenerLugaresOcupados(int idProyeccion) {
        List<Lugar> ocupados = new ArrayList<>();

        String sql = "SELECT fila, numero FROM lugares WHERE idProyeccion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProyeccion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Lugar l = new Lugar();
                    l.setFila(rs.getString("fila"));
                    l.setNumero(rs.getInt("numero"));
                    l.setOcupado(true);
                    ocupados.add(l);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al obtener lugares disponibles",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return ocupados;
        }
        return ocupados;
    }

    public void vincularLugarADetalle(int idLugar, int idDetalle) {
        String sql = "INSERT INTO detalle_lugares (idDetalle, idLugar) VALUES (?,?)";
        System.out.println("id lugar " + idLugar);
        System.out.println("id detalle " + idDetalle);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idDetalle);
            ps.setInt(2, idLugar);
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al vincular lugares",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return;
        }
    }
     public List<Lugar> listarLugares() {
        List<Lugar> lista = new ArrayList<>();
        String sql = "SELECT * FROM lugares";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lugar l = new Lugar();
                l.setIdLugar(rs.getInt("idLugar"));
                l.setIdProyeccion(rs.getInt("idProyeccion"));
                l.setFila(rs.getString("fila"));
                l.setNumero(rs.getInt("numero"));
                l.setOcupado(rs.getBoolean("ocupado"));
                lista.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar lugares: " + ex.getMessage());
        }
        return lista;
    }
     public Lugar buscarLugar(int idLugar) {
        Lugar l = null;
        String sql = "SELECT * FROM lugares WHERE idLugar = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLugar);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                l = new Lugar();
                l.setIdLugar(rs.getInt("idLugar"));
                l.setIdProyeccion(rs.getInt("idProyeccion"));
                l.setFila(rs.getString("fila"));
                l.setNumero(rs.getInt("numero"));
                l.setOcupado(rs.getBoolean("ocupado"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el lugar: " + ex.getMessage());
        }
        return l;
    }
     public void modificarLugar(Lugar l) {
        String sql = "UPDATE lugares SET idProyeccion=?, fila=?, numero=?, ocupado=? WHERE idLugar=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, l.getIdProyeccion());
            ps.setString(2, l.getFila());
            ps.setInt(3, l.getNumero());
            ps.setBoolean(4, l.isOcupado());
            ps.setInt(5, l.getIdLugar());
            int exito = ps.executeUpdate();

            if (exito > 0) {
                JOptionPane.showMessageDialog(null, "Lugar actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el lugar a modificar");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el lugar: " + ex.getMessage());
        }
    }
      public void bajaLugar(int idLugar) {
        String sql = "DELETE FROM lugares WHERE idLugar = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLugar);
            int exito = ps.executeUpdate();
            if (exito > 0) {
                JOptionPane.showMessageDialog(null, "Lugar eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el lugar");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el lugar: " + ex.getMessage());
        }
    }

}
