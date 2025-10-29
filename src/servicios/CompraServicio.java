/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import entidades.Comprador;
import entidades.DetalleTicket;
import entidades.Lugar;
import entidades.Proyeccion;
import entidades.TicketCompra;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import persistencia.CompradorData;
import persistencia.Conexion;
import persistencia.Context;
import persistencia.DetalleTicketData;
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
    private DetalleTicketData detalleTicketData = Context.getDetalleTicketData();
    private Connection connection = Context.getConnetion().establishConnection();

    public boolean guardarCompra(Comprador comprador, List<Lugar> lugaresSeleccionados, Proyeccion proyeccion) {

        try {
            connection.setAutoCommit(false);
            int idComprador = compradorData.guardarOActualizar(comprador);

            double monto = proyeccion.getPrecio() * lugaresSeleccionados.size();
            TicketCompra ticket = new TicketCompra(LocalDate.now(), proyeccion.getFecha(), monto, idComprador);
            int idTicket = ticketCompraData.guardarTicket(ticket);

            DetalleTicket detalleTicket = new DetalleTicket(proyeccion.getIdProyeccion(), lugaresSeleccionados, lugaresSeleccionados.size(), monto, ticket);

            DetalleTicket detalleCreado = detalleTicketData.guardarDetalleTicket(detalleTicket);

            for (Lugar lugar : detalleCreado.getLugares()) {
                int idLugar = lugar.getIdLugar();
                lugaresData.vincularLugarADetalle(idLugar, detalleCreado.getIdDetalle());
            }

            connection.commit();
            return true;

        } catch (SQLException ex) {
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(
                        null,
                        "Hubo un error en el flujo de compra",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
