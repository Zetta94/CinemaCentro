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

    public String guardarCompra(Comprador comprador, List<Lugar> lugaresSeleccionados, Proyeccion proyeccion) {

        try {
            connection.setAutoCommit(false);

            int idComprador = compradorData.guardarOActualizar(comprador);

            double monto = proyeccion.getPrecio() * lugaresSeleccionados.size();
            TicketCompra ticket = new TicketCompra(LocalDate.now(), proyeccion.getFecha(), monto, idComprador);

            int idTicket = ticketCompraData.guardarTicket(ticket);

            TicketCompra ticketReal = ticketCompraData.buscarTicketPorId(idTicket);

            String codigoReal = (ticketReal != null ? ticketReal.getCodigoTicket() : null);

            DetalleTicket detalleTicket = new DetalleTicket(
                    proyeccion.getIdProyeccion(),
                    lugaresSeleccionados,
                    lugaresSeleccionados.size(),
                    monto,
                    ticketReal
            );

            DetalleTicket detalleCreado = detalleTicketData.guardarDetalleTicket(detalleTicket);

            for (Lugar lugar : detalleCreado.getLugares()) {
                lugaresData.vincularLugarADetalle(lugar.getIdLugar(), detalleCreado.getIdDetalle());
            }

            connection.commit();
            return codigoReal;

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Hubo un error en el flujo de compra", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
