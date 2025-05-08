/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

/**
 * Clase que representa un arma de tipo Pistola.
 * La pistola permite impactar una sola pulga (normal o mutante) que se encuentre en la posición clickeada.
 * Si impacta a una mutante, esta puede transformarse en una pulga normal antes de morir completamente.
 *
 * 
 * @author SOFIA RUDAS
 * @version 1.0.0
 * @since 07052025
 */
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Pistola extends Armas {

    /**
     * Constructor que inicializa el arma con el nombre "Pistola".
     */
    public Pistola() {
        super("Pistola");
    }
    
    /**
     * Usa la pistola sobre la posición especificada en el jardín.
     * Intenta impactar primero una pulga mutante, y si no hay ninguna en esa posición, una pulga normal.
     * 
     * @param garden El jardín donde se encuentran las pulgas.
     * @param x Coordenada horizontal del clic.
     * @param y Coordenada vertical del clic.
     */

    @Override
    public void usar(Garden garden, int x, int y) {
        Point click = new Point(x, y);
        ArrayList<PulgaNormal> normales = garden.getPulgasNormales();
        ArrayList<PulgaMutante> mutantes = garden.getPulgasMutantes();

        for (int i = 0; i < mutantes.size(); i++) {
            PulgaMutante mutante = mutantes.get(i);
            if (mutante.getBoundaries().contains(click)) {
                Pulga resultado = mutante.pulgaImpactada();

                if (!mutante.estaViva()) {
                    mutantes.remove(i);
                } else if (resultado instanceof PulgaNormal) {
                    mutantes.remove(i);
                    normales.add((PulgaNormal) resultado);
                }

                return; 
            }
        }

        for (int i = 0; i < normales.size(); i++) {
            PulgaNormal normal = normales.get(i);
            if (normal.getBoundaries().contains(click)) {
                normal.pulgaImpactada();
                if (!normal.estaViva()) {
                    normales.remove(i);
                }
                return;
            }
        }
    }
}


