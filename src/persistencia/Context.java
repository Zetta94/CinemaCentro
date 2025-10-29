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
    private static LugaresData lugaresData;
    private static CompradorData compradorData;
    private static TicketCompraData ticketCompraData;
    private static DetalleTicketData detalleTicketData;
    private static Conexion connection;

    public static void inicializar(Conexion conexion) {
        peliculaData = new PeliculaData(conexion);
        proyeccionData = new ProyeccionData(conexion);
        salaData = new SalaData(conexion);
        lugaresData = new LugaresData(conexion);
        compradorData = new CompradorData(conexion);
        ticketCompraData = new TicketCompraData(conexion);
        detalleTicketData = new DetalleTicketData(conexion);
        connection = conexion;
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
    
    public static LugaresData getLugaresData() {
        return lugaresData;
    }
    
    public static CompradorData getCompradorData() {
        return compradorData;
    }
    
    public static TicketCompraData getTicketCompraData() {
        return ticketCompraData;
    }
    
    public static DetalleTicketData getDetalleTicketData() {
        return detalleTicketData;
    }
    
    public static Conexion getConnetion() {
        return connection;
    }
}
