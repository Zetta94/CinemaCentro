/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Manuel Zuñiga
 */

import java.time.LocalDate;

public class TicketCompra {

    private int idTicket;               
    private LocalDate fechaCompra;
    private LocalDate fechaFuncion;
    private double monto;
    private Comprador comprador;       
    private Lugar lugar; 
    private int idComprador;


    public TicketCompra() {
    }

    public TicketCompra(int idTicket, LocalDate fechaCompra, LocalDate fechaFuncion,
                        double monto, Comprador comprador, Lugar lugar) {
        this.idTicket = idTicket;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.comprador = comprador;
        this.lugar = lugar;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(LocalDate fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

  public int getIdComprador() {
    return idComprador;
}

public void setIdComprador(int idComprador) {
    this.idComprador = idComprador;
    
 }
}

