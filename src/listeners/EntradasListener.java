/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listeners;

import entidades.Comprador;

/**
 *
 * @author Morbo
 */
public interface EntradasListener<T> {
    boolean validarDatos();
    T guardarDatos();
}
