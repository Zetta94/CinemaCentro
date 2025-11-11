package entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketCompra {

    private int idTicket;
    private LocalDate fechaCompra;
    private LocalDate fechaFuncion;
    private double monto;
    private int idComprador;
    private String codigoTicket;
    private Comprador comprador;
    private Pelicula pelicula;
    private LocalTime hora;

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

    public String getCodigoTicket() {
        return codigoTicket;
    }

    public void setCodigoTicket(String codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "TicketCompra{" +
                "idTicket=" + idTicket +
                ", fechaCompra=" + fechaCompra +
                ", fechaFuncion=" + fechaFuncion +
                ", monto=" + monto +
                ", idComprador=" + idComprador +
                ", codigoTicket='" + codigoTicket + '\'' +
                ", pelicula=" + (pelicula != null ? pelicula.getTitulo() : "null") +
                ", comprador=" + (comprador != null ? comprador.getNombre() : "null") +
                ", hora=" + hora +
                '}';
    }
}
