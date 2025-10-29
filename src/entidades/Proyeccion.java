
package entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Proyeccion {
    private int idProyeccion;
    private Pelicula pelicula;
    private Sala sala;
    private String idioma;
    private boolean es3D;
    private boolean subtitulada;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private double precio;

    public Proyeccion() {}

    public Proyeccion(int idProyeccion, Pelicula pelicula, Sala sala, String idioma, boolean es3D, boolean subtitulada, LocalTime horaInicio, LocalTime horaFin, LocalDate fecha, double precio) {
        this.idProyeccion = idProyeccion;
        this.pelicula = pelicula;
        this.sala = sala;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.precio = precio;
    }

    //  Getters  //
    public int getIdProyeccion() {
        return idProyeccion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public String getIdioma() {
        return idioma;
    }

    public boolean isEs3D() {
        return es3D;
    }

    public boolean isSubtitulada() {
        return subtitulada;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public double getPrecio() {
        return precio;
    }

    //  Setters  //
    public void setIdProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setEs3D(boolean es3D) {
        this.es3D = es3D;
    }

    public void setSubtitulada(boolean subtitulada) {
        this.subtitulada = subtitulada;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public void setFecha (LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

   // toString //
    @Override
    public String toString() {
        return "Sala: " + sala + " - " + "Horario: " + horaInicio;
    }
}