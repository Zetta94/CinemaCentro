package persistencia;

import entidades.TicketCompra;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketCompraData {

    private Connection con = null;

    public TicketCompraData(Conexion conexion) {
        con = conexion.establishConnection();
    }

    // Alta //
    public void guardarTicket(TicketCompra ticket) {
        String sql = "INSERT INTO ticketcompra (fechaCompra, fechaFuncion, monto, idComprador) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(ticket.getFechaCompra()));
            ps.setDate(2, Date.valueOf(ticket.getFechaFuncion()));
            ps.setDouble(3, ticket.getMonto());
            ps.setInt(4, ticket.getIdComprador());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ticket.setIdTicket(rs.getInt(1));
                System.out.println("Ticket guardado con ID: " + ticket.getIdTicket());
            }
        } catch (SQLException ex) {
            System.out.println("Error al guardar ticket: " + ex.getMessage());
        }
    }

    //  Baja //
    public void eliminarTicket(int idTicket) {
        String sql = "DELETE FROM ticketcompra WHERE idTicket = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTicket);
            int exito = ps.executeUpdate();
            if (exito == 1) System.out.println("Ticket eliminado correctamente.");
            else System.out.println("No se encontró el ticket.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar ticket: " + ex.getMessage());
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
            if (exito == 1) System.out.println("Ticket actualizado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar ticket: " + ex.getMessage());
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
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar ticket: " + ex.getMessage());
        }
        return t;
    }

    //  Listar todos //
    public List<TicketCompra> listarTodos() {
        List<TicketCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM ticketcompra";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
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
            System.out.println("Error al listar tickets: " + ex.getMessage());
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
            System.out.println("Error al listar tickets por fecha: " + ex.getMessage());
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
                if (dCompra != null) t.setFechaCompra(dCompra.toLocalDate());
                if (dFuncion != null) t.setFechaFuncion(dFuncion.toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setIdComprador(rs.getInt("idComprador"));
                lista.add(t);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al listar tickets por pelicula: " + ex.getMessage());
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
    try (Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        System.out.println("Peliculas mas vistas:");
        while (rs.next()) {
            System.out.printf(" - %s: %d entradas%n",
                    rs.getString("titulo"),
                    rs.getInt("entradasVendidas"));
        }
    } catch (SQLException ex) {
        System.out.println("Error al generar ranking: " + ex.getMessage());
    }
  }
}    
