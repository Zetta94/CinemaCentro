/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauricio
 */
public class Conexion {

    static Connection getConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String url;
    private String user;
    private String password;

    private Connection connection = null;

    public Conexion(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection establishConnection() {
        while (true) {
            try {
                if (connection != null && !connection.isClosed() && connection.isValid(2)) {
                    return connection;
                }
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conectado a la BD correctamente.");
                return connection;

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "No se pudo conectar a la base de datos.\n\n" + e.getMessage(),
                        "Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new String[]{"Reintentar", "Salir"},
                        "Reintentar"
                );

                if (opcion == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    continue;
                }
            }
        }
    }
}
