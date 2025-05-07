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
public class Misil extends Arma {
    public Misil() {
        super("Misil", "Arma de área");
    }
    
    @Override
    public String getDescripcion() {
        return "Misil que elimina la mitad de las pulgas en el campo";
    }
    
    public void usar(Garden garden, int x, int y) {
        garden.lanzarMisil(); 
    }

    public void usar(SimuladorPulgas sim, int x, int y) {
        
    }
}

