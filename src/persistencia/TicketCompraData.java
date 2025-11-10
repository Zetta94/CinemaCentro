package persistencia;

import entidades.TicketCompra;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class TicketCompraData {

    private Connection con = null;

    public TicketCompraData(Conexion conexion) {
        con = conexion.establishConnection();
    }

    // ALTA 
    public int guardarTicket(TicketCompra ticket) {
        String codigo = generarCodigoTicket();
        ticket.setCodigoTicket(codigo);

        String sql = "INSERT INTO tickets (fechaCompra, fechaFuncion, monto, idComprador, codigoTicket) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(ticket.getFechaCompra()));
            ps.setDate(2, Date.valueOf(ticket.getFechaFuncion()));
            ps.setDouble(3, ticket.getMonto());
            ps.setInt(4, ticket.getIdComprador());
            ps.setString(5, ticket.getCodigoTicket());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ticket.setIdTicket(rs.getInt(1));
                JOptionPane.showMessageDialog(
                        null,
                        "🎟 Ticket listo para retirar.\nCódigo de retiro: " + ticket.getCodigoTicket(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                return ticket.getIdTicket();
            } else {
                return -1;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    // GENERAR CÓDIGO 
    private String generarCodigoTicket() {
        String chars = "ABCDEF0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            codigo.append(chars.charAt(random.nextInt(chars.length())));
        }
        return codigo.toString();
    }

    // BAJA 
    public void eliminarTicket(int idTicket) {
        String sql = "DELETE FROM tickets WHERE idTicket = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTicket);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null,
                        "Ticket eliminado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el ticket deseado.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFICACIÓN 
    public void modificarTicket(TicketCompra t) {
        String sql = "UPDATE tickets SET fechaCompra=?, fechaFuncion=?, monto=?, idComprador=?, codigoTicket=? WHERE idTicket=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(t.getFechaCompra()));
            ps.setDate(2, Date.valueOf(t.getFechaFuncion()));
            ps.setDouble(3, t.getMonto());
            ps.setInt(4, t.getIdComprador());
            ps.setString(5, t.getCodigoTicket());
            ps.setInt(6, t.getIdTicket());
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null,
                        "Ticket actualizado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el ticket a actualizar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // BUSCAR POR ID 
    public TicketCompra buscarTicketPorId(int idTicket) {
        TicketCompra t = null;
        String sql = "SELECT * FROM tickets WHERE idTicket = ?";
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
                t.setCodigoTicket(rs.getString("codigoTicket"));
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el ticket con ID: " + idTicket,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al buscar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return t;
    }

    // BUSCAR POR CÓDIGO 
    public TicketCompra obtenerTicketPorCodigo(String codigo) {
        TicketCompra t = null;
        String sql = "SELECT * FROM tickets WHERE codigoTicket = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
                t.setCodigoTicket(rs.getString("codigoTicket"));
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró ningún ticket con el código: " + codigo,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al buscar ticket por código:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return t;
    }

    // LISTAR TODOS
    public List<TicketCompra> listarTodos() {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets";

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TicketCompra t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
                t.setCodigoTicket(rs.getString("codigoTicket"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al listar los tickets:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    // LISTAR POR FECHA
    public List<TicketCompra> listarTicketsPorFecha(LocalDate fecha) {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE fechaCompra = ?";
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
                t.setCodigoTicket(rs.getString("codigoTicket"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al listar tickets por fecha:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    // LISTAR POR PELÍCULA
    public List<TicketCompra> listarTicketsPorPelicula(int idPelicula) {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT t.* FROM tickets t "
                + "JOIN detalle_tickets dt ON t.idTicket = dt.idTicket "
                + "JOIN proyeccion pr ON dt.idProyeccion = pr.idProyeccion "
                + "WHERE pr.idPelicula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TicketCompra t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                Date dCompra = rs.getDate("fechaCompra");
                Date dFuncion = rs.getDate("fechaFuncion");
                if (dCompra != null) t.setFechaCompra(dCompra.toLocalDate());
                if (dFuncion != null) t.setFechaFuncion(dFuncion.toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
                t.setCodigoTicket(rs.getString("codigoTicket"));
                lista.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al listar tickets por película:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    // PELÍCULAS MÁS VISTAS
    public void peliculasMasVistas() {
        String sql = "SELECT p.titulo, COUNT(dt.idDetalle) AS entradasVendidas "
                + "FROM peliculas p "
                + "JOIN proyeccion pr ON p.idPelicula = pr.idPelicula "
                + "JOIN detalle_tickets dt ON pr.idProyeccion = dt.idProyeccion "
                + "GROUP BY p.titulo "
                + "ORDER BY entradasVendidas DESC "
                + "LIMIT 5";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            StringBuilder sb = new StringBuilder("Películas más vistas:\n");
            while (rs.next()) {
                sb.append(" - ").append(rs.getString("titulo"))
                        .append(": ").append(rs.getInt("entradasVendidas"))
                        .append(" entradas\n");
            }
            JOptionPane.showMessageDialog(null,
                    sb.toString(),
                    "Ranking de películas",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar ranking:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
