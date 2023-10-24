/* NCKU, EE.108B
   Ryan, Chung
*/

import java.io.*;
import java.util.*;

public class ReadFiles{
    
    private File thisFile;
    private int exist, monkHP, monkMP, monkFP, robberHP, robberMP, robberFP, bardHP, bardMP, bardFP, day, food, drink, gold, treasure, jewel, egg, house, role, role_X, role_Y;
    
    //constructor
    public ReadFiles(File newFile){
        
        thisFile = newFile;
        
        try{
            Scanner file = new Scanner( newFile );
            
            while( file.hasNextLine() ){
                
                //read a line
                Scanner scn = new Scanner(file.nextLine());
                scn.useDelimiter(",");
                
                //set variables
                exist = scn.nextInt();
                monkHP = scn.nextInt();
                monkMP = scn.nextInt();
                monkFP = scn.nextInt();
                robberHP = scn.nextInt();
                robberMP = scn.nextInt();
                robberFP = scn.nextInt();
                bardHP = scn.nextInt();
                bardMP = scn.nextInt();
                bardFP = scn.nextInt();
                day = scn.nextInt();
                food = scn.nextInt();
                drink = scn.nextInt();
                gold = scn.nextInt();
                treasure = scn.nextInt();
                jewel = scn.nextInt();
                egg = scn.nextInt();
                house = scn.nextInt();
                role = scn.nextInt();
                role_X = scn.nextInt();
                role_Y = scn.nextInt();
                
                scn.close();
            }
        }catch(IOException e){
            System.out.println("\nIO exception happend.");
        }
    }
    
    //check if the file is used
    public int exist(){
        
        return exist;
    }
    
    //check monk's HP
    public int monkHP(){
        
        return monkHP;
    }
    
    //check monk's MP
    public int monkMP(){
        
        return monkMP;
    }
    
    //check monk's FP
    public int monkFP(){
        
        return monkFP;
    }
    
    //check robber's HP
    public int robberHP(){
        
        return robberHP;
    }
    
    //check robber's MP
    public int robberMP(){
        
        return robberMP;
    }
    
    //check robber's FP
    public int robberFP(){
        
        return robberFP;
    }
    
    //check bard's HP
    public int bardHP(){
        
        return bardHP;
    }
    
    //check bard's MP
    public int bardMP(){
        
        return bardMP;
    }
    
    //check bard's FP
    public int bardFP(){
        
        return bardFP;
    }
    
    //check number of day
    public int day(){
        
        return day;
    }
    
    //check numbers of foods
    public int food(){
        
        return food;
    }
    
    //check numbers of drink
    public int drink(){
        
        return drink;
    }
    
    //check numbers of gold
    public int gold(){
        
        return gold;
    }
    
    //check numbers of treasures
    public int treasure(){
        
        return treasure;
    }
    
    //check numbers of jewels
    public int jewel(){
        
        return jewel;
    }
    
    //check numbers of dragon's egg
    public int egg(){
        
        return egg;
    }
    
    //check level of house
    public int house(){
        
        return house;
    }
    
    //check which role is the player playing
    public int role(){
        
        return role;
    }
    
    //check the role's X coordinate
    public int role_X(){
        
        return role_X;
    }
    
    //check the role's Y coordinate
    public int role_Y(){
        
        return role_Y;
    }
}