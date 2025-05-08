/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgaslocas.models;

/**
 * Hilo encargado de generar automáticamente pulgas normales y mutantes
 * dentro del jardín a intervalos regulares de tiempo.
 * 
 * 
 * @author sofia bermudez
 * @version 1.0.0
 * @since 07052025
 */
public class generadorPulgas extends Thread {
    
    /**
     * Referencia al jardín donde se agregarán las pulgas.
     */
    private Garden garden;
    
    /**
     * Controla si el hilo debe seguir ejecutándose.
     */
    private boolean ejecutando = true;
    
     /**
     * Momento en el que se agregó la última pulga normal.
     */
    private long ultimaNormal = 0;
    
    /**
     * Momento en el que se agregó la última pulga mutante.
     */
    private long ultimaMutante = 0;
    
     /**
     * Crea un nuevo generador de pulgas para el jardín dado.
     *
     * @param garden Objeto de tipo Garden en el que se generarán pulgas.
     */

    public generadorPulgas(Garden garden) {
        this.garden = garden;
    }
    
    /**
     * Método principal del hilo que se ejecuta continuamente mientras esté activo.
     * Verifica si ha pasado el tiempo suficiente para generar nuevas pulgas y las agrega.
     */

    @Override
    public void run() {
        while (ejecutando) {
            long ahora = System.currentTimeMillis();
            
            if (ahora - ultimaNormal >= 5000) {
                garden.agregarPulgaNormal();
                ultimaNormal = ahora;
            }
            
            if (ahora - ultimaMutante >= 10000) {
                garden.agregarPulgaMutante();
                ultimaMutante = ahora;
            }
            
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Detiene la ejecución del hilo de generación de pulgas.
     * Establece la bandera {@code ejecutando} como falsa.
     */
    
    public void detener() {
        ejecutando = false;
    }
}