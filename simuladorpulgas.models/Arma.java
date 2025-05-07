/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

import simuladorpulgas.SimuladorPulgas;

/**
 *
 * @author sofia bermudez
 */
public abstract class Arma {
    private String nombre;
    private String tipo;
    
    public Arma(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public abstract String getDescripcion();
    
    public abstract void usar(Garden garden, int x, int y);
}

