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
    private int idComprador;
    private String codigo;

    public TicketCompra() {
    }

    public TicketCompra(int idTicket, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, int idComprador) {
        this.idTicket = idTicket;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.idComprador = idComprador;
    }

    public TicketCompra(LocalDate fechaCompra, LocalDate fechaFuncion, double monto, int idComprador) {
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.idComprador = idComprador;
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

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;

    }
    
   public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
     @Override
    public String toString() {
        return "TicketCompra{" + 
                "idTicket=" + idTicket + 
                ", fechaCompra=" + fechaCompra + 
                ", fechaFuncion=" + fechaFuncion + 
                ", monto=" + monto + 
                ", idComprador=" + idComprador + 
                ", codigo='" + codigo + '\'' + 
                '}';
    }  
}
