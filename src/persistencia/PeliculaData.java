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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
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
        String sql = "UPDATE peliculas SET titulo = ?, director = ?, actores = ?, origen = ?, genero = ?, estreno = ?, enCartelera = ? WHERE id = ?";

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
            if (updated > 0) {
                return true;
            } else {
                return false;
            }
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

    public List<Pelicula> buscarPeliculas(String titulo, String genero, Boolean enCartelera) {
        StringBuilder sql = new StringBuilder(
                "SELECT id, titulo, director, actores, origen, genero, estreno, enCartelera FROM peliculas WHERE 1=1"
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
                } else if (p instanceof Boolean) {
                    ps.setBoolean(i + 1, (Boolean) p);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula p = new Pelicula();
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
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            Pelicula pelicula = new Pelicula();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setActores(rs.getString("actores"));
                    pelicula.setOrigen(rs.getString("origen"));
                    pelicula.setGenero(rs.getString("genero"));
                    pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
                    pelicula.setEnCartelera(rs.getBoolean("enCartelera"));
                }
            }

            return pelicula;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo obtener la película",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
            return null;
        }
    }

    public List<Pelicula> obtenerPeliculasEnCartelera() {
        String sql = "SELECT * FROM peliculas WHERE enCartelera = 1";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
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
}
