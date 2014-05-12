/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tasso
 */
public class Food {
    Snake snake;
    
    final int size = 25;
    
    int max = 575;
    int min = 200;
    int maxY = 425;
    int minY = 0;
    int multiple = 25;       
    
    int value;
    int valueY;
    
    static final Random rand = new Random(System.currentTimeMillis());
    
    public Rectangle rect = new Rectangle(rand.nextInt((max - min) / multiple) * multiple + min,
            rand.nextInt((max - min) / multiple) * multiple + min, 25, 25);
    
    //update the food value
    public void update() {
        value = rand.nextInt((max - min) / multiple) * multiple + min;
        valueY = rand.nextInt((maxY - minY) / multiple) * multiple + minY;
    
    if(!check())    update();
    else            rect.setRect(value, valueY, size, size);
}
   //method to avoid the food be created on the snake
    boolean check(){   
            ArrayList body = StartingClass.getBody();
            for (int i = 0; i < body.size(); i++) {
                Snake p = (Snake) body.get(i);
                if(value==p.getCenterX() && valueY==p.getCenterY())
                    return false;
    }
        return true;
    }
}