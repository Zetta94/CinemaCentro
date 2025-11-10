/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.DetalleTicket;
import entidades.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Morbo
 */
public class DetalleTicketData {

    private Connection con = null;
    private LugaresData lugaresData = Context.getLugaresData();

    public DetalleTicketData(Conexion conexion) {
        this.con = conexion.establishConnection();
    }

    public DetalleTicket guardarDetalleTicket(DetalleTicket detalleTicket) {
        String sql = "INSERT INTO detalle_tickets (idTicket, idProyeccion, cantidad, subtotal) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            ps.setInt(1, detalleTicket.getTicket().getIdTicket());
            ps.setInt(2, detalleTicket.getIdFuncion());
            ps.setInt(3, detalleTicket.getCantidad());
            ps.setDouble(4, detalleTicket.getSubtotal());

            for (Lugar lugar : detalleTicket.getLugares()) {
                int idLugar = lugaresData.guardarLugar(lugar);
                lugar.setIdLugar(idLugar);
            }

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                detalleTicket.setIdDetalle(rs.getInt(1));
                return detalleTicket;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el detalle de ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<DetalleTicket> obtenerDetallesPorTicket(int idTicket) {
        List<DetalleTicket> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_tickets WHERE idTicket = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTicket);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleTicket d = new DetalleTicket();
                d.setIdDetalle(rs.getInt("idDetalle"));
                d.setIdTicket(rs.getInt("idTicket"));
                d.setIdFuncion(rs.getInt("idProyeccion"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setSubtotal(rs.getDouble("subtotal"));
                lista.add(d);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al obtener detalles del ticket:\n" + ex.getMessage());
        }

        return lista;
    }

}
