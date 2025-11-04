package persistencia;

import entidades.Pelicula;
import entidades.Proyeccion;
import entidades.Sala;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ProyeccionData {

    private Connection con = null;
    private PeliculaData peliculaData;
    private SalaData salaData;

    public ProyeccionData(Conexion conexion) {
        this.con = conexion.establishConnection();
        peliculaData = new PeliculaData(conexion);
        salaData = new SalaData(conexion);
    }

    public boolean guardarProyeccion(Proyeccion p) {
        String sql = "INSERT INTO proyeccion (idPelicula, idSala, idioma, es3D, subtitulada, horaInicio, horaFin, fecha, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getPelicula().getIdPelicula());
            ps.setInt(2, p.getSala().getIdSala());
            ps.setString(3, p.getIdioma());
            ps.setBoolean(4, p.isEs3D());
            ps.setBoolean(5, p.isSubtitulada());
            ps.setTime(6, Time.valueOf(p.getHoraInicio()));
            ps.setTime(7, Time.valueOf(p.getHoraFin()));
            ps.setDate(8, java.sql.Date.valueOf(p.getFecha()));
            ps.setDouble(9, p.getPrecio());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setIdProyeccion(rs.getInt(1));
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Proyección: " + ex.getMessage());
            return false;
        }
    }

    public List<Proyeccion> listarProyecciones() {
        List<Proyeccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyeccion";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula peli = peliculaData.obtenerPorId(rs.getInt("idPelicula"));
                Sala sala = salaData.buscarSala(rs.getInt("idSala"));

                Proyeccion p = new Proyeccion(
                        rs.getInt("idProyeccion"),
                        peli,
                        sala,
                        rs.getString("idioma"),
                        rs.getBoolean("es3D"),
                        rs.getBoolean("subtitulada"),
                        rs.getTime("horaInicio").toLocalTime(),
                        rs.getTime("horaFin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("precio")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar Proyecciones: " + ex.getMessage());
        }
        return lista;
    }

    public List<Proyeccion> listarProyeccionesPorId(int peliculaId) {
        List<Proyeccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyeccion WHERE idPelicula = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, peliculaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula peli = peliculaData.obtenerPorId(rs.getInt("idPelicula"));
                Sala sala = salaData.buscarSala(rs.getInt("idSala"));

                Proyeccion p = new Proyeccion(
                        rs.getInt("idProyeccion"),
                        peli,
                        sala,
                        rs.getString("idioma"),
                        rs.getBoolean("es3D"),
                        rs.getBoolean("subtitulada"),
                        rs.getTime("horaInicio").toLocalTime(),
                        rs.getTime("horaFin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("precio")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar Proyecciones: " + ex.getMessage());
        }
        return lista;
    }

    public List<Proyeccion> listarProyeccionPorSala(int idsala) {
        List<Proyeccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyeccion WHERE idSala = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idsala);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula peli = peliculaData.obtenerPorId(rs.getInt("idPelicula"));
                Sala sala = salaData.buscarSala(idsala);

                Proyeccion p = new Proyeccion(
                        rs.getInt("idProyeccion"),
                        peli,
                        sala,
                        rs.getString("idioma"),
                        rs.getBoolean("es3D"),
                        rs.getBoolean("subtitulada"),
                        rs.getTime("horaInicio").toLocalTime(),
                        rs.getTime("horaFin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("precio")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar Proyecciones: " + ex.getMessage());
        }
        return lista;
    }

    public List<Proyeccion> buscarProyecciones(Sala sala, Boolean subtitulada, Boolean es3D) {
        StringBuilder sql = new StringBuilder(
                "SELECT p.idProyeccion, p.idPelicula, p.idSala, p.idioma, p.es3D, p.subtitulada, p.horaInicio, p.horaFin, p.fecha, p.precio FROM proyeccion p WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();
        if (sala != null && sala.getIdSala() > 0) {
            sql.append(" AND p.idSala = ?");
            params.add(sala.getIdSala());
        }
        if (subtitulada != null) {
            sql.append(" AND p.subtitulada = ?");
            params.add(subtitulada);
        }
        if (es3D != null) {
            sql.append(" AND p.es3D = ?");
            params.add(es3D);
        }

        sql.append(" ORDER BY p.fecha DESC, p.horaInicio ASC");

        List<Proyeccion> proyecciones = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object p = params.get(i);

                if (p instanceof Integer) {
                    ps.setInt(i + 1, (Integer) p);
                } else if (p instanceof Boolean) {
                    ps.setBoolean(i + 1, (Boolean) p);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Proyeccion proyeccion = new Proyeccion();
                    proyeccion.setIdProyeccion(rs.getInt("idProyeccion"));
                    Pelicula p = peliculaData.obtenerPorId(rs.getInt("idPelicula"));
                    Sala s = salaData.buscarSala(rs.getInt("idSala"));
                    proyeccion.setPelicula(p);
                    proyeccion.setSala(s);
                    proyeccion.setIdioma(rs.getString("idioma"));
                    proyeccion.setEs3D(rs.getBoolean("es3D"));
                    proyeccion.setSubtitulada(rs.getBoolean("subtitulada"));
                    proyeccion.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                    proyeccion.setHoraFin(rs.getTime("horaFin").toLocalTime());
                    proyeccion.setFecha(rs.getDate("fecha").toLocalDate());
                    proyeccion.setPrecio(rs.getDouble("precio"));

                    proyecciones.add(proyeccion);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo realizar la busqueda",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }

        return proyecciones;
    }

    public boolean eliminarProyeccion(int idProyeccion) {
        String sql = "DELETE FROM proyeccion WHERE idProyeccion=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyeccion);
            int resultado = ps.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Proyeccion eliminada correctamente");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la proyeccion: " + ex.getMessage());
            return false;
        }
    }

    public boolean modificarProyeccion(Proyeccion p) {
        String sql = "UPDATE proyeccion SET idPelicula = ?, idSala = ?, idioma = ?, es3D = ?, subtitulada = ?, horaInicio = ?, horaFin = ?, fecha = ?, precio = ? WHERE idProyeccion = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getPelicula().getIdPelicula());
            ps.setInt(2, p.getSala().getIdSala());
            ps.setString(3, p.getIdioma());
            ps.setBoolean(4, p.isEs3D());
            ps.setBoolean(5, p.isSubtitulada());
            ps.setTime(6, Time.valueOf(p.getHoraInicio()));
            ps.setTime(7, Time.valueOf(p.getHoraFin()));
            ps.setDate(8, java.sql.Date.valueOf(p.getFecha()));
            ps.setDouble(9, p.getPrecio());
            ps.setInt(10, p.getIdProyeccion());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar Proyección: " + ex.getMessage());
            return false;
        }
    }
    
     public Proyeccion obtenerProyeccionPorId(int idProyeccion) {
        String sql = "SELECT * FROM proyeccion WHERE idProyeccion = ?";
        Proyeccion p = null;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProyeccion);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Pelicula peli = peliculaData.obtenerPorId(rs.getInt("idPelicula"));
                Sala sala = salaData.buscarSala(rs.getInt("idSala"));

                p = new Proyeccion(
                        rs.getInt("idProyeccion"),
                        peli,
                        sala,
                        rs.getString("idioma"),
                        rs.getBoolean("es3D"),
                        rs.getBoolean("subtitulada"),
                        rs.getTime("horaInicio").toLocalTime(),
                        rs.getTime("horaFin").toLocalTime(),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar Proyecciones: " + ex.getMessage());
        }
        return p;
    }
}
