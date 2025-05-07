/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

import simuladorpulgas.SimuladorPulgas;

/**
 *
 * @author SOFIA RUDAS
 */
public class Pistola extends Arma {
    public Pistola() {
        super("Pistola", "Arma de corto alcance");
    }
    
    @Override
    public String getDescripcion() {
        return "Pistola simple que elimina una pulga a la vez";
    }
    
    public void usar(Garden garden, int x, int y) {
        garden.dispararPistola(x, y);
    }

    public void usar(SimuladorPulgas sim, int x, int y) {
   
    }
}

