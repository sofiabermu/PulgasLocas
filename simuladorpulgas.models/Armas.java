/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

/**
 * Clase abstracta que representa un arma dentro del juego 
 * 
 * Esta clase sirve como base para armas concretas que afectan el jardín y las entidades dentro de él.
 * 
 * @author SOFIA RUDAS
 * @since 07052025
 * @version 1.0.0
 */
public abstract class Armas {
    
    /** Tipo de arma */
    private String tipo;
    
    /**
     * Crea una nueva arma con el tipo especificado.
     *
     * @param tipo el tipo de arma
     */
    
    public Armas( String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Obtiene el tipo del arma.
     *
     * @return el tipo de arma como cadena
     */
    
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Establece o modifica el tipo del arma.
     *
     * @param tipo el nuevo tipo de arma
     */
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Método abstracto que define la acción de usar el arma en una posición específica del jardín.
     *
     * @param garden el jardín sobre el cual se aplica el arma
     * @param x coordenada horizontal dentro del jardín
     * @param y coordenada vertical dentro del jardín
     */
    
    public abstract void usar(Garden garden, int x, int y);
}
