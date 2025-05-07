/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

/**
 *
 * @author SOFIA RUDAS
 */
public class GeneradorPulgas extends Thread {
    private Garden garden;
    private boolean ejecutando = true;
    private long ultimaNormal = 0;
    private long ultimaMutante = 0;

    public GeneradorPulgas(Garden garden) {
        this.garden = garden;
    }

    @Override
    public void run() {
        while (ejecutando) {
            long ahora = System.currentTimeMillis();
            
            // Agregar pulga normal cada 5 segundos (5000 ms)
            if (ahora - ultimaNormal >= 5000) {
                garden.agregarPulgaNormal();
                ultimaNormal = ahora;
                System.out.println("Pulga normal agregada");
            }
            
            // Agregar pulga mutante cada 10 segundos (10000 ms)
            if (ahora - ultimaMutante >= 10000) {
                garden.agregarPulgaMutante();
                ultimaMutante = ahora;
                System.out.println("Pulga mutante agregada");
            }
            
            try {
                Thread.sleep(100); // Pequeña pausa para no saturar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void detener() {
        ejecutando = false;
    }
}

