/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa un arma tipo Misil que elimina al azar la mitad de las pulgas 
 * (normales y mutantes) presentes en el jardín al momento de su activación
 * 
 * @author SOFIA RUDAS
 * @version 1.0.0
 * @since 07052025
 */
public class Misil extends Armas {

    /**
     * Constructor que inicializa el arma con el nombre "Misil".
     */
    public Misil() {
        super("Misil");
    }
    
    /**
     * Usa el arma en el jardín indicado, eliminando al azar la mitad del total
     * de pulgas (normales y mutantes) presentes en él.
     * 
     * @param garden El jardín sobre el cual se aplica el arma.
     * @param x Coordenada x (no utilizada en esta implementación).
     * @param y Coordenada y (no utilizada en esta implementación).
     */

    @Override
    public void usar(Garden garden, int x, int y) {
        ArrayList<PulgaNormal> normales = garden.getPulgasNormales();
        ArrayList<PulgaMutante> mutantes = garden.getPulgasMutantes();
        Random random = new Random();

        int totalPulgas = normales.size() + mutantes.size();
        int cantidadAEliminar = (totalPulgas + 1) / 2; 

        int eliminadas = 0;

        while (eliminadas < cantidadAEliminar && (normales.size() > 0 || mutantes.size() > 0)) {

            boolean eliminarNormal = normales.size() > 0 && (mutantes.size() == 0 || random.nextBoolean());

            if (eliminarNormal) {
                int index = random.nextInt(normales.size());
                normales.remove(index);
            } else {
                int index = random.nextInt(mutantes.size());
                mutantes.remove(index);
            }

            eliminadas++;
        }
    }
}
