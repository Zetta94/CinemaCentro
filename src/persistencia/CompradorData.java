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
    
    public Comprador obtenerCompradorPorDni (String dni) {
        String sql = "SELECT * FROM compradores WHERE dni = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dni);
            Comprador comprador = new Comprador();
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
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
        }catch (SQLException ex) {
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
}
