/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pulgaslocas;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import pulgaslocas.ui.GameWindow;

/**
 * inicia la interfaz gráfica del juego mediante una ventana de tipo JFrame
 * 
 * @author SOFIA RUDAS
 * @since 07052025
 * @version 1.0.0
 */
public class PulgasLocas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Pulgas Locas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameWindow gameWindow = new GameWindow();
        frame.add(gameWindow);

        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false);
        frame.setVisible(true);

        gameWindow.requestFocusInWindow();
        });
    }
    
}
