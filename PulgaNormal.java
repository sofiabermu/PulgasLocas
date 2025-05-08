/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Representa una pulga normal dentro del jardín.
 * Las pulgas normales tienen solo un punto de resistencia y mueren al ser impactadas una vez.
 * 
 * 
 * @author sofia bermudez
 * @since 07052025
 * @version 1.0.0
 */
public class PulgaNormal extends Pulga{
    
    /**
     * Nivel de resistencia de la pulga. Comienza en 1.
     */
    int resistencia =1;
    
    /**
     * Constructor para crear una nueva pulga normal.
     *
     * @param x      Coordenada X inicial.
     * @param y      Coordenada Y inicial.
     * @param height Altura de la pulga.
     * @param width  Ancho de la pulga.
     */
    
    public PulgaNormal(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.image = new ImageIcon(getClass().getResource("/pulgaslocas/images/pulga_normal.png"));
    }
    
    /**
     * Método que se llama cuando la pulga es impactada por un arma.
     * Reduce su resistencia, y si llega a 0, se marca como no viva.
     */
    
    public void pulgaImpactada(){
        resistencia --;
        if(resistencia ==0){
            viva = false;
        }
    }
    
    /**
     * Dibuja la imagen de la pulga normal en pantalla.
     *
     * @param g Objeto Graphics usado para pintar.
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
