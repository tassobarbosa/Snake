/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Tasso
 */
public class Snake {
    final int size = 25;
    
    private int centerX = 200;
    private int centerY = 350;
   
    int auxX = 0;
    int auxY = 0;
    
    private int direcao = 1;     
    
    public static Rectangle rect = new Rectangle(0, 0, 0, 0);

    
    public void update(){
        
        auxX = centerX;
        auxY = centerY;
        
        if(direcao == 1)centerX+=25; //R
        if(direcao == 2)centerX-=25; //L
        if(direcao == 3)centerY-=25; //D
        if(direcao == 4)centerY+=25; //U
        
        rect.setRect(centerX, centerY, size, size);
    }     
    
    public int getAuxX() {
        return auxX;
    }

    public int getAuxY() {
        return auxY;
    }

    public void setAuxX(int auxX) {
        this.auxX = auxX;
    }

    public void setAuxY(int auxY) {
        this.auxY = auxY;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void moveRight(){
        if(direcao!=2)
        direcao = 1;                
    }
    
    public void moveLeft(){
        if(direcao!=1)
        direcao = 2;
    }
    public void moveUp(){
        if(direcao!=4)
        direcao = 3;
    }
    public void moveDown(){
        if(direcao!=3)
        direcao = 4;
    }    
}
