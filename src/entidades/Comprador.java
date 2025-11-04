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

public class Comprador {

    private int idComprador;
    private String dni;
    private String nombre;
    private LocalDate fechaNac;
    private String password;
    private String medioPago;

    public Comprador() {
    }

    public Comprador(int idComprador, String dni, String nombre, LocalDate fechaNac, String password, String medioPago) {
        this.idComprador = idComprador;
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.password = password;
        this.medioPago = medioPago;
    }

    public Comprador(String dni, String nombre, LocalDate fechaNac, String medioPago) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.medioPago = medioPago;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

}
