/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Manuel Zuñiga
 */

import java.util.List;

public class DetalleTicket {

    private int idDetalle;         
    private int idFuncion;          
    private List<Lugar> lugares;    
    private int cantidad;          
    private double subtotal;        
    private TicketCompra ticket;  

    public DetalleTicket() {
    }

    public DetalleTicket(int idDetalle, int idFuncion, List<Lugar> lugares, int cantidad, double subtotal, TicketCompra ticket) {
        this.idDetalle = idDetalle;
        this.idFuncion = idFuncion;
        this.lugares = lugares;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.ticket = ticket;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public TicketCompra getTicket() {
        return ticket;
    }

    public void setTicket(TicketCompra ticket) {
        this.ticket = ticket;
    }

}
