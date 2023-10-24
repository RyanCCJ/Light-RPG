/* NCKU, EE.108B
   Ryan, Chung
*/

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class hw5{

    public static void main( String[] args ){
        
        GUI game = new GUI();
        game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //set warning message when clicking the close button
        WindowAdapter w = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result=JOptionPane.showConfirmDialog(game,
                                                         "未存檔的進度將不會被保存",
                                                         "確定要結束遊戲嗎？",
                                                         JOptionPane.YES_NO_OPTION,
                                                         JOptionPane.WARNING_MESSAGE);
                if (result==JOptionPane.YES_OPTION) System.exit(0);
            }
        };
        
        //set a loop to choose menu
        int MenuChoose;
        boolean MenuOutput = true;
        while( MenuOutput ){

            try { Thread.sleep(100); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            
            MenuChoose = game.MenuChoose();
            switch( MenuChoose ){
                    
                //main menu
                case 0:
                    
                    game.Menu();
                    game.repaint();
                    game.setVisible( true );
                    game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                    game.removeWindowListener(w);
                    MenuChoose = game.MenuChoose(4);
                    break;
                    
                //start
                case 1:
                    
                    game.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
                    game.addWindowListener(w);
                    
                    //check files and show the story
                    game.Menu_Start();
                    if(!game.fileExist()){
                        game.fileExist(true);
                        game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                        game.removeWindowListener(w);
                        MenuChoose = game.MenuChoose(4);
                        break;
                    }
                    boolean check = false, check_2 = false, check_3 = game.LoadCheck();
                    if(check_3){
                    game.repaint();
                    game.setVisible( true );
                    while(!check){
                        
                        //loading the game
                        System.out.print("L");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("o");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("a");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("d");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("i");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("n");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("g");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("...");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        System.out.print("\b\b\b\b\b\b\b\b\b\b          \b\b\b\b\b\b\b\b\b\b");
                        try {
                            Thread.sleep(200);
                            
                        }catch (InterruptedException e) {
                        }
                        
                        check_2 = game.check();
                        if(check_2) check = true;
                        if(check)   check_2 = game.check(false);
                    }
                    }
                    //start the game
                    game.StartGame();
                    game.repaint();
                    game.setVisible( true );
                    game.Instructions();
                    MenuChoose = game.MenuChoose(0);
                    break;
                    
                //file
                case 2:
                    game.Menu_Load();
                    game.repaint();
                    game.setVisible( true );
                    MenuChoose = game.MenuChoose(4);
                    break;
                    
                //option
                case 3:
                    game.Menu_Option();
                    game.repaint();
                    game.setVisible( true );
                    MenuChoose = game.MenuChoose(4);
                    break;
                    
                default:
                    break;
            }
        }
    }
}