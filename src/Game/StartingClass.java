/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tasso
 */
public class StartingClass extends Applet implements Runnable, KeyListener{



        enum GameState {
		Running, Dead
	}
        GameState state = GameState.Running;
    
    private static Snake snake;
    public static Food food;
    private static ArrayList<Snake> body = new ArrayList<>();  
    
    int restartBtn = 0;
    
    int speed=100;
    int auxSpeed=0;
    int level=1;
    
    public static int score = 0;
    
    private Font font = new Font(null, Font.BOLD, 20);
    
    @Override
    public void init() {
        setSize(800, 479);
        setFocusable(true);
        addKeyListener(this);
    }            

    @Override
    public void start() {
        snake = new Snake();
        food = new Food();
        body.add(snake); 
        Thread thread =  new Thread(this);
        thread.start();
    }   
    
    public void restart(){
        body.clear();
        snake = new Snake();
        food = new Food();
        body.add(snake);
        score=0;
        level=1;
        speed=100;
        restartBtn = 1;
        state = GameState.Running;
    } 
    
    @Override
    public void run() { 
        while(true){            
            if (state == GameState.Running) {
            snake.update();
            
            //test collision between the food and the head snake
            if(checkCollision(snake.rect, food.rect)){
                food.update();
                Snake p = new Snake();
                body.add(p);
                score++;
                auxSpeed++;
                if(auxSpeed==5 && speed>50){
                    speed-=5;
                    level++;
                    auxSpeed=0;
                }
                        
            }
            
            //wall collision
            if(snake.getCenterY()<0)state=GameState.Dead;
            if(snake.getCenterY()>450)state=GameState.Dead;
            if(snake.getCenterX()<200)state=GameState.Dead;
            if(snake.getCenterX()>576)state=GameState.Dead;  
            
            //update the values body with the last position of the next Snake
            //and the actual Snake last position
            for(int i=1;i<body.size();i++){
                Snake a = (Snake) body.get(i-1);
                Snake b =  (Snake) body.get(i);
                
                b.setAuxX(b.getCenterX());
                b.setAuxY(b.getCenterY());
                
                b.setCenterX(a.getAuxX());
                b.setCenterY(a.getAuxY()); 
            }
            
            //test the collision between the head snake with any body snake
             if(body.size()>4)
                for(int i=4;i<body.size();i++){
                    Snake b =  (Snake) body.get(i);
                    if(checkCollision(snake.getCenterX(), snake.getCenterY(), body.get(i).getCenterX(), body.get(i).getCenterY())){
                        state=GameState.Dead;
                    }
                }
            
            repaint();
            
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
      } 
    }

    @Override
    public void paint(Graphics grphcs) {
        
        grphcs.setFont(font);
        grphcs.drawString("Snake 1.0", 50, 50);
        grphcs.drawString("Score  "+score, 630, 50);
        grphcs.drawString("Level  "+level, 630, 100);
        grphcs.drawString("Speed  "+speed, 630, 150);
        
        if(speed==50) grphcs.drawString("Max Speed, Run!", 630, 200);
        
        //--I created a restart flag to set the variable STATE inside the paint method
        if(restartBtn==1){
            state = GameState.Running;
            restartBtn=0;
        }
        
        if (state == GameState.Running) {
        //frame
        grphcs.drawRect(198,0, 404, 478);
        
        //snake and body
        for(int i=0;i<body.size();i++){
            //atualiza quadrados da snake
            Snake b =  (Snake) body.get(i);
            b.rect.setRect(b.getCenterX(), b.getCenterY(), 25, 25);
            
            //draw the snake and body
            grphcs.drawRect((int)b.rect.getX(),(int)b.rect.getY(), 25, 25);                
        }
        
        //draw the food
        grphcs.fillRect((int)food.rect.getX(), (int)food.rect.getY(), food.size, food.size);
        
        }else if (state == GameState.Dead) {
			grphcs.setColor(Color.BLACK);
			grphcs.fillRect(0, 0, 800, 480);
			grphcs.setColor(Color.WHITE);
			grphcs.drawString("Dead", 360, 190);
                        grphcs.drawString("Enter to Restart", 310, 240);
        }
    } 
        
    boolean checkCollision(Rectangle r1, Rectangle r2) {
		if (r1.intersects(r2)){
			return true;			
			}
                return false;
		}
    
    boolean checkCollision(int x, int y, int a, int b){
        if(x==a && y==b) return true;
        else
        return false;    
    }

   public static ArrayList<Snake> getBody() {
        return body;
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            snake.moveUp();
            break;
            
        case KeyEvent.VK_DOWN:
            snake.moveDown();
            break;
            
        case KeyEvent.VK_RIGHT:
            snake.moveRight();
            break;
            
        case KeyEvent.VK_LEFT:
            snake.moveLeft();
            break;
            
        case KeyEvent.VK_ENTER:
            if(state == GameState.Dead){                
                restart();
            }
            break;            
                
                }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    
}
