/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simuladorpulgas;

import javax.swing.JFrame;
import simuladorpulgas.models.Garden;
import simuladorpulgas.views.GameWindow;
import simuladorpulgas.views.gWindow;

/**
 *
 * @author SOFIA RUDAS
 */
public class SimuladorPulgas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Garden garden = new Garden(0, 0, 500, 500);
        GameWindow panel = new GameWindow();
        panel.setGarden(garden);

        JFrame frame = new JFrame("Pulgas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack(); // Ajusta al tamaño preferido del panel
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
