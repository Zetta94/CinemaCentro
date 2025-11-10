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
    private int idProyeccion;   
    private int idTicket;
    private List<Lugar> lugares;    
    private int cantidad;          
    private double subtotal;        
    private TicketCompra ticket;  

    public DetalleTicket() {
    }

    public DetalleTicket(int idDetalle, int idProyeccion, List<Lugar> lugares, int cantidad, double subtotal, TicketCompra ticket) {
        this.idDetalle = idDetalle;
        this.idProyeccion = idProyeccion;
        this.lugares = lugares;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.ticket = ticket;
    }
    
    public DetalleTicket(int idProyeccion, List<Lugar> lugares, int cantidad, double subtotal, TicketCompra ticket) {
        this.idProyeccion = idProyeccion;
        this.lugares = lugares;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.ticket = ticket;
    }
  
    public int getIdDetalle() {
        return idDetalle;
    }

     public int getIdTicket() {
        return idTicket;
    }
     
     public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
     
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFuncion() {
        return idProyeccion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idProyeccion = idFuncion;
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
