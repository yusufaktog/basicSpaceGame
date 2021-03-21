/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacegame;

import javax.swing.JFrame;

/**
 *
 * @author joseph
 */
public class GameScreen extends JFrame{
    static GameScreen gameScreen;
    static SpaceGame game;
    
    public GameScreen(String title){
        super(title);
        
    }
    public static void initiateTheGame(){
        
        gameScreen = new GameScreen("Space Game");
        gameScreen.setBounds(550,275,800,600);
        gameScreen.setResizable(true);
        gameScreen.setFocusable(false);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new SpaceGame();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        gameScreen.add(game);
        gameScreen.setVisible(true);
        
    }
    public static void main(String[] args) {        
       initiateTheGame();
    }
    
}
