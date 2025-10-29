/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Comprador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Morbo
 */
public class CompradorData {

    private Connection connection = null;

    public CompradorData(Conexion conexion) {
        this.connection = conexion.establishConnection();
    }

    public boolean crearComprador(Comprador comprador) {
        String sql = "INSERT INTO compradores (dni,nombre,fechaNac,medioPago,password) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, comprador.getDni());
            ps.setString(2, comprador.getNombre());
            ps.setDate(3, java.sql.Date.valueOf(comprador.getFechaNac()));
            ps.setString(4, comprador.getMedioPago());
            ps.setString(5, comprador.getPassword());

            int created = ps.executeUpdate();
            if (created == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo crear el comprador",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return false;
        }
    }

    public Comprador obtenerCompradorPorDni(String dni) {
        String sql = "SELECT * FROM compradores WHERE dni = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dni);
            Comprador comprador = new Comprador();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    comprador.setIdComprador(rs.getInt("idComprador"));
                    comprador.setDni(rs.getString("dni"));
                    comprador.setNombre(rs.getString("nombre"));
                    comprador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    comprador.setPassword(rs.getString("password"));
                    comprador.setMedioPago(rs.getString("medioPago"));
                } else {
                    return null;
                }
            }
            return comprador;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo crear el comprador",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return null;
        }
    }

    public Comprador obtenerCompradorPorId(int id) {

        String sql = "Select * FROM compradores Where idComprador = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Comprador comprador = new Comprador();
                    comprador.setIdComprador(rs.getInt("idComprador"));
                    comprador.setDni(rs.getString("dni"));
                    comprador.setNombre(rs.getString("nombre"));
                    comprador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    comprador.setMedioPago(rs.getString("medioPago"));
                    comprador.setPassword(rs.getString("password"));
                    return comprador;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar comprador por ID", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return null;
    }

    public boolean modificarComprador(Comprador comprador) {
        String sql = "UPDATE compradores SET dni = ?, nombre = ?, fechaNac = ?, medioPago = ?, password = ? Where idComprador = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, comprador.getDni());
            ps.setString(2, comprador.getNombre());
            ps.setDate(3, Date.valueOf(comprador.getFechaNac()));
            ps.setString(4, comprador.getMedioPago());
            ps.setString(5, comprador.getPassword());
            ps.setInt(6, comprador.getIdComprador());

            int updated = ps.executeUpdate();
            return updated == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar el comprador", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean bajaComprador(int idComprador) {
        String sql = "DElete FROm compradores Where idComprador = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idComprador);
            int deleted = ps.executeUpdate();
            return deleted == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el comprador", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }

    public List<Comprador> obtenerTodosLosCompradores() {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "Select * From compradores";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Comprador comprador = new Comprador();
                comprador.setIdComprador(rs.getInt("idComprador"));
                comprador.setDni(rs.getString("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                comprador.setMedioPago(rs.getString("medioPago"));
                comprador.setPassword(rs.getString("password"));
                compradores.add(comprador);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de compradores", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return compradores;
    }

    public List<Comprador> buscarCompradoresPorNombre(String nombreParcial) {
        List<Comprador> compradores = new ArrayList<>();
        String sql = "Select * From compradores Where nombre LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nombreParcial + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comprador comprador = new Comprador();
                    comprador.setIdComprador(rs.getInt("idComprador"));
                    comprador.setDni(rs.getString("dni"));
                    comprador.setNombre(rs.getString("nombre"));
                    comprador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    comprador.setMedioPago(rs.getString("medioPago"));
                    comprador.setPassword(rs.getString("password"));
                    compradores.add(comprador);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar compradores por nombre", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return compradores;
    }

    public int guardarOActualizar(Comprador comprador) throws SQLException {
        String sql = "INSERT INTO compradores (dni, nombre, fechaNac, password, medioPago) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE nombre = VALUES(nombre), fechaNac = VALUES(fechaNac), password = VALUES(password), medioPago = VALUES(medioPago)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, comprador.getDni());
            ps.setString(2, comprador.getNombre());
            ps.setDate(3, Date.valueOf(comprador.getFechaNac()));
            ps.setString(4, comprador.getPassword());
            ps.setString(5, comprador.getMedioPago());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                Comprador compradorExistente = obtenerCompradorPorDni(comprador.getDni());
                return compradorExistente.getIdComprador();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar o actualizar el comprador",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return -1;
        }
    }
}
