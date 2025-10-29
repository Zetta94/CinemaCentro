package persistencia;

import entidades.TicketCompra;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TicketCompraData {

    private Connection con = null;

    public TicketCompraData(Conexion conexion) {
        con = conexion.establishConnection();
    }

    // Alta //
    public int guardarTicket(TicketCompra ticket) {
        String sql = "INSERT INTO tickets (fechaCompra, fechaFuncion, monto, idComprador) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(ticket.getFechaCompra()));
            ps.setDate(2, Date.valueOf(ticket.getFechaFuncion()));
            ps.setDouble(3, ticket.getMonto());
            ps.setInt(4, ticket.getIdComprador());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ticket.setIdTicket(rs.getInt(1));
                JOptionPane.showMessageDialog(
                        null,
                        "Ticket guardado correctamente con ID: " + ticket.getIdTicket(),
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                return ticket.getIdTicket();
            } else return -1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    //  Baja //
    public void eliminarTicket(int idTicket) {
        String sql = "DELETE FROM ticketcompra WHERE idTicket = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTicket);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ticket eliminado correctamente.",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No se encontró el ticket deseado.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al eliminar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //  Modificacion //
    public void modificarTicket(TicketCompra t) {
        String sql = "UPDATE ticketcompra SET fechaCompra=?, fechaFuncion=?, monto=?, idComprador=? WHERE idTicket=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(t.getFechaCompra()));
            ps.setDate(2, Date.valueOf(t.getFechaFuncion()));
            ps.setDouble(3, t.getMonto());
            ps.setInt(4, t.getIdComprador());
            ps.setInt(5, t.getIdTicket());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Ticket actualizado correctamente.",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No se encontró el ticket a actualizar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al actualizar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //  Buscar por ID //
    public TicketCompra buscarTicketPorId(int idTicket) {
        TicketCompra t = null;
        String sql = "SELECT * FROM ticketcompra WHERE idTicket = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTicket);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No se encontró el ticket con ID: " + idTicket,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al buscar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return t;
    }

    //  Listar todos //
    public List<TicketCompra> listarTodos() {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM ticketcompra";

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TicketCompra t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar los tickets:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    //  Tickets emitidos en una fecha //
    public List<TicketCompra> listarTicketsPorFecha(LocalDate fecha) {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM ticketcompra WHERE fechaCompra = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TicketCompra t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar tickets por fecha:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    //  Tickets por pelicula //
    public List<TicketCompra> listarTicketsPorPelicula(int idPelicula) {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT tc.* FROM ticketcompra tc "
                + "JOIN detalleticket dt ON tc.idTicket = dt.idTicket "
                + "JOIN proyeccion pr ON dt.idProyeccion = pr.idProyeccion "
                + "WHERE pr.idPelicula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPelicula);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TicketCompra t = new TicketCompra();
                    t.setIdTicket(rs.getInt("idTicket"));
                    Date dCompra = rs.getDate("fechaCompra");
                    Date dFuncion = rs.getDate("fechaFuncion");
                    if (dCompra != null) {
                        t.setFechaCompra(dCompra.toLocalDate());
                    }
                    if (dFuncion != null) {
                        t.setFechaFuncion(dFuncion.toLocalDate());
                    }
                    t.setMonto(rs.getDouble("monto"));
                    t.setIdComprador(rs.getInt("idComprador"));
                    lista.add(t);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar tickets por pelicula:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    // Peliculas mas vistas //
    public void peliculasMasVistas() {
        String sql = "SELECT p.titulo, COUNT(dt.idDetalle) AS entradasVendidas "
                + "FROM pelicula p "
                + "JOIN proyeccion pr ON p.idPelicula = pr.idPelicula "
                + "JOIN detalleticket dt ON pr.idProyeccion = dt.idProyeccion "
                + "GROUP BY p.titulo "
                + "ORDER BY entradasVendidas DESC "
                + "LIMIT 5";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            StringBuilder sb = new StringBuilder("Peliculas mas vistas:\n");
            while (rs.next()) {
                sb.append(" - ").append(rs.getString("titulo"))
                        .append(": ").append(rs.getInt("entradasVendidas"))
                        .append(" entradas\n");
            }
            JOptionPane.showMessageDialog(
                    null,
                    sb.toString(),
                    "Ranking de peliculas",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al generar ranking:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
