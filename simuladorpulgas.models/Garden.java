/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import simuladorpulgas.elements.Sprite;
import simuladorpulgas.elements.SpriteContainer;

/**
 *
 * @author sofia bermudez
 */
public class Garden extends SpriteContainer {
    private int puntaje = 0;
    private int puntajeMaximo = 0;
    private final String archivoPuntaje = "C:/Users/sofia/OneDrive/Desktop/pulgasLocas/puntaje_maximo.txt";

    private GeneradorPulgas generador;

    public Garden (int x, int y, int height, int width) {
        super(x, y, height, width);
        cargarPuntajeMaximo();
        iniciarGenerador();
    }
    
    private boolean hayColision(int x, int y, int tamaño) {
        Rectangle nueva = new Rectangle(x, y, tamaño, tamaño);
        for (Sprite s : sprites) {
            if (s instanceof Pulga && s.getBounds().intersects(nueva)) {
                return true;
            }
        }
        return false;
    }
    
    private int[] posicionLibre(int tamaño) {
        int x, y;
        int intentos = 0;
        do {
            x = aleatorioX();
            y = aleatorioY();
            intentos++;
            if (intentos > 100){
                break;
            } 
        } while (hayColision(x, y, tamaño));
        return new int[]{x, y};
    }

    public void agregarPulgaNormal() {
        PulgaNormal p = new PulgaNormal(this.aleatorioX(), this.aleatorioY(), 20);
        p.setGraphicContainer(this);
        this.add(p);
        System.out.println("Pulga normal agregada a mano en: " + p.getX() + ", " + p.getY());
        this.refresh(); 
    }

    public void agregarPulgaMutante() {
        PulgaMutante p = new PulgaMutante(this.aleatorioX(), this.aleatorioY(), 20);
        p.setGraphicContainer(this);
        this.add(p);
        System.out.println("Pulga mutante agregada a mano en: " + p.getX() + ", " + p.getY());
        this.refresh();
    }

    public void saltarPulgas() {
        Random r = new Random();
        for (Sprite s : sprites) {
            if (s instanceof Pulga p && p.estaViva()) {
                p.saltar(r, this.getBoundaries());
                System.out.println("La pulga ha saltado");
            }
        }
        this.refresh();
    }
    
    private boolean todasLasPulgasMuertas() {
        for (Sprite s : sprites) {
            if (s instanceof Pulga p && p.estaViva()) {
                return false;
            }
        }
        return true;
    }

    public void dispararPistola(int x, int y) {
        for (Sprite s : sprites) {
            if (s instanceof Pulga p && p.estaViva() && dentroDeRango(p, x, y)) {
                p.recibirImpacto();
                if (!p.estaViva()) puntaje++;
                break;
            }
        }
        
        System.out.println("Se disparó una pistola en (" + x + ", " + y + ")");
        
        if (todasLasPulgasMuertas()) {
            terminarGenerador();  
            finalizarPartida();  
        }

        this.refresh();
    }

    public void lanzarMisil() {
        int aEliminar = sprites.size() / 2;
        int eliminadas = 0;
        for (int i = 0; i < sprites.size() && eliminadas < aEliminar; i++) {
            Sprite s = sprites.get(i);
            if (s instanceof Pulga p && p.estaViva()) {
                p.recibirImpacto();
                if (!p.estaViva()) {
                    eliminadas++;
                    puntaje++;
                }
            }
        }
        
        System.out.println("Se lanzó un misil y eliminó " + eliminadas + " pulgas.");
        
        if (todasLasPulgasMuertas()) {
            terminarGenerador();
            finalizarPartida(); 
        }

        this.refresh();
    }

    private boolean dentroDeRango(Pulga p, int x, int y) {
        return x >= p.getX() && x <= p.getX() + p.getWidth() &&
               y >= p.getY() && y <= p.getY() + p.getHeight();
    }

    public int aleatorioX() {
        return (int)(Math.random() * (getBoundaries().width - 30));
    }

    public int aleatorioY() {
        return (int)(Math.random() * (getBoundaries().height - 30));
    }

    public void iniciarGenerador() {
        if (generador == null) {
            generador = new GeneradorPulgas(this);
            generador.start();
        }
    }

    public void terminarGenerador() {
        generador.detener();
    }

    public void guardarPuntajeMaximo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPuntaje))) {
            bw.write(String.valueOf(puntajeMaximo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarPuntajeMaximo() {
        File archivo = new File(archivoPuntaje);
        if (!archivo.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                bw.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            if (linea != null) {
                puntajeMaximo = Integer.parseInt(linea);
            }
        } catch (IOException | NumberFormatException e) {
            puntajeMaximo = 0;
        }
    }

    @Override
    public void refresh() {
        this.repaint();
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Call parent's paint method first
        
        // Draw the garden background
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
        
        // Draw all sprites (pulgas)
        for (Sprite sprite : sprites) {
            sprite.paint(g);
        }
        
        // Draw score
        g.setColor(Color.BLACK);
        g.drawString("Puntaje: " + puntaje, 10, 20);
        g.drawString("Puntaje Máximo: " + puntajeMaximo, 10, 40);
        
    }
    
    public void reiniciarPartida() {
        this.sprites.clear();         
        this.puntaje = 0;             
        this.iniciarGenerador();      
        this.refresh();               
    }
    
    private void finalizarPartida() {
        if (puntaje > puntajeMaximo) {
            puntajeMaximo = puntaje;
            guardarPuntajeMaximo();
        }

        int opcion = JOptionPane.showConfirmDialog(null,
            "¡Fin del juego!\nPuntaje: " + puntaje + "\nPuntaje máximo: " + puntajeMaximo + 
            "\n¿Deseas jugar de nuevo?", "Juego terminado", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarPartida();
        } else {
            System.exit(0);
        }
    }
}