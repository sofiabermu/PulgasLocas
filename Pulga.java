/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import pulgaslocas.elements.SpriteContainer;

/**
 * Clase abstracta que representa una pulga dentro del jardín.
 * Cada pulga tiene una posición, dimensiones y un estado de vida.
 * Puede saltar aleatoriamente dentro de los límites establecidos y ser dibujada.
 * 
 * 
 * @author sofia bermudez
 * @version 1.0.0
 * @since 07052025
 */
public abstract class Pulga extends SpriteContainer {
    
    /**
     * Indica si la pulga está viva.
     */
    protected boolean viva = true;
    
    /**
     * Constructor de la clase Pulga.
     *
     * @param x      Coordenada X inicial de la pulga.
     * @param y      Coordenada Y inicial de la pulga.
     * @param height Altura de la pulga.
     * @param width  Ancho de la pulga.
     */
    
    public Pulga(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
    
    /**
     * Verifica si la pulga está viva.
     *
     * @return true si la pulga está viva, false en caso contrario.
     */
    
    public boolean estaViva (){
        return viva;
    }
    
     /**
     * Hace que la pulga salte a una nueva posición aleatoria dentro de los límites especificados.
     *
     * @param boundaries Rectángulo que define el área válida de movimiento para la pulga.
     */
    
    public void saltar(Rectangle boundaries) {
        int nuevoX = boundaries.x + (int) (Math.random() * boundaries.width);
        int nuevoY = boundaries.y + (int) (Math.random() * boundaries.height);
        this.x = nuevoX;
        this.y = nuevoY;
    }
    
    /**
     * Método abstracto que debe ser implementado por las subclases para dibujar la pulga.
     *
     * @param g Objeto para realizar las operaciones de dibujo.
     */
    
    public abstract void paint (Graphics g);
    
}
