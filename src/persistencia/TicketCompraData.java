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
            } else {
                // Intento 2: recuperar ID usando el código único
                String sql2 = "SELECT idTicket FROM tickets WHERE codigoTicket = ?";
                try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                    ps2.setString(1, ticket.getCodigoTicket());
                    ResultSet rs2 = ps2.executeQuery();
                    if (rs2.next()) {
                        ticket.setIdTicket(rs2.getInt("idTicket"));
                    } else {
                        return -1;
                    }
                }
            }

            return ticket.getIdTicket();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private String generarCodigoTicket() {
        String chars = "ABCDEF0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            codigo.append(chars.charAt(random.nextInt(chars.length())));
        }
        return codigo.toString();
    }

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

    public TicketCompra buscarTicketPorId(int idTicket) {
        TicketCompra t = null;

        String sql
                = "SELECT t.*, c.nombre AS nombreComprador, c.dni AS dniComprador\n"
                + "FROM tickets t\n"
                + "JOIN compradores c ON t.idComprador = c.idComprador\n"
                + "WHERE t.idTicket = ?";

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

                t.setNombreComprador(rs.getString("nombreComprador"));
                t.setDniComprador(rs.getString("dniComprador"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al buscar el ticket:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return t;
    }

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

    public List<TicketCompra> listarTicketsPorFecha(LocalDate fecha) {
        List<TicketCompra> lista = new ArrayList<>();

        String sql
                = "SELECT t.idTicket, t.fechaCompra, t.fechaFuncion, t.monto, t.codigoTicket, "
                + "       c.idComprador, c.nombre AS nombreComprador, c.dni, c.medioPago, "
                + "       p.idPelicula, p.titulo, p.genero, p.origen, "
                + "       pr.idProyeccion, pr.fecha AS fechaProyeccion, pr.horaInicio, pr.horaFin, pr.idioma, "
                + "       s.nroSala, s.apta3D "
                + "FROM tickets t "
                + "JOIN compradores c ON t.idComprador = c.idComprador "
                + "JOIN detalle_tickets dt ON t.idTicket = dt.idTicket "
                + "JOIN proyeccion pr ON dt.idProyeccion = pr.idProyeccion "
                + "JOIN peliculas p ON pr.idPelicula = p.idPelicula "
                + "JOIN salas s ON pr.idSala = s.idSala "
                + "WHERE t.fechaCompra = ? "
                + "ORDER BY p.titulo";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TicketCompra t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));

                Date dCompra = rs.getDate("fechaCompra");
                if (dCompra != null) {
                    t.setFechaCompra(dCompra.toLocalDate());
                }

                t.setMonto(rs.getDouble("monto"));
                t.setCodigoTicket(rs.getString("codigoTicket"));

                entidades.Comprador c = new entidades.Comprador();
                c.setIdComprador(rs.getInt("idComprador"));
                c.setNombre(rs.getString("nombreComprador"));
                c.setDni(rs.getString("dni"));
                c.setMedioPago(rs.getString("medioPago"));
                t.setComprador(c);

                entidades.Pelicula p = new entidades.Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setOrigen(rs.getString("origen"));
                t.setPelicula(p);

                java.sql.Time horaInicio = rs.getTime("horaInicio");
                if (horaInicio != null) {
                    t.setHora(horaInicio.toLocalTime());
                }

                lista.add(t);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar tickets por fecha:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return lista;
    }

    public List<TicketCompra> listarTicketsPorPelicula(int idPelicula) {
        List<TicketCompra> lista = new ArrayList<>();

        String sql
                = "SELECT t.idTicket, t.fechaCompra, t.fechaFuncion, t.monto, t.codigoTicket, "
                + "       c.idComprador, c.nombre AS nombreComprador, c.dni, c.medioPago, "
                + "       p.idPelicula, p.titulo, p.genero, p.origen, "
                + "       pr.idProyeccion, pr.fecha AS fechaProyeccion, pr.horaInicio, pr.horaFin, "
                + "       pr.idioma, pr.es3D, pr.subtitulada, "
                + "       s.nroSala, s.apta3D "
                + "FROM tickets t "
                + "JOIN compradores c ON t.idComprador = c.idComprador "
                + "JOIN detalle_tickets dt ON t.idTicket = dt.idTicket "
                + "JOIN proyeccion pr ON dt.idProyeccion = pr.idProyeccion "
                + "JOIN peliculas p ON pr.idPelicula = p.idPelicula "
                + "JOIN salas s ON pr.idSala = s.idSala "
                + "WHERE p.idPelicula = ? "
                + "ORDER BY t.fechaCompra";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TicketCompra t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));

                Date dCompra = rs.getDate("fechaCompra");
                if (dCompra != null) {
                    t.setFechaCompra(dCompra.toLocalDate());
                }

                Date dFuncion = rs.getDate("fechaFuncion");
                if (dFuncion != null) {
                    t.setFechaFuncion(dFuncion.toLocalDate());
                }

                t.setMonto(rs.getDouble("monto"));
                t.setCodigoTicket(rs.getString("codigoTicket"));

                entidades.Comprador c = new entidades.Comprador();
                c.setIdComprador(rs.getInt("idComprador"));
                c.setNombre(rs.getString("nombreComprador"));
                c.setDni(rs.getString("dni"));
                c.setMedioPago(rs.getString("medioPago"));
                t.setComprador(c);

                entidades.Pelicula p = new entidades.Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setOrigen(rs.getString("origen"));
                t.setPelicula(p);

                java.sql.Time horaInicio = rs.getTime("horaInicio");
                if (horaInicio != null) {
                    t.setHora(horaInicio.toLocalTime());
                }

                lista.add(t);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar tickets por película:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return lista;
    }

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

    public int contarTicketsPorPelicula(int idPelicula) {
        String sql
                = "SELECT COUNT(t.idTicket) AS cantidad "
                + "FROM tickets t "
                + "JOIN detalle_tickets dt ON t.idTicket = dt.idTicket "
                + "JOIN proyeccion pr ON dt.idProyeccion = pr.idProyeccion "
                + "WHERE pr.idPelicula = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al contar tickets por película:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return 0;
    }

    public TicketCompra obtenerTicketPorDniYCodigo(String dni, String codigo) {
        TicketCompra t = null;

        String sql
                = "SELECT t.idTicket, t.fechaCompra, t.fechaFuncion, t.monto, t.codigoTicket, "
                + "       c.dni, c.nombre, c.medioPago, p.titulo "
                + "FROM tickets t "
                + "JOIN compradores c ON t.idComprador = c.idComprador "
                + "JOIN detalle_tickets dt ON t.idTicket = dt.idTicket "
                + "JOIN proyeccion pr ON dt.idProyeccion = pr.idProyeccion "
                + "JOIN peliculas p ON pr.idPelicula = p.idPelicula "
                + "WHERE c.dni = ? AND t.codigoTicket = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            ps.setString(2, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new TicketCompra();
                t.setIdTicket(rs.getInt("idTicket"));
                t.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                t.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                t.setMonto(rs.getDouble("monto"));
                t.setCodigoTicket(rs.getString("codigoTicket"));
                t.setDniComprador(rs.getString("dni"));
                t.setNombreComprador(rs.getString("nombre"));
                t.setMedioPago(rs.getString("medioPago"));
                t.setTituloPelicula(rs.getString("titulo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar ticket:\n" + ex.getMessage());
        }

        return t;
    }

    public List<entidades.Comprador> listarCompradoresPorFecha(LocalDate fechaFuncion) {
        List<entidades.Comprador> lista = new ArrayList<>();

        String sql
                = "SELECT DISTINCT c.idComprador, c.nombre, c.dni, c.medioPago "
                + "FROM compradores c "
                + "JOIN tickets t ON c.idComprador = t.idComprador "
                + "WHERE t.fechaFuncion = ? "
                + "ORDER BY c.nombre";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fechaFuncion));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                entidades.Comprador c = new entidades.Comprador();
                c.setIdComprador(rs.getInt("idComprador"));
                c.setNombre(rs.getString("nombre"));
                c.setDni(rs.getString("dni"));
                c.setMedioPago(rs.getString("medioPago"));
                lista.add(c);
            }

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "No se registraron compradores en la fecha seleccionada.",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar compradores por fecha:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return lista;
    }

}
