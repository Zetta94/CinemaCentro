/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Manuel Zuñiga
 */
public class Lugar {

    private int idLugar;
    private String fila;
    private int numero;
    private boolean ocupado;
    private int idProyeccion;

    public Lugar() {
    }

    public Lugar(int idProyeccion, String fila, int numero, boolean ocupado, int idFuncion) {
        this.idLugar = idProyeccion;
        this.fila = fila;
        this.numero = numero;
        this.ocupado = ocupado;
        this.idProyeccion = idFuncion;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(int idFuncion) {
        this.idProyeccion = idFuncion;
    }

    @Override
    public String toString() {
        return fila + numero;
    }
}
