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
    private int idFuncion;        


    public Lugar() {
    }

    public Lugar(int idLugar, String fila, int numero, boolean ocupado, int idFuncion) {
        this.idLugar = idLugar;
        this.fila = fila;
        this.numero = numero;
        this.ocupado = ocupado;
        this.idFuncion = idFuncion;
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

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

}

