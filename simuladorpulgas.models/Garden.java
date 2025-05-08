/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import pulgaslocas.elements.SpriteContainer;

/**
 * Clase que representa el jardín del juego, donde se desarrollan las interacciones
 * con las pulgas normales y mutantes.
 * 
 * 
 * @author SOFIA RUDAS
 * @since 07052025
 * @version 1.0.0
 */
public class Garden extends SpriteContainer {
    
    private ArrayList<PulgaNormal> pulgasNormales;
    private ArrayList<PulgaMutante> pulgasMutantes;
    private int puntaje = 0;
    private int puntajeMaximo = 0;
    private generadorPulgas generador;
    private File archivoPuntaje = new File("puntaje_maximo.txt");
    private Random random = new Random();
    public static final int SPEED = 5;
    
    /**
     * Crea un nuevo jardín con las dimensiones y posición dadas.
     * 
     * @param x coordenada X del jardín
     * @param y coordenada Y del jardín
     * @param height altura del jardín
     * @param width ancho del jardín
     */
    
    public Garden(int x, int y, int height, int width) {
        super(x, y, height, width);
        pulgasNormales = new ArrayList<>();
        pulgasMutantes = new ArrayList<>();
        cargarPuntajeMaximo();
        generador = new generadorPulgas(this);
        generador.start();
    }
    
    /**
     * Agrega una nueva pulga normal al jardín en una posición aleatoria.
     */
    
    public void agregarPulgaNormal() {
        int pulga_x = random.nextInt(width - 50);
        int pulga_y = random.nextInt(height - 50);
        PulgaNormal nueva = new PulgaNormal(pulga_x, pulga_y, 50, 50);
        pulgasNormales.add(nueva);
    }
    
     /**
     * Agrega una nueva pulga mutante al jardín en una posición aleatoria.
     */
    
    public void agregarPulgaMutante() {
        int pulga_x = random.nextInt(width - 50);
        int pulga_y = random.nextInt(height - 50);
        PulgaMutante nueva = new PulgaMutante(pulga_x, pulga_y, 50, 50);
        pulgasMutantes.add(nueva);
    }
    
    /**
     * Retorna la lista de pulgas normales presentes en el jardín.
     * 
     * @return lista de objetos PulgaNormal
     */
    
    public ArrayList<PulgaNormal> getPulgasNormales() {
        return pulgasNormales;
    }
    
    /**
     * Retorna la lista de pulgas mutantes presentes en el jardín.
     * 
     * @return lista de objetos PulgaMutante
     */
    
    public ArrayList<PulgaMutante> getPulgasMutantes() {
        return pulgasMutantes;
    }
    
    /**
     * Retorna el puntaje actual del jugador.
     * 
     * @return puntaje actual
     */
    
    public int getPuntaje() {
        return puntaje;
    }
    
    /**
     * Aumenta el puntaje del jugador y actualiza el puntaje máximo si es necesario.
     * 
     * @param puntos cantidad de puntos a sumar
     */
    
    public void aumentarPuntaje(int puntos) {
        puntaje += puntos;
        if (puntaje > puntajeMaximo) {
            puntajeMaximo = puntaje;
            guardarPuntajeMaximo();
        }
    }
    
    /**
     * Retorna el puntaje máximo registrado.
     * 
     * @return puntaje máximo
     */
    
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }
    
    /**
     * Guarda el puntaje máximo actual en un archivo para persistencia.
     */
    
    public void guardarPuntajeMaximo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPuntaje))) {
            bw.write(String.valueOf(puntajeMaximo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Carga el puntaje máximo desde un archivo, si existe.
     */
    
    public void cargarPuntajeMaximo() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPuntaje))) {
            String linea = br.readLine();
            if (linea != null) {
                puntajeMaximo = Integer.parseInt(linea);
            }
        } catch (IOException | NumberFormatException e) {
            puntajeMaximo = 0;
        }
    }
    
     /**
     * Detiene el hilo generador de pulgas y guarda el puntaje máximo.
     */
    
    public void detenerJuego() {
        if (generador != null) {
            generador.detener();
        }
        guardarPuntajeMaximo();
    }
    
     /**
     * Reinicia el estado del juego, incluyendo puntaje y listas de pulgas.
     */
    
    public void reiniciarJuego() {
        puntaje = 0;
        pulgasNormales.clear();
        pulgasMutantes.clear();
        if (generador != null) {
            generador.detener();
        }
        generador = new generadorPulgas(this);
        generador.start();
    }
    
    /**
     * Dibuja todas las pulgas del jardín.
     * 
     * @param g contexto gráfico para pintar
     */
    
    @Override
    public void paint(Graphics g) {

        for (int i = 0; i < pulgasNormales.size(); i++) {
            pulgasNormales.get(i).paint(g);
        }
        
        for (int i = 0; i < pulgasMutantes.size(); i++) {
            pulgasMutantes.get(i).paint(g);
        }
    }
    
    /**
     * Actualiza el estado de las pulgas y detiene el juego si se alcanza el límite.
     */
    
    @Override
    public void refresh() {

        for (int i = 0; i < pulgasNormales.size(); i++) {
            PulgaNormal pulga = pulgasNormales.get(i);
            moverPulga(pulga);
            pulga.refresh();
        }
        
        for (int i = 0; i < pulgasMutantes.size(); i++) {
            PulgaMutante pulga = pulgasMutantes.get(i);
            moverPulga(pulga);
            pulga.refresh();
        }
        
        if (pulgasNormales.size() + pulgasMutantes.size() >= 20) {
            detenerJuego();
        }
    }
    
    /**
     * Mueve aleatoriamente una pulga dentro de los límites del jardín.
     * 
     * @param pulga la pulga a mover
     */
    
    public void moverPulga(Pulga pulga) {

        int movX = random.nextInt(2*SPEED+1) - SPEED; 
        int movY = random.nextInt(2*SPEED+1) - SPEED; 

        int newX = pulga.x + movX;
        int newY = pulga.y + movY;

        if (newX >= x && newX <= (x + width - pulga.width)) {
            pulga.x = newX;
        } else {
            pulga.x -= movX;
        }

        if (newY >= y && newY <= (y + height - pulga.height)) {
            pulga.y = newY;
        } else {
            pulga.y -= movY;
        }
    }
    
    /**
     * Hace que todas las pulgas salten dentro de los límites del jardín.
     */
   
    public void hacerSaltarPulgas() {
        Rectangle limits = getBoundaries();
        
        for (int i = 0; i < pulgasNormales.size(); i++) {
            pulgasNormales.get(i).saltar(limits);
        }
        
        for (int i = 0; i < pulgasMutantes.size(); i++) {
            pulgasMutantes.get(i).saltar(limits);
        }
    }
    
    /**
     * Retorna el rectángulo que representa los límites del jardín.
     * 
     * @return límites del contenedor como Rectangle
     */
    
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
