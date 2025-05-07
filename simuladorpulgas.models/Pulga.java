/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import simuladorpulgas.elements.SpriteMobile;

/**
 *
 * @author SOFIA RUDAS
 */
public abstract class Pulga extends SpriteMobile {
    protected boolean viva = true;

    public Pulga(int x, int y, int width, int height) {
        super(x, y, height, width);
    }

    public boolean estaViva() {
        return viva;
    }

    public void recibirImpacto() {
        viva = false;
    }
    public void saltar(Random r, Rectangle boundaries) {
        int newX = r.nextInt(boundaries.width - this.width);
        int newY = r.nextInt(boundaries.height - this.height);

        this.x = newX;
        this.y = newY;
    }
    public void saltar() {
        // Get the boundaries from the container
        Rectangle boundaries = getGraphicContainer().getBoundaries();
        // Create a random generator
        Random r = new Random();
        // Call the parameterized method
        saltar(r, boundaries);
    }

    public abstract void paint(Graphics g);
}
