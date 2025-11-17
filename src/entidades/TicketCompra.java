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
    private String dniComprador;
    private String nombreComprador;
    private String medioPago;
    private String tituloPelicula;

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

    public String getDniComprador() {
        return dniComprador;
    }

    public void setDniComprador(String dniComprador) {
        this.dniComprador = dniComprador;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    @Override
    public String toString() {
        return String.format(
                "🎟 Ticket Nº %d\n"
                + "Película: %s\n"
                + "Fecha de función: %s\n"
                + "Fecha de compra: %s\n"
                + "Monto: $%.2f\n"
                + "Comprador: %s (DNI: %s)\n"
                + "Medio de pago: %s\n"
                + "Código de retiro: %s\n",
                idTicket,
                tituloPelicula,
                fechaFuncion,
                fechaCompra,
                monto,
                nombreComprador,
                dniComprador,
                medioPago,
                codigoTicket
        );
    }
}
