/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simuladorpulgas.elements;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author SOFIA RUDAS
 */
public abstract class SpriteContainer extends JPanel implements GraphicContainer {

    protected int x, y, width, height;
    public ArrayList<Sprite> sprites = new ArrayList<>(); 
    
    public SpriteContainer(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }   
    
    public boolean add(Sprite sprite){
        return sprites.add(sprite);
    }
    
    public void remove(int index)
    {
        sprites.remove(index);
    }

    public void remove(Sprite sprite)
    {
        sprites.remove(sprite);
    }

}
