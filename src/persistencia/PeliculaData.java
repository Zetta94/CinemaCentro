/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Zuñiga
 */
public class PeliculaData {

    private Connection connection = null;

    public PeliculaData(Conexion conexion) {
        this.connection = conexion.establishConnection();
    }

    public boolean crearPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas (titulo,director,actores,origen,genero,estreno,enCartelera) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getActores());
            ps.setString(4, pelicula.getOrigen());
            ps.setString(5, pelicula.getGenero());
            ps.setDate(6, java.sql.Date.valueOf(pelicula.getEstreno()));
            ps.setBoolean(7, pelicula.isEnCartelera());

            int created = ps.executeUpdate();
            if (created == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo crear la película",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return false;
        }
    }

    public boolean editarPelicula(Pelicula pelicula, int id) {
        String sql = "UPDATE peliculas SET titulo = ?, director = ?, actores = ?, origen = ?, genero = ?, estreno = ?, enCartelera = ? WHERE idPelicula = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getActores());
            ps.setString(4, pelicula.getOrigen());
            ps.setString(5, pelicula.getGenero());
            ps.setDate(6, java.sql.Date.valueOf(pelicula.getEstreno()));
            ps.setBoolean(7, pelicula.isEnCartelera());
            ps.setInt(8, id);

            int updated = ps.executeUpdate();
            return updated > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo actualizar la película",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return false;
        }
    }

    public List<Pelicula> buscarPeliculas(String titulo, String genero, Integer enCartelera) {
        StringBuilder sql = new StringBuilder(
                "SELECT idPelicula, titulo, director, actores, origen, genero, estreno, enCartelera FROM peliculas WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (titulo != null && !titulo.isEmpty()) {
            sql.append(" AND LOWER(titulo) LIKE ?");
            params.add("%" + titulo.trim().toLowerCase() + "%");
        }

        if (genero != null && !genero.isEmpty()) {
            sql.append(" AND LOWER(genero) LIKE ?");
            params.add("%" + genero.trim().toLowerCase() + "%");
        }

        if (enCartelera != null) {
            sql.append(" AND enCartelera = ?");
            params.add(enCartelera);
        }

        sql.append(" ORDER BY estreno DESC");

        List<Pelicula> peliculas = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                Object p = params.get(i);
                if (p instanceof String) {
                    ps.setString(i + 1, (String) p);
                } else if (p instanceof Integer) {
                    ps.setInt(i + 1, (Integer) p);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula p = new Pelicula();
                    p.setIdPelicula(rs.getInt("idPelicula"));
                    p.setTitulo(rs.getString("titulo"));
                    p.setDirector(rs.getString("director"));
                    p.setActores(rs.getString("actores"));
                    p.setOrigen(rs.getString("origen"));
                    p.setGenero(rs.getString("genero"));
                    p.setEstreno(rs.getDate("estreno").toLocalDate());
                    p.setEnCartelera(rs.getBoolean("enCartelera"));
                    peliculas.add(p);
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
            return peliculas;
        }

        return peliculas;
    }

    public List<Pelicula> obtenerTodas() {
        String sql = "SELECT * FROM peliculas";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setActores(rs.getString("actores"));
                    pelicula.setOrigen(rs.getString("origen"));
                    pelicula.setGenero(rs.getString("genero"));
                    pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                    pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
                    peliculas.add(pelicula);
                }
            }

            return peliculas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo obtener las películas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return null;
        }
    }

    public Pelicula obtenerPorId(int id) {
        String sql = "SELECT * FROM peliculas WHERE idPelicula = ?";
        Pelicula pelicula = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setActores(rs.getString("actores"));
                pelicula.setOrigen(rs.getString("origen"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener la película: " + ex.getMessage());
        }

        return pelicula;
    }

    public List<Pelicula> obtenerPeliculasEnCartelera() {
        String sql = "SELECT * FROM peliculas WHERE enCartelera = 1 AND estreno <= CURDATE();";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setActores(rs.getString("actores"));
                    pelicula.setOrigen(rs.getString("origen"));
                    pelicula.setGenero(rs.getString("genero"));
                    pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                    pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
                    peliculas.add(pelicula);
                }
            }

            return peliculas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo obtener las películas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return null;
        }
    }

    public void eliminarPelicula(int id) {
        String sql = "DELETE FROM peliculas WHERE idPelicula = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar película: " + ex.getMessage());
        }
    }

    public List<Pelicula> obtenerPeliculasMasVistas(int limite) {
        List<Pelicula> lista = new ArrayList<>();

        String sql = """
        SELECT p.*, COUNT(dt.idDetalle) AS vistas
        FROM peliculas p
        JOIN proyeccion pr ON p.idPelicula = pr.idPelicula
        JOIN detalle_tickets dt ON pr.idProyeccion = dt.idProyeccion
        GROUP BY p.idPelicula
        ORDER BY vistas DESC
        LIMIT ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, limite);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("idPelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setOrigen(rs.getString("origen"));
                p.setDirector(rs.getString("director"));
                p.setEstreno(rs.getDate("estreno").toLocalDate());
                p.setEnCartelera(rs.getBoolean("enCartelera"));
                lista.add(p);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al listar películas más vistas:\n" + ex.getMessage());
        }

        return lista;
    }

    public Pelicula buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM peliculas WHERE LOWER(titulo) = LOWER(?) LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, titulo.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("idPelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setActores(rs.getString("actores"));
                    pelicula.setOrigen(rs.getString("origen"));
                    pelicula.setGenero(rs.getString("genero"));
                    pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                    pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
                    return pelicula;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar película por título: " + ex.getMessage());
        }
        return null;
    }

    public LocalDate obtenerProximaFuncion(int idPelicula) {
        String sql = """
        SELECT MIN(fecha) AS proxima
        FROM proyeccion
        WHERE idPelicula = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getDate("proxima") != null) {
                return rs.getDate("proxima").toLocalDate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
