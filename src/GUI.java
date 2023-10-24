/* NCKU, EE.108B
   Ryan, Chung
*/

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame{
    
    private Container contents;
    private Menu menu;
    private MenuItem save, load, delet, exit;
    private JButton[] MenuBtn = {new JButton(new ImageIcon(getClass().getResource("picture/Start.png"))),
                                 new JButton(new ImageIcon(getClass().getResource("picture/Load.png"))),
                                 new JButton(new ImageIcon(getClass().getResource("picture/Option.png"))),
                                 new JButton(new ImageIcon(getClass().getResource("picture/file/empty.png"))),
                                 new JButton(new ImageIcon(getClass().getResource("picture/file/empty.png"))),
                                 new JButton(new ImageIcon(getClass().getResource("picture/file/empty.png")))};
    private JButton[] SystemBtn = {new JButton(new ImageIcon(getClass().getResource("picture/Return.png"))),
                                   new JButton(new ImageIcon(getClass().getResource("picture/Skip.png"))),
                                   new JButton(new ImageIcon(getClass().getResource("picture/Control.png"))),
                                   new JButton(new ImageIcon(getClass().getResource("picture/Change.png"))),
                                   new JButton(new ImageIcon(getClass().getResource("picture/Talk.png")))};
    private JLabel[] background = {new JLabel(new ImageIcon(getClass().getResource("picture/StartBackground.png"))),
                                   new JLabel(new ImageIcon(getClass().getResource("picture/StoryBackground.png"))),
                                   new JLabel(new ImageIcon(getClass().getResource("picture/town.png")))};
    private JButton[] role = {new JButton(new ImageIcon(getClass().getResource("picture/monk/monkBtn.png"))),
                             new JButton(new ImageIcon(getClass().getResource("picture/robber/robberBtn.png"))),
                             new JButton(new ImageIcon(getClass().getResource("picture/bard/bardBtn.png"))),
                             new JButton(new ImageIcon(getClass().getResource("picture/monk/monkBtn.png"))),
                             new JButton(new ImageIcon(getClass().getResource("picture/robber/robberBtn.png"))),
                             new JButton(new ImageIcon(getClass().getResource("picture/bard/bardBtn.png")))};
    private ImageIcon[] houseImage = {new ImageIcon(getClass().getResource("picture/house/tent.png")),
                                      new ImageIcon(getClass().getResource("picture/house/logcabin.png")),
                                      new ImageIcon(getClass().getResource("picture/house/residence.png")),
                                      new ImageIcon(getClass().getResource("picture/house/villa.png")),
                                      new ImageIcon(getClass().getResource("picture/house/depo.png"))};
    private File[] file = {new File("save/data_1.txt"),
                           new File("save/data_2.txt"),
                           new File("save/data_3.txt"),
                           new File("save/data_origine.txt")};
    private ReadFiles[] Data = {new ReadFiles(file[0]),
                                new ReadFiles(file[1]),
                                new ReadFiles(file[2])};
    private Character monk = new Character(100,80,0);
    private Character robber = new Character(100,80,0);
    private Character bard = new Character(100,80,0);
    private int day=1, food=0, drink=0, gold=0, treasure=0, jewel=0, egg=0, house=1, current_role=0, role_X=485, role_Y=365,  MenuChoose = 0, current_Data =0;
    private String[] monk_status_S = new String[4];
    private String[] bard_status_S = new String[4];
    private String[] robber_status_S = new String[4];
    private String[] itemNum = new String[13];
    private JLabel[] item = new JLabel[13];
    private JLabel[] monk_status = new JLabel[4];
    private JLabel[] bard_status = new JLabel[4];
    private JLabel[] robber_status = new JLabel[4];
    private JLabel sRole, dir = new JLabel(new ImageIcon(getClass().getResource("picture/dir.png")));
    private boolean check = false, check_1 = true, check_2 = true, check_3 = true, check_4 = true, fileExist = true;
    private String cmd = null, houseType = "tent";
    private ImageIcon[][] Role = new ImageIcon[3][16];
    private ImageIcon current_house = houseImage[0];
    
    //constructor
    public GUI(){
        
        super("RPG Game");
        contents = getContentPane();
        contents.setBackground( Color.BLACK );
        
        //set menubar
        save = new MenuItem("Save");
        MenuShortcut ms1 = new MenuShortcut(KeyEvent.VK_S, false);
        save.setShortcut(ms1);
        save.addActionListener(new ActionHandler());
        load = new MenuItem("Load");
        MenuShortcut ms2 = new MenuShortcut(KeyEvent.VK_L, false);
        load.setShortcut(ms2);
        load.addActionListener(new ActionHandler());
        delet = new MenuItem("Delet");
        MenuShortcut ms3 = new MenuShortcut(KeyEvent.VK_D, false);
        delet.setShortcut(ms3);
        delet.addActionListener(new ActionHandler());
        exit = new MenuItem("Exit");
        MenuShortcut ms4 = new MenuShortcut(KeyEvent.VK_E, false);
        exit.setShortcut(ms4);
        exit.addActionListener(new ActionHandler());
        
        menu = new Menu("File");
        menu.add(save);
        menu.add(load);
        menu.add(delet);
        menu.addSeparator();
        menu.add(exit);
        
        //set all buttons
        role[0].setBounds(10,10,90,90);
        role[1].setBounds(10,185,90,90);
        role[2].setBounds(10,360,90,90);
        role[3].setBounds(10,10,90,90);
        role[4].setBounds(10,130,90,90);
        role[5].setBounds(10,250,90,90);
        
        MenuBtn[0].setBounds(394,180,166,60);
        MenuBtn[1].setBounds(394,280,166,60);
        MenuBtn[2].setBounds(394,380,166,60);
        
        SystemBtn[0].setBounds(610,460,40,40);
        SystemBtn[1].setBounds(800,500,78,29);
        SystemBtn[2].setBounds(5,540,30,30);
        SystemBtn[3].setBounds(40,540,30,30);
        SystemBtn[4].setBounds(75,540,30,30);
        
        for(int i=0; i<3; i++){
            MenuBtn[i].addActionListener(new ActionHandler());
        }
        for(int j=0; j<5; j++){
            SystemBtn[j].addActionListener(new ActionHandler());
        }
        for(int k=0; k<6; k++){
            role[k].addActionListener(new ActionHandler());
        }
        contents.addKeyListener( new KeyHandler() );
        contents.setFocusable(true);
        
        //set role's images
        for(int i=0; i<16; i++){
            //set the string components
            String Image = "picture/";
            String monkImage = "monk/";
            String bardImage = "bard/";
            String robberImage = "robber/";
            String PNG = ".png";
            String m = Image + monkImage + (i+1) + PNG;
            String b = Image + bardImage + (i+1) + PNG;
            String r = Image + robberImage + (i+1) + PNG;
            
            //add the ImageIcons
            Role[0][i] = new ImageIcon(getClass().getResource(m));
            Role[1][i] = new ImageIcon(getClass().getResource(r));
            Role[2][i] = new ImageIcon(getClass().getResource(b));
        }
        
        setSize( 960, 600 );
        setLocationRelativeTo(null);
        setVisible( true );
    }
    
    public int MenuChoose(){
        
        return MenuChoose;
    }
    
    public int MenuChoose(int num){
        
        MenuChoose = num;
        return MenuChoose;
    }
    
    public boolean LoadCheck(){
        
        return check_1;
    }
    
    public boolean check(){
        
        return check;
    }
    
    public boolean check(boolean b){
        
        check = b;
        return check;
    }
    
    public boolean fileExist(){
        
        return fileExist;
    }
    
    public void fileExist(boolean b){
        
        fileExist = b;
    }
    
    private void setHouse(int num){
        if(num<5){
            houseType = "tent";
            current_house = houseImage[0];
        }
        else if(num<10){
            houseType = "logcabin";
            current_house = houseImage[1];
        }
        else if(num<15){
            houseType = "residence";
            current_house = houseImage[2];
        }
        else if(num<20){
            houseType = "villa";
            current_house = houseImage[3];
        }
        else if(num<25){
            houseType = "depo";
            current_house = houseImage[4];
        }
    }
    
    public void Menu(){
        
        contents.removeAll();
        contents.setLayout(new BorderLayout());
        
        //reset menubar
        MenuBar menubar = new MenuBar();
        menubar.remove(menu);
        setMenuBar(menubar);
        
        //set background
        background[0].setLocation(0,0);
        
        contents.add( MenuBtn[0] );
        contents.add( MenuBtn[1] );
        contents.add( MenuBtn[2] );
        contents.add( background[0] );
        
    }
    
    public void Menu_Start(){
        
        //check if there is empty file to use
        if(check_1){
            if( Data[0].exist()==0 )    current_Data = 0;
            else if( Data[1].exist()==0 )   current_Data = 1;
            else if( Data[2].exist()==0 )   current_Data = 2;
            else{   JOptionPane.showMessageDialog
                (contents,"您的存檔已滿，請另外選擇檔案","注意",JOptionPane.WARNING_MESSAGE);
                fileExist = false;
                check_4 = false;
            }
        }
        if(Data[current_Data].exist()==0)
            check_1 = true;
        if( check_1 && check_4 ){
            
            //set background
            contents.removeAll();
            contents.setLayout(new BorderLayout());
            background[1].setLocation(0,0);
            
            contents.add( SystemBtn[1] );
            contents.add( background[1] );
        }
        check_4 = true;
    }
    
    public void Menu_Load(){
        
        contents.removeAll();
        contents.setLayout(new BorderLayout());
        
        //set background
        background[0].setLocation(0,0);
        
        //initialize the Data
        for(int i=0; i<3; i++){
            Data[i] = new ReadFiles(file[i]);
        }
        
        //first button
        if( Data[0].exist()==0 )
            MenuBtn[3] = new JButton(new ImageIcon(getClass().getResource("picture/file/empty.png")));
        else
            MenuBtn[3] = new JButton(new ImageIcon(getClass().getResource("picture/file/file_1.png")));
        MenuBtn[3].setBounds(394,180,166,60);
        MenuBtn[3].addActionListener(new ActionHandler());
        
        //second button
        if( Data[1].exist()==0 )
            MenuBtn[4] = new JButton(new ImageIcon(getClass().getResource("picture/file/empty.png")));
        else
            MenuBtn[4] = new JButton(new ImageIcon(getClass().getResource("picture/file/file_2.png")));
        MenuBtn[4].setBounds(394,280,166,60);
        MenuBtn[4].addActionListener(new ActionHandler());
        
        //third button
        if( Data[2].exist()==0 )
            MenuBtn[5] = new JButton(new ImageIcon(getClass().getResource("picture/file/empty.png")));
        else
            MenuBtn[5] = new JButton(new ImageIcon(getClass().getResource("picture/file/file_3.png")));
        MenuBtn[5].setBounds(394,380,166,60);
        MenuBtn[5].addActionListener(new ActionHandler());
        
        contents.add( MenuBtn[3] );
        contents.add( MenuBtn[4] );
        contents.add( MenuBtn[5] );
        contents.add( SystemBtn[0] );
        contents.add( background[0] );
    }
    
    public void Menu_Option(){
        
        contents.removeAll();
        contents.setLayout(new BorderLayout());
        
        dir.setBounds(306,156,348,288);
        background[0].setLocation(0,0);
        
        contents.add( dir);
        contents.add( SystemBtn[0] );
        contents.add( background[0] );
    }
    
    public void StartGame(){
        
        contents.removeAll();
        contents.setLayout(null);
        
        //add menubar
        MenuBar menubar = new MenuBar();
        menubar.add(menu);
        setMenuBar(menubar);
        
        //set background
        background[2].setBounds(110,0,850,600);
        
        //initialize the variables
        day=1; food=0; drink=0; gold=0; treasure=0; jewel=0; egg=0; house=1; current_role=0; role_X=485; role_Y=365;
        monk.sHP(100); monk.sMP(80); monk.rFP();
        bard.sHP(100); bard.sMP(80); bard.rFP();
        robber.sHP(100); robber.sMP(80); robber.rFP();
        
        monk_status_S[0] = new String("Monk");
        monk_status_S[1] = new String("HP: "+monk.HP());
        monk_status_S[2] = new String("MP: "+monk.MP());
        monk_status_S[3] = new String("FP: "+monk.FP());
        bard_status_S[0] = new String("Bard");
        bard_status_S[1] = new String("HP: "+bard.HP());
        bard_status_S[2] = new String("MP: "+bard.MP());
        bard_status_S[3] = new String("FP: "+bard.FP());
        robber_status_S[0] = new String("Robber");
        robber_status_S[1] = new String("HP: "+robber.HP());
        robber_status_S[2] = new String("MP: "+robber.MP());
        robber_status_S[3] = new String("FP: "+robber.FP());
        itemNum[0] = new String("Day: "+day);
        itemNum[1] = new String("Food: "+food);
        itemNum[2] = new String("Drink: "+drink);
        itemNum[3] = new String("Gold: "+gold);
        itemNum[4] = new String("Treasure: "+treasure);
        itemNum[5] = new String("Jewel: "+jewel);
        itemNum[6] = new String("Dragon's egg: "+egg);
        itemNum[7] = new String("House Level: "+house);
        itemNum[8] = new String("House: "+houseType);
        itemNum[9] = new String("==========");
        itemNum[10] = new String("Monk");
        itemNum[11] = new String("Robber");
        itemNum[12] = new String("Bard");
        
        for(int i=0; i<4; i++){
            monk_status[i] = new JLabel(monk_status_S[i]);
            bard_status[i] = new JLabel(bard_status_S[i]);
            robber_status[i] = new JLabel(robber_status_S[i]);
            monk_status[i].setForeground( Color.WHITE );
            bard_status[i].setForeground( Color.WHITE );
            robber_status[i].setForeground( Color.WHITE );
        }
        for(int j=0; j<13; j++){
            item[j] = new JLabel(itemNum[j]);
            item[j].setForeground( Color.WHITE );
        }item[9].setForeground( Color.ORANGE );
        
        //check to load the data
        if(!check_1){
            Load();
        }check_1 = true;
        
        monk_status[0].setBounds(10,110,100,10);
        monk_status[1].setBounds(10,130,100,10);
        monk_status[2].setBounds(10,145,100,10);
        monk_status[3].setBounds(10,160,100,10);
        
        bard_status[0].setBounds(10,460,100,10);
        bard_status[1].setBounds(10,480,100,10);
        bard_status[2].setBounds(10,495,100,10);
        bard_status[3].setBounds(10,510,100,10);
        
        robber_status[0].setBounds(10,285,100,10);
        robber_status[1].setBounds(10,305,100,10);
        robber_status[2].setBounds(10,320,100,10);
        robber_status[3].setBounds(10,335,100,10);
        
        item[0].setBounds(10,370,100,15);
        item[1].setBounds(10,440,100,10);
        item[2].setBounds(10,455,100,10);
        item[3].setBounds(10,470,100,10);
        item[4].setBounds(10,485,100,10);
        item[5].setBounds(10,500,100,15);
        item[6].setBounds(10,515,100,15);
        item[7].setBounds(10,400,100,10);
        item[8].setBounds(10,385,100,15);
        item[9].setBounds(5,420,100,10);
        item[10].setBounds(10,110,100,10);
        item[11].setBounds(10,230,100,10);
        item[12].setBounds(10,350,100,10);
        
        sRole = new JLabel( Role[current_role][0] );
        sRole.setBounds(role_X,role_Y,64,96);
        
        //add all of them
        for(int k=0; k<3; k++){
            contents.add( role[k] );
            contents.add( SystemBtn[k+2] );
        }
        for(int l=0; l<4; l++){
            contents.add( monk_status[l] );
            contents.add( bard_status[l] );
            contents.add( robber_status[l] );
        }
        contents.add( sRole );
        contents.add( background[2] );
        
    }
    
    //let user enter instruction
    public void Instructions(){
        
        check_3 = true;
        while(check_3){
            Sleep(100);
            if(!(cmd==null)){
            try{
            if(cmd.substring(0,4).equals("rest")){
                if(cmd.length()==4){
                    day += 1;
                    food -= 1;
                    monk.rFP();
                    robber.rFP();
                }
                else if(cmd.length()==6||cmd.length()==7){
                    day += Integer.parseInt(cmd.substring(5));
                    food -= Integer.parseInt(cmd.substring(5));
                    monk.rFP();
                    robber.rFP();
                }else{
                    JOptionPane.showMessageDialog(null,
                                                  "請重新輸入，或輸入help來確認目前可用的指令",
                                                  "Command ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                if(cmd.equals("status")){
                    String st = new String("Day: " + day
                                           +"\n\nMonk\nHP: " + monk.HP()
                                           +"\nMP: " + monk.MP()
                                           +"\nFP: " + monk.FP()
                                           +"\n\nRobber\nHP: " + robber.HP()
                                           +"\nMP: " + robber.MP()
                                           +"\nFP: " + robber.FP()
                                           +"\n\nBard\nHP: " + bard.HP()
                                           +"\nMP: " + bard.MP()
                                           +"\nFP: " + bard.FP()
                                           +"\n\nHouse: " + houseType
                                           +"\nHouse Level: " + house
                                           +"\n\nItem\nFood: " + food
                                           +"\nDrink: " + drink
                                           +"\nGold: " + gold
                                           +"\nTreasure: " + treasure
                                           +"\nJewel: " + jewel
                                           +"\nDragon's egg: " + egg);
                    JOptionPane.showMessageDialog(null,st,"Status",
                                                  JOptionPane.PLAIN_MESSAGE,
                                                  new ImageIcon(getClass().getResource("picture/status.png")));
                }
                else if(cmd.equals("help")){
                    String st = new String("(1)use [monk/robber/bard]:\n\tchange the role on the map."
                                           +"\n(2)item [monk/robber/bard] food:\n\tthe role's HP+60, Food-1"
                                           +"\n(3)item [monk/robber/bard] drink:\n\tthe role's MP+60, Drink-1"
                                           +"\n(4)skill [monk/robber/bard] buy_food:\n\tthe role's FP+10, Food+2, Gold-1"
                                           +"\n(5)skill [monk/robber/bard] buy_drink:\n\tthe role's FP+10, Drink+1, Gold-1"
                                           +"\n(6)skill [monk/robber/bard] improve:\n\tthe role's FP+25, House level +1"
                                           +"\n(7)skill robber loot:\n\trobber's HP-30, MP-20, FP+40, Gold+2"
                                           +"\n(8)skill robber adventure:\n\trobber's HP-60, MP-30, FP+40, Treasure+1 (Only When The Day is 2,4,6,8,10)"
                                           +"\n(9)skill monk heal [monk/robber/bard]:\n\tthe role's HP+65, monk's MP-30, FP+30"
                                           +"\n(10)skill monk adventure:\n\tmonk's HP-60, MP-60, FP+60, Jewel+1 (Only When The Day is 3,6,9)"
                                           +"\n(11)skill bard perform:\n\tbard's MP-30, FP+30, other's FP-25"
                                           +"\n(12)skill bard adventure:\n\tbard's HP-90, MP-80, FP+80, Dragon's egg +1 (Only When The Day is 5,10)"
                                           +"\n(13)rest:\n\tDay+1, Food-1, all role's FP=0"
                                           +"\n(14)rest [day]:\n\tDay+(day), Food-(day), all role's FP=0"
                                           +"\n(15)status:\n\tShow the Day, the role's HP, MP, FP and all the items."
                                           +"\n(16)help:\n\tList all the instructions."
                                           +"\n(17)save:\n\tSave the game."
                                           +"\n(18)load:\n\tLoad the record of the game."
                                           +"\n(19)delet:\n\tDelet the record of the game."
                                           +"\n(20)exit:\n\tStop the game, and list the titles you earned.");
                    JOptionPane.showMessageDialog(null,st,"Instruction List",
                                                  JOptionPane.PLAIN_MESSAGE);
                }
                else if(cmd.equals("save")){
                    Save();
                }
                else if(cmd.equals("load")){
                    Load();
                }
                else if(cmd.equals("delet")){
                    Delet();
                }
                else if(cmd.equals("use monk")){
                    current_role = 0;
                    sRole.setIcon(Role[0][0]);
                }
                else if(cmd.equals("use bard")){
                    current_role = 2;
                    sRole.setIcon(Role[2][0]);
                }
                else if(cmd.equals("use robber")){
                    current_role = 1;
                    sRole.setIcon(Role[1][0]);
                }
                else if(cmd.equals("skill monk improve")){
                    monk.iFP(25);
                    house += 1;
                    setHouse(house);
                }
                else if(cmd.equals("skill bard improve")){
                    bard.iFP(25);
                    house += 1;
                    setHouse(house);
                }
                else if(cmd.equals("skill robber improve")){
                    robber.iFP(25);
                    house += 1;
                    setHouse(house);
                }
                else if(cmd.equals("item monk food")){
                    if(food>=1){
                        monk.iHP(60);
                        food -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的食物吃光了，請去商店購買更多食物",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("item bard food")){
                    if(food>=1){
                        bard.iHP(60);
                        food -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的食物吃光了，請去商店購買更多食物",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("item robber food")){
                    if(food>=1){
                        robber.iHP(60);
                        food -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的食物吃光了，請去商店購買更多食物",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("item monk drink")){
                    if(drink>=1){
                        monk.iMP(60);
                        drink -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的飲料喝完了，請去商店購買更多飲料",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("item bard drink")){
                    if(drink>=1){
                        bard.iMP(60);
                        drink -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的飲料喝完了，請去商店購買更多飲料",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("item robber drink")){
                    if(drink>=1){
                        robber.iMP(60);
                        drink -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的飲料喝完了，請去商店購買更多飲料",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill monk buy_food")){
                    if(gold>=1){
                        monk.iFP(10);
                        food += 2;
                        gold -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的金幣用罄了，請劫財以取得更多$",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill bard buy_food")){
                    if(gold>=1){
                        bard.iFP(10);
                        food += 2;
                        gold -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的金幣用罄了，請劫財以取得更多$",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill robber buy_food")){
                    if(gold>=1){
                        robber.iFP(10);
                        food += 2;
                        gold -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的金幣用罄了，請劫財以取得更多$",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill monk buy_drink")){
                    if(gold>=1){
                        monk.iFP(10);
                        drink += 1;
                        gold -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的金幣用罄了，請劫財以取得更多$",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill bard buy_drink")){
                    if(gold>=1){
                        bard.iFP(10);
                        drink += 1;
                        gold -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的金幣用罄了，請劫財以取得更多$",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill robber buy_drink")){
                    if(gold>=1){
                        robber.iFP(10);
                        drink += 1;
                        gold -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "你的金幣用罄了，請劫財以取得更多$",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill robber loot")){
                    if(robber.MP()>=20){
                        robber.dHP(30);
                        robber.dMP(20);
                        robber.iFP(40);
                        gold += 2;
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "盜賊沒有足夠的MP",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill bard perform")){
                    if(bard.MP>=30){
                        monk.dFP(25);
                        robber.dFP(25);
                        bard.dMP(30);
                        bard.iFP(30);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "吟遊詩人沒有足夠的MP",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill monk heal monk")){
                    if(monk.MP>=30){
                        monk.iHP(65);
                        monk.dMP(30);
                        monk.iFP(30);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "僧侶沒有足夠的MP",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill monk heal bard")){
                    if(monk.MP>=30){
                        bard.iHP(65);
                        monk.dMP(30);
                        monk.iFP(30);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "僧侶沒有足夠的MP",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill monk heal robber")){
                    if(monk.MP>=30){
                        robber.iHP(65);
                        monk.dMP(30);
                        monk.iFP(30);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "僧侶沒有足夠的MP",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill robber adventure")){
                    if(day==2||day==4||day==6||day==8||day==10){
                        if(robber.MP>=30){
                            robber.dHP(60);
                            robber.dMP(30);
                            robber.iFP(40);
                            treasure += 1;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                                          "盜賊沒有足夠的MP",
                                                          "Command ERROR",
                                                          JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "盜賊今天不能冒險XD\n(Only on Day 2, 4, 6, 8, 10)",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill monk adventure")){
                    if(day==3||day==6||day==9){
                        if(monk.MP>=60){
                            monk.dHP(60);
                            monk.dMP(60);
                            monk.iFP(60);
                            jewel += 1;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                                          "僧侶沒有足夠的MP",
                                                          "Command ERROR",
                                                          JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "僧侶今天不能冒險XD\n(Only on Day 3, 6, 9)",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("skill bard adventure")){
                    if(day==5||day==10){
                        if(monk.MP>=80){
                            monk.dHP(90);
                            monk.dMP(80);
                            monk.iFP(80);
                            egg += 1;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                                          "吟遊詩人沒有足夠的MP",
                                                          "Command ERROR",
                                                          JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                                      "吟遊詩人今天不能冒險XD\n(Only on Day 5, 10)",
                                                      "Command ERROR",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(cmd.equals("exit")){
                    int result=JOptionPane.showConfirmDialog(null,
                                                             "遊戲將中途結束，列出稱號",
                                                             "確定要離開遊戲嗎？",
                                                             JOptionPane.YES_NO_OPTION,
                                                             JOptionPane.WARNING_MESSAGE);
                    if (result==JOptionPane.YES_OPTION) check_3 = false;
                }
                else{
                    JOptionPane.showMessageDialog(null,
                                                  "請重新輸入，或輸入help來確認目前可用的指令",
                                                  "Command ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,
                                              "請重新輸入，或輸入help來確認目前可用的指令",
                                              "Command ERROR",JOptionPane.ERROR_MESSAGE);
            }

            //set new status and numbers
            monk_status_S[1] = new String("HP: "+monk.HP());
            monk_status_S[2] = new String("MP: "+monk.MP());
            monk_status_S[3] = new String("FP: "+monk.FP());
            bard_status_S[1] = new String("HP: "+bard.HP());
            bard_status_S[2] = new String("MP: "+bard.MP());
            bard_status_S[3] = new String("FP: "+bard.FP());
            robber_status_S[1] = new String("HP: "+robber.HP());
            robber_status_S[2] = new String("MP: "+robber.MP());
            robber_status_S[3] = new String("FP: "+robber.FP());
            itemNum[0] = new String("Day: "+day);
            itemNum[1] = new String("Food: "+food);
            itemNum[2] = new String("Drink: "+drink);
            itemNum[3] = new String("Gold: "+gold);
            itemNum[4] = new String("Treasure: "+treasure);
            itemNum[5] = new String("Jewel: "+jewel);
            itemNum[6] = new String("Dragon's egg: "+egg);
            itemNum[7] = new String("House Level: "+house);
            itemNum[8] = new String("House: "+houseType);
            
            for(int i=0; i<4; i++){
                monk_status[i].setText(monk_status_S[i]);
                bard_status[i].setText(bard_status_S[i]);
                robber_status[i].setText(robber_status_S[i]);
            }
            for(int j=0; j<9; j++){
                item[j].setText(itemNum[j]);
            }
            
            if(monk.HP()<=0){
                monk.sHP(50);
                day += 3;
                food -= 3;
                monk.rFP();
                bard.rFP();
                robber.rFP();
            }
            else if(bard.HP()<=0){
                bard.sHP(50);
                day += 3;
                food -= 3;
                monk.rFP();
                bard.rFP();
                robber.rFP();
            }
            else if(robber.HP()<=0){
                robber.sHP(50);
                day += 3;
                food -= 3;
                monk.rFP();
                bard.rFP();
                robber.rFP();
            }
            if(day>10||food<0)  check_3 = false;
            }
            cmd = null;
        }
        String title = new String();
        if(treasure>=6)
            title += "Your Title: 寶物獵人\n";
        if(jewel>=3)
            title += "Your Title: 神官\n";
        if(egg>=2)
            title += "Your Title: 特級馴獸師\n";
        if(gold>=10)
            title += "Your Title: 怪盜義賊\n";
        if(house>=20)
            title += "Your Title: 匠工之王\n";
        if((food + drink)>=20)
            title += "Your Title: 倉儲者\n";
        if(treasure==0 && jewel==0 && egg==0)
            title += "Your Title: 平凡的一生\n";
        JOptionPane.showMessageDialog(null,title,"Game Over",
                                      JOptionPane.PLAIN_MESSAGE,
                                      new ImageIcon(getClass().getResource("picture/cup.png")));
    }
    
    //save the game
    private void Save(){
        try{
            PrintWriter pw =
            new PrintWriter( new FileOutputStream(file[current_Data],false) );
            pw.print( 1 );
            pw.print( ',' );
            pw.print( monk.HP() );
            pw.print( ',' );
            pw.print( monk.MP() );
            pw.print( ',' );
            pw.print( monk.FP() );
            pw.print( ',' );
            pw.print( robber.HP() );
            pw.print( ',' );
            pw.print( robber.MP() );
            pw.print( ',' );
            pw.print( robber.FP() );
            pw.print( ',' );
            pw.print( bard.HP() );
            pw.print( ',' );
            pw.print( bard.MP() );
            pw.print( ',' );
            pw.print( bard.FP() );
            pw.print( ',' );
            pw.print( day );
            pw.print( ',' );
            pw.print( food );
            pw.print( ',' );
            pw.print( drink );
            pw.print( ',' );
            pw.print( gold );
            pw.print( ',' );
            pw.print( treasure );
            pw.print( ',' );
            pw.print( jewel );
            pw.print( ',' );
            pw.print( egg );
            pw.print( ',' );
            pw.print( house );
            pw.print( ',' );
            pw.print( current_role );
            pw.print( ',' );
            pw.print( role_X );
            pw.print( ',' );
            pw.print( role_Y );
            pw.flush();
            pw.close();
        }
        catch(FileNotFoundException f){
            System.out.println("\nFile Not Found/Can’t be Created");
        }
    }
    
    //load the game
    private void Load(){
        //set datas from the file
        Data[current_Data] = new ReadFiles(file[current_Data]);
        monk.sHP(Data[current_Data].monkHP());
        monk.sMP(Data[current_Data].monkMP());
        monk.sFP(Data[current_Data].monkFP());
        bard.sHP(Data[current_Data].bardHP());
        bard.sMP(Data[current_Data].bardMP());
        bard.sFP(Data[current_Data].bardFP());
        robber.sHP(Data[current_Data].robberHP());
        robber.sMP(Data[current_Data].robberMP());
        robber.sFP(Data[current_Data].robberFP());
        day = Data[current_Data].day();
        food = Data[current_Data].food();
        drink = Data[current_Data].drink();
        gold = Data[current_Data].gold();
        treasure = Data[current_Data].treasure();
        jewel = Data[current_Data].jewel();
        egg = Data[current_Data].egg();
        house = Data[current_Data].house();
        current_role = Data[current_Data].role();
        role_X = Data[current_Data].role_X();
        role_Y = Data[current_Data].role_Y();
        setHouse(house);
        
        //set new status and numbers
        monk_status_S[1] = new String("HP: "+monk.HP());
        monk_status_S[2] = new String("MP: "+monk.MP());
        monk_status_S[3] = new String("FP: "+monk.FP());
        bard_status_S[1] = new String("HP: "+bard.HP());
        bard_status_S[2] = new String("MP: "+bard.MP());
        bard_status_S[3] = new String("FP: "+bard.FP());
        robber_status_S[1] = new String("HP: "+robber.HP());
        robber_status_S[2] = new String("MP: "+robber.MP());
        robber_status_S[3] = new String("FP: "+robber.FP());
        itemNum[0] = new String("Day: "+day);
        itemNum[1] = new String("Food: "+food);
        itemNum[2] = new String("Drink: "+drink);
        itemNum[3] = new String("Gold: "+gold);
        itemNum[4] = new String("Treasure: "+treasure);
        itemNum[5] = new String("Jewel: "+jewel);
        itemNum[6] = new String("Dragon's egg: "+egg);
        itemNum[7] = new String("House Level: "+house);
        itemNum[8] = new String("House: "+houseType);
        
        for(int i=0; i<4; i++){
            monk_status[i].setText(monk_status_S[i]);
            bard_status[i].setText(bard_status_S[i]);
            robber_status[i].setText(robber_status_S[i]);
        }
        for(int j=0; j<9; j++){
            item[j].setText(itemNum[j]);
        }
        
    }
    
    //delet the game
    private void Delet(){
        try{
            Scanner scn = new Scanner( new FileInputStream(file[3]) );
            String str = scn.nextLine();
            try{
                PrintWriter pw =
                new PrintWriter( new FileOutputStream(file[current_Data],false) );
                pw.print(str);
                pw.flush();
                pw.close();
            }
            catch(FileNotFoundException f){
                System.out.println("\nFile Not Found/Can’t be Created");
            }
            scn.close();
        }
        catch(IOException i){
            System.out.println("\nIO Exception Happened");
        }
    }
    
    //set sleep method
    private static void Sleep(int num){
        try {
            Thread.sleep(num);
            
        }catch (InterruptedException e) {
        }
    }
    
    //set walk method for the role
    private void Walk( int direction ){
        
        //set new thread for delay
        new Thread() {
        public void run() {
        try{
        switch(direction){
            //down
            case 0:
                for(int i=1; i<=4; i++){
                    sRole.setIcon( Role[current_role][i-1] );
                    Sleep(100);
                    role_Y += 8;
                    if(role_Y>504)
                        role_Y = 504;
                    sRole.setLocation( role_X, role_Y );
                    Sleep(100);
                }
                sRole.setIcon( Role[current_role][0] );
                break;
            
            //left
            case 1:
                for(int i=5; i<=8; i++){
                    sRole.setIcon( Role[current_role][i-1] );
                    Sleep(100);
                    role_X -= 8;
                    if(role_X<92)
                        role_X = 92;
                    sRole.setLocation( role_X, role_Y );
                    Sleep(100);
                }
                sRole.setIcon( Role[current_role][4] );
                break;
                
            //right
            case 2:
                for(int i=9; i<=12; i++){
                    sRole.setIcon( Role[current_role][i-1] );
                    Sleep(100);
                    role_X += 8;
                    if(role_X>918)
                        role_X = 918;
                    sRole.setLocation( role_X, role_Y );
                    Sleep(100);
                }
                sRole.setIcon( Role[current_role][8] );
                break;
             
            //up
            case 3:
                for(int i=13; i<=16; i++){
                    sRole.setIcon( Role[current_role][i-1] );
                    Sleep(100);
                    role_Y -= 8;
                    if(role_Y<-20)
                        role_Y = -20;
                    sRole.setLocation( role_X, role_Y );
                    Sleep(100);
                }
                sRole.setIcon( Role[current_role][12] );
                break;
                
            default:
                System.out.println("Wrong direction. Only Between 0 to 3.");
                break;
        }
        }catch(Exception e){
            System.out.println("Unknow exception happened.");
        }
        }
        }.start();
    }
    
    //set KeyListener for contents
    class KeyHandler implements KeyListener{
        public void keyPressed(KeyEvent e){
            
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_UP:
                    Walk(3);
                    break;
                    
                case KeyEvent.VK_LEFT:
                    Walk(1);
                    break;
                    
                case KeyEvent.VK_DOWN:
                    Walk(0);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    Walk(2);
                    break;
                    
                case KeyEvent.VK_W:
                    Walk(3);
                    break;
                    
                case KeyEvent.VK_A:
                    Walk(1);
                    break;
                    
                case KeyEvent.VK_S:
                    Walk(0);
                    break;
                    
                case KeyEvent.VK_D:
                    Walk(2);
                    break;
                    
                default:
                    break;
            }
        }
        //override other two methods
        public void keyReleased(KeyEvent event) {
        }
        public void keyTyped(KeyEvent t) {
        }
    }
    
    //set ActionListener for buttons
    class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            if ( e.getSource() == MenuBtn[0] )
                MenuChoose = 1;
            else if ( e.getSource() == MenuBtn[1] )
                MenuChoose = 2;
            else if ( e.getSource() == MenuBtn[2] )
                MenuChoose = 3;
            else if ( e.getSource() == MenuBtn[3] ){
                check_1 = false;
                current_Data = 0;
                MenuChoose = 1;
            }
            else if ( e.getSource() == MenuBtn[4] ){
                check_1 = false;
                current_Data = 1;
                MenuChoose = 1;
            }
            else if ( e.getSource() == MenuBtn[5] ){
                check_1 = false;
                current_Data = 2;
                MenuChoose = 1;
            }
            else if ( e.getSource() == SystemBtn[0] )
                MenuChoose = 0;
            else if ( e.getSource() == SystemBtn[1] )
                check = true;
            else if ( e.getSource() == SystemBtn[2] )
                cmd = JOptionPane.showInputDialog(null,
                                                  "請輸入指令：",
                                                  "Command",
                                                  JOptionPane.QUESTION_MESSAGE);
            else if (e.getSource() == SystemBtn[3] ){
                if(check_2){
                    for(int i=0; i<3; i++){
                        contents.remove(role[i]);
                    }
                    for(int j=0; j<4; j++){
                        contents.remove(monk_status[j]);
                        contents.remove(bard_status[j]);
                        contents.remove(robber_status[j]);
                    }
                    for(int k=3; k<6; k++){
                        contents.add(role[k]);
                    }
                    for(int l=0; l<13; l++){
                        contents.add(item[l]);
                    }
                    check_2 = false;
                }
                else{
                    for(int i=0; i<3; i++){
                        contents.add(role[i]);
                    }
                    for(int j=0; j<4; j++){
                        contents.add(monk_status[j]);
                        contents.add(bard_status[j]);
                        contents.add(robber_status[j]);
                    }
                    for(int k=3; k<6; k++){
                        contents.remove(role[k]);
                    }
                    for(int l=0; l<13; l++){
                        contents.remove(item[l]);
                    }
                    check_2 = true;
                }
                repaint();
            }
            else if (e.getSource() == SystemBtn[4] ){
                if( role_X>=340 && role_X<=440 && role_Y>=270 && role_Y<=370 ){
                    String[] Amy_opt = {"小屋","狀態","指令"};
                    int opt = JOptionPane.showOptionDialog(null,"在這裡你可以查看狀態、指令\n或經營你的冒險者小屋",
                                                           "遊戲導覽員 Amy",
                                                           JOptionPane.YES_NO_CANCEL_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/Amy.png")),
                                                           Amy_opt,null);
                    if (opt==JOptionPane.YES_OPTION){
                        int result=JOptionPane.showConfirmDialog(null,
                                                                 "想要升級小屋嗎？",
                                                                 "冒險者小屋 House",
                                                                 JOptionPane.YES_NO_OPTION,
                                                                 JOptionPane.PLAIN_MESSAGE,
                                                                 current_house);
                        if (result==JOptionPane.YES_OPTION){
                            String[] role_opt = {"吟遊詩人","盜賊","僧侶"};
                            int opt1 = JOptionPane.showOptionDialog(null,"選擇要付出勞力的人(FP-10)",
                                                                   "升級小屋 Improve",
                                                                   JOptionPane.YES_NO_CANCEL_OPTION,
                                                                   JOptionPane.PLAIN_MESSAGE,
                                                                   current_house,role_opt,null);
                            if (opt1==JOptionPane.YES_OPTION){
                                cmd = "skill bard improve";
                            }
                            if (opt1==JOptionPane.NO_OPTION){
                                cmd = "skill robber improve";
                            }
                            if (opt1==JOptionPane.CANCEL_OPTION){
                                cmd = "skill monk improve";
                            }
                        }
                            
                    }
                    if (opt==JOptionPane.NO_OPTION){
                        cmd = "status";
                    }
                    if (opt==JOptionPane.CANCEL_OPTION){
                        cmd = "help";
                    }
                }
                if( role_X>=530 && role_X<=630 && role_Y>=185 && role_Y<=285 ){
                    String[] Shop_opt = {"食物","藥水","搶劫他"};
                    int opt = JOptionPane.showOptionDialog(null,
                                                           "請問你要買什麼？\n1金幣、10點FP：\n2份食物或1瓶藥水",
                                                           "商店 Shop",
                                                           JOptionPane.YES_NO_CANCEL_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/shop.png")),
                                                           Shop_opt,null);
                    if (opt==JOptionPane.YES_OPTION){
                        String[] role_opt = {"吟遊詩人","盜賊","僧侶"};
                        int opt1 = JOptionPane.showOptionDialog(null,"選擇要買食物的人(FP-10)",
                                                               "買食物 Buy_food",
                                                               JOptionPane.YES_NO_CANCEL_OPTION,
                                                               JOptionPane.PLAIN_MESSAGE,
                                                               new ImageIcon(getClass().getResource("picture/shop.png")),
                                                               role_opt,null);
                        if (opt1==JOptionPane.YES_OPTION){
                            cmd = "skill bard buy_food";
                        }
                        if (opt1==JOptionPane.NO_OPTION){
                            cmd = "skill robber buy_food";
                        }
                        if (opt1==JOptionPane.CANCEL_OPTION){
                            cmd = "skill monk buy_food";
                        }
                    }
                    if (opt==JOptionPane.NO_OPTION){
                        String[] role_opt = {"吟遊詩人","盜賊","僧侶"};
                        int opt2 = JOptionPane.showOptionDialog(null,"選擇要買藥水的人(FP-10)",
                                                               "買藥水 Buy_drink",
                                                               JOptionPane.YES_NO_CANCEL_OPTION,
                                                               JOptionPane.PLAIN_MESSAGE,
                                                               new ImageIcon(getClass().getResource("picture/shop.png")),
                                                               role_opt,null);
                        if (opt2==JOptionPane.YES_OPTION){
                            cmd = "skill bard buy_drink";
                        }
                        if (opt2==JOptionPane.NO_OPTION){
                            cmd = "skill robber buy_drink";
                        }
                        if (opt2==JOptionPane.CANCEL_OPTION){
                            cmd = "skill monk buy_drink";
                        }
                    }
                    if (opt==JOptionPane.CANCEL_OPTION){
                        cmd = "skill robber loot";
                    }
                }
            }
            else if ( e.getSource() == role[0] ){
                String[] monk_opt = {"冒險","治療","切換角色"};
                int opt = JOptionPane.showOptionDialog(null,"請選擇僧侶的行動",
                                                       "僧侶 Monk",
                                                       JOptionPane.YES_NO_CANCEL_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon(getClass().getResource("picture/monk/monk.png")),
                                                       monk_opt,null);
                if (opt==JOptionPane.YES_OPTION){
                    cmd = "skill monk adventure";
                }
                if (opt==JOptionPane.NO_OPTION){
                    String[] heal_opt = {"自己","盜賊","吟遊詩人"};
                    String heal_one =
                    (String)JOptionPane.showInputDialog(null,"請選擇要治療的人","治療 Heal",
                                                        JOptionPane.PLAIN_MESSAGE,
                                                        new ImageIcon(getClass().getResource("picture/monk/monk.png")),
                                                        heal_opt,"自己");
                    try{
                        if( heal_one.equals("自己") )
                            cmd = "skill monk heal monk";
                        else if( heal_one.equals("盜賊") )
                            cmd = "skill monk heal robber";
                        else if( heal_one.equals("吟遊詩人") )
                            cmd = "skill monk heal bard";
                    }
                    catch(Exception r){}
                }
                if (opt==JOptionPane.CANCEL_OPTION){
                    cmd = "use monk";
                }
            }
            else if ( e.getSource() == role[1] ){
                String[] robber_opt = {"冒險","洗劫","切換角色"};
                int opt = JOptionPane.showOptionDialog(null,"請選擇盜賊的行動",
                                                       "盜賊 Robber",
                                                       JOptionPane.YES_NO_CANCEL_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon(getClass().getResource("picture/robber/robber.png")),
                                                       robber_opt,null);
                if (opt==JOptionPane.YES_OPTION){
                    cmd = "skill robber adventure";
                }
                if (opt==JOptionPane.NO_OPTION){
                    cmd = "skill robber loot";
                }
                if (opt==JOptionPane.CANCEL_OPTION){
                    cmd = "use robber";
                }
            }
            else if ( e.getSource() == role[2] ){
                String[] bard_opt = {"冒險","演奏","切換角色"};
                int opt = JOptionPane.showOptionDialog(null,"請選擇吟遊詩人的行動",
                                                       "吟遊詩人 Bard",
                                                       JOptionPane.YES_NO_CANCEL_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon(getClass().getResource("picture/bard/bard.png")),
                                                       bard_opt,null);
                if (opt==JOptionPane.YES_OPTION){
                    cmd = "skill bard adventure";
                }
                if (opt==JOptionPane.NO_OPTION){
                    cmd = "skill bard perform";
                }
                if (opt==JOptionPane.CANCEL_OPTION){
                    cmd = "use bard";
                }
            }
            else if ( e.getSource() == role[3] ){
                String[] monk_opt = {"買東西","吃東西","經營小屋"};
                int opt = JOptionPane.showOptionDialog(null,"請選擇僧侶的行動",
                                                       "僧侶 Monk",
                                                       JOptionPane.YES_NO_CANCEL_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon(getClass().getResource("picture/monk/monk.png")),
                                                       monk_opt,null);
                if (opt==JOptionPane.YES_OPTION){
                    String[] buyItem = {"食物","藥水"};
                    String ask = "請問你要買什麼？\n1金幣、10點FP：\n2份食物或1瓶藥水";
                    int itm = JOptionPane.showOptionDialog(null,ask,"商店 Shop",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/potion.png")),
                                                           buyItem,null);
                    if(itm==JOptionPane.YES_OPTION){
                        cmd = "skill monk buy_food";
                    }
                    if(itm==JOptionPane.NO_OPTION){
                        cmd = "skill monk buy_drink";
                    }
                }
                if (opt==JOptionPane.NO_OPTION){
                    String[] buyItem = {"食物","藥水"};
                    String ask = "現在想要吃什麼？\n消耗1份物品：\n恢復60點MP或60點HP";
                    int itm = JOptionPane.showOptionDialog(null,ask,"背包 Bag",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/potion.png")),
                                                           buyItem,null);
                    if(itm==JOptionPane.YES_OPTION){
                        cmd = "item monk food";
                    }
                    if(itm==JOptionPane.NO_OPTION){
                        cmd = "item monk drink";
                    }
                }
                if (opt==JOptionPane.CANCEL_OPTION){
                    cmd = "skill monk improve";
                }
            }
            else if ( e.getSource() == role[4] ){
                String[] robber_opt = {"買東西","吃東西","經營小屋"};
                int opt = JOptionPane.showOptionDialog(null,"請選擇盜賊的行動",
                                                       "盜賊 Robber",
                                                       JOptionPane.YES_NO_CANCEL_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon(getClass().getResource("picture/robber/robber.png")),
                                                       robber_opt,null);
                if (opt==JOptionPane.YES_OPTION){
                    String[] buyItem = {"食物","藥水"};
                    String ask = "請問你要買什麼？\n1金幣、10點FP：\n2份食物或1瓶藥水";
                    int itm = JOptionPane.showOptionDialog(null,ask,"商店 Shop",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/potion.png")),
                                                           buyItem,null);
                    if(itm==JOptionPane.YES_OPTION){
                        cmd = "skill robber buy_food";
                    }
                    if(itm==JOptionPane.NO_OPTION){
                        cmd = "skill robber buy_drink";
                    }
                }
                if (opt==JOptionPane.NO_OPTION){
                    String[] buyItem = {"食物","藥水",};
                    String ask = "現在想要吃什麼？\n消耗1份物品：\n恢復60點MP或60點HP";
                    int itm = JOptionPane.showOptionDialog(null,ask,"背包 Bag",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/potion.png")),
                                                           buyItem,null);
                    if(itm==JOptionPane.YES_OPTION){
                        cmd = "item robber food";
                    }
                    if(itm==JOptionPane.NO_OPTION){
                        cmd = "item robber drink";
                    }
                }
                if (opt==JOptionPane.CANCEL_OPTION){
                    cmd = "skill robber improve";
                }
            }
            else if ( e.getSource() == role[5] ){
                String[] bard_opt = {"買東西","吃東西","經營小屋"};
                int opt = JOptionPane.showOptionDialog(null,"請選擇吟遊詩人的行動",
                                                       "吟遊詩人 Bard",
                                                       JOptionPane.YES_NO_CANCEL_OPTION,
                                                       JOptionPane.PLAIN_MESSAGE,
                                                       new ImageIcon(getClass().getResource("picture/bard/bard.png")),
                                                       bard_opt,null);
                if (opt==JOptionPane.YES_OPTION){
                    String[] buyItem = {"食物","藥水",};
                    String ask = "請問你要買什麼？\n1金幣、10點FP：\n2份食物或1瓶藥水";
                    int itm = JOptionPane.showOptionDialog(null,ask,"商店 Shop",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/potion.png")),
                                                           buyItem,null);
                    if(itm==JOptionPane.YES_OPTION){
                        cmd = "skill bard buy_food";
                    }
                    if(itm==JOptionPane.NO_OPTION){
                        cmd = "skill bard buy_drink";
                    }
                }
                if (opt==JOptionPane.NO_OPTION){
                    String[] buyItem = {"食物","藥水",};
                    String ask = "現在想要吃什麼？\n消耗1份物品：\n恢復60點MP或60點HP";
                    int itm = JOptionPane.showOptionDialog(null,ask,"背包 Bag",
                                                           JOptionPane.YES_NO_OPTION,
                                                           JOptionPane.PLAIN_MESSAGE,
                                                           new ImageIcon(getClass().getResource("picture/potion.png")),
                                                           buyItem,null);
                    if(itm==JOptionPane.YES_OPTION){
                        cmd = "item bard food";
                    }
                    if(itm==JOptionPane.NO_OPTION){
                        cmd = "item bard drink";
                    }
                }
                if (opt==JOptionPane.CANCEL_OPTION){
                    cmd = "skill bard improve";
                }
            }
            else if ( e.getSource() == save )
                Save();
            else if ( e.getSource() == load )
                Load();
            else if ( e.getSource() == delet )
                Delet();
            else if ( e.getSource() == exit )
                cmd = "exit";
            
            //let contents be focused
            contents.requestFocus();
        }
    }
}