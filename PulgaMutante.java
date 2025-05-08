/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Representa una pulga mutante dentro del jardín.
 * Las pulgas mutantes tienen mayor resistencia (pueden recibir más de un impacto)
 * y pueden transformarse en pulgas normales antes de morir completamente.
 * 
 * 
 * @author sofia bermudez
 */
public class PulgaMutante extends Pulga{
    
     /**
     * Nivel de resistencia de la pulga mutante. Comienza en 2.
     * Al recibir un impacto, disminuye.
     */
    int resistencia=2;
    
    /**
     * Referencia a la pulga normal en la que se transforma si recibe el primer impacto.
     */
    private PulgaNormal transformadaEn = null;
    
    /**
     * Constructor para crear una nueva pulga mutante.
     *
     * @param x      Coordenada X inicial.
     * @param y      Coordenada Y inicial.
     * @param height Altura de la pulga.
     * @param width  Ancho de la pulga.
     */

    public PulgaMutante(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.image = new ImageIcon(getClass().getResource("/pulgaslocas/images/pulga_mutante.png"));
    }
    
    /**
     * Método que se llama cuando la pulga es impactada por un arma.
     *
     * @return Una nueva PulgaNormal si se transforma, null si muere, o la misma pulga si sigue mutante.
     */
    
    public Pulga pulgaImpactada(){
        resistencia--;
        if (resistencia == 1) {
            transformarEnNormal();
            return transformadaEn; 
        }
        else if (resistencia <= 0) {
            viva = false; 
            return null; 
        } 
        else {
            return this; 
        }
    }
    
    /**
     * Transforma la pulga mutante en una pulga normal manteniendo su posición y tamaño.
     */
    
    private void transformarEnNormal() {
        transformadaEn = new PulgaNormal(this.x, this.y, this.height, this.width);
    }
    
    /**
     * Dibuja la imagen de la pulga mutante en pantalla.
     *
     * @param g Objeto utilizado para pintar.
     */

    @Override
    public void paint(Graphics g) {
        g.drawImage(image.getImage(), x, y, width, height, null);
    }
    
    /**
     * Actualiza el estado de la pulga. No realiza ninguna acción en esta implementación.
     */

    @Override
    public void refresh() {
        
    }
    
    /**
     * Retorna los límites del área ocupada por la pulga.
     *
     * @return Un objeto Rectangle que representa los límites.
     */

    @Override
    public Rectangle getBoundaries() { 
        return new Rectangle(x, y, width, height);  
    }
    
}
