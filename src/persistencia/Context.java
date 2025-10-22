/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author Mauricio
 */

//Esta clase se usa para inicializar las clases datas y que sean accesibles en toda la aplicacion
//Sin tener que pasarlas por parametros varias veces
public class Context {

    private static PeliculaData peliculaData;
    private static ProyeccionData proyeccionData;
    private static SalaData salaData;

    public static void inicializar(Conexion conexion) {
        peliculaData = new PeliculaData(conexion);
        proyeccionData = new ProyeccionData(conexion);
        salaData = new SalaData(conexion);
    }

    public static PeliculaData getPeliculaData() {
        return peliculaData;
    }
    
    public static ProyeccionData getProyeccionData() {
        return proyeccionData;
    }
    
    public static SalaData getSalaData() {
        return salaData;
    }
    
    
}
