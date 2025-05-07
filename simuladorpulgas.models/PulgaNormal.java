/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.models;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author SOFIA RUDAS
 */
public class PulgaNormal extends Pulga {
    
    public PulgaNormal(int x, int y, int size) {
        super(x, y, size, size);
        this.image = new ImageIcon(ClassLoader.getSystemResource("simuladorpulgas/images/pulga_normal.png"));
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(image.getImage(), x, y, width, height, null);
    }

    @Override
    public void recibirImpacto() {
        super.recibirImpacto();
    }
}

