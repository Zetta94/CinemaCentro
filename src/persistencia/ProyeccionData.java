
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

    public void guardarProyeccion(Proyeccion p) {
        String sql = "INSERT INTO proyeccion (idPelicula, idSala, idioma, es3D, subtitulada, horaInicio, horaFin, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getPelicula().getIdPelicula());
            ps.setInt(2, p.getSala().getIdSala());
            ps.setString(3, p.getIdioma());
            ps.setBoolean(4, p.isEs3D());
            ps.setBoolean(5, p.isSubtitulada());
            ps.setTime(6, Time.valueOf(p.getHoraInicio()));
            ps.setTime(7, Time.valueOf(p.getHoraFin()));
            ps.setDouble(8, p.getPrecio());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setIdProyeccion(rs.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Proyeccion agregada correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Proyección: " + ex.getMessage());
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
                    rs.getDouble("precio")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar Proyecciones: " + ex.getMessage());
        }
        return lista;
    }
}