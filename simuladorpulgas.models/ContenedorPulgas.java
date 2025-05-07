/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.random;
import java.util.Random;
import simuladorpulgas.elements.Sprite;
import simuladorpulgas.elements.SpriteContainer;

/**
 *
 * @author sofia bermudez
 */
public class ContenedorPulgas extends SpriteContainer {

    private boolean generadorActivo = true;
    private Random random = new Random();

    public ContenedorPulgas(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void agregarPulgaSinColision(boolean mutante) {
        int intentos = 0;
        while (intentos < 100) {
            int x = (int)(Math.random() * (width - 30));
            int y = (int)(Math.random() * (height - 30));
            Pulga nueva = mutante ? new PulgaMutante(x, y, 20) : new PulgaNormal(x, y, 20);
            nueva.setGraphicContainer(this);
            Rectangle boundsNueva = new Rectangle(nueva.getX(), nueva.getY(), nueva.getWidth(), nueva.getHeight());

            boolean colision = false;
            for (Sprite s : sprites) {
                if (s instanceof Pulga p && p.estaViva()) {
                    Rectangle boundsExistente = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
                    if (boundsNueva.intersects(boundsExistente)) {
                        colision = true;
                        break;
                    }
                }
            }

            if (!colision) {
                this.add(nueva);
                this.refresh();
                return;
            }

            intentos++;
        }
    }

    public void saltarPulgas() {
        Rectangle boundaries = new Rectangle(0, 0, width, height);
        for (Sprite s : sprites) {
            if (s instanceof Pulga p && p.estaViva()) {
                p.saltar(random,boundaries);
            }
        }
        this.refresh();
    }

    public void dispararPistola(int x, int y) {
        for (Sprite s : sprites) {
            if (s instanceof Pulga p && p.estaViva() &&
                x >= p.getX() && x <= p.getX() + p.getWidth() &&
                y >= p.getY() && y <= p.getY() + p.getHeight()) {
                p.recibirImpacto();
                break;
            }
        }
        this.refresh();
    }

    public void terminarGenerador() {
        generadorActivo = false;
    }

    public boolean isGeneradorActivo() {
        return generadorActivo;
    }

    @Override
    public void paint(Graphics g) {
        for (Sprite s : sprites) {
            s.paint(g);
        }
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rectangle getBoundaries() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
