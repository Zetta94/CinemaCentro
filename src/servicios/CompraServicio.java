/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import entidades.Comprador;
import entidades.Lugar;
import java.util.List;
import persistencia.CompradorData;
import persistencia.Context;
import persistencia.LugaresData;
import persistencia.ProyeccionData;
import persistencia.TicketCompraData;

/**
 *
 * @author Mauricio
 */
public class CompraServicio {

    private CompradorData compradorData = Context.getCompradorData();
    private LugaresData lugaresData = Context.getLugaresData();
    private TicketCompraData ticketCompraData = Context.getTicketCompraData();
    
    public boolean guardarCompra(Comprador comprador, List<Lugar> lugaresSeleccionados, Proyeccion proyeccion) {
        String sqlComprador = "INSERT INTO compradores (dni, nombre, fechaNac, password, medioPago) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE VALUES(nombre), fechaNac = VALUES(fechaNac), password = VALUES(password), medioPago = VALUES(medioPago)";
        String sqlTicket = "INSERT INTO tickets (fechaCompra, fechaFuncion, monto, idComprador) VALUES (?,?,?,?)";
        String sqlDetaleTicket = "INSERT INTO detalle_tickets (idTicket, idProyeccion, cantidad, subtotal) VALUES (?,?,?,?)";
        String sqlLugares = "INSERT INTO lugares (fila, numero, ocupado, idProyeccion) VALUES (?,?,?,?)";
        String sqlDetalleLugar = "INSERT INTO detalle_lugares (idDetalle, idLugar) VALUES (?,?)";
    }

}
