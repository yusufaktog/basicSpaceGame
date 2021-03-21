/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author joseph
 */
public class SpaceGame extends JPanel implements KeyListener,ActionListener{
    Timer timer  = new Timer(5,this);
    private int passedTime = 0;private int bulletCount = 0;
    private BufferedImage image;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int ballPosX = 0; private int ballMovementX = 4;
    private int bulletMovementY = 5; private int spaceShipPosX = 0;
    private int spaceShipMovementX = 30;

    public SpaceGame() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("spaceShip.png")));
        } catch (IOException ex) {
            Logger.getLogger(SpaceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        passedTime+=5;
        g.setColor(Color.red);
        g.fillOval(ballPosX, 0 , 25, 25);
        
        g.setColor(Color.CYAN);
        for (Bullet bullet : bullets) {
            if(bullet.getPosY() >= 0)
                g.fillRect(bullet.getPosX(), bullet.getPosY(), 5, 10);
            
        }
        if(isFinished()){
            timer.stop();
            int option = JOptionPane.showConfirmDialog(this, "Would you like to continue ?","Select an option...",JOptionPane.YES_NO_OPTION);
            if(option == 0){    
                GameScreen.gameScreen.dispose();
                GameScreen.initiateTheGame();
            }
            else{
                
                JOptionPane.showMessageDialog(this, "Time Passed : " + passedTime/1000 + " seconds\nbullet count: " + bulletCount);
                System.exit(0);
            }
        }
        //g.drawLine(WIDTH, WIDTH, WIDTH, WIDTH);
        g.drawImage(image , spaceShipPosX , 450 , image.getHeight()/2 , (image.getWidth()/2) , this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

       
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        if(key == KeyEvent.VK_LEFT){
            if( spaceShipPosX <= 0)
                spaceShipPosX = this.getWidth();
            else{
                spaceShipPosX -= spaceShipMovementX;
                
            }
            
        }
            
        if(key == KeyEvent.VK_RIGHT){
            if( spaceShipPosX >= this.getWidth())
                spaceShipPosX = 0;
            else{
                spaceShipPosX += spaceShipMovementX;
                
            }
        }
        if(key == KeyEvent.VK_SPACE){
            bullets.add( new Bullet(spaceShipPosX + 52, (int) (this.getHeight()-image.getHeight()/2.4)) );
            bulletCount++;

        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        
        timer.start();
        ballPosX += ballMovementX;
        if(ballPosX == this.getWidth()   )
            ballMovementX *=-1;
            
        
        if(ballPosX == 0)
            ballMovementX *=-1;
        
        for (Bullet bullet : bullets) {
            bullet.setPosY(bullet.getPosY() - bulletMovementY);
            
        }

            
        repaint();
        
       
    }
    public boolean isFinished(){
        for (Bullet bullet : bullets) {
            if(new Rectangle(bullet.getPosX(),bullet.getPosY(),5,10).intersects(new Rectangle(ballPosX,0,20,20)) )
                return true;
            
            
        }
        return false;
    }
    
    
}
