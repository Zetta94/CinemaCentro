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
import java.sql.Date;
import java.sql.Statement;
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

}
