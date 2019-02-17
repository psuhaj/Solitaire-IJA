/**
 * @file       Solitaire.java
 * @brief      Implementation of the GUI and connecting the parts together. This is the main class.
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */



package solitaire;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.util.Collections;
import solitaire.model.game.*;
import solitaire.model.board.*; 
import solitaire.model.cards.*; 
import solitaire.*;




/**
* Main class of the game. Gui is implemented and game is being created.
*/

public class Solitaire extends  JPanel {
    
    public static int counter=1;
    public static int games = 1;
    public static boolean oneGame = true;
	public static boolean[][] whereIsGame = new boolean[2][2];

	private  int gameUpFlag=0;
    private  int targetNum=-1;
    private int workingNum=-1;
    private  Card workingCard;


    private  workingPanel[] workingStacks = new workingPanel[7];

    private JLabel gameDeckUp;


	public Game GAME1;

    private double scale;


    /**
    * Constructor with scale flag and index of the game.
    *@param scaleIn 	flag for detecting scale
    *@param index 		index of the game
    */
    public Solitaire(boolean scaleIn,int index) {
        if(scaleIn){
            this.scale=0.5;
        }
        else{
            this.scale=1;
        }
        GAME1=new Game();
        initUI(this.workingStacks,index);
    }


    /**
    * Constructor for new game with already started game.
    *@param gameIn 		game object to load
    *@param index 		index of the game
    */
    public Solitaire(Game gameIn,int index){

        this.scale=0.5;
        GAME1=gameIn;
        initUI(this.workingStacks,index);
    }


    /**
    * Initialization of the GUI for one game. 
    *@param workingStacks	array of working stacks
    *@param index 			index of the game
    */
    private void initUI(workingPanel[] workingStacks,int index) {
        



    	//layout is null, so we can position the elements correctly
    	setLayout(null);
    	//set the background color
        setBackground(Color.green);



        //create buttons

        Insets insets = getInsets();
        JLabel text = new JLabel();
        text.setText("Game :"+index);
        text.setBounds((int)((40+181+220+insets.left)*scale), (int)((70+ insets.top)*scale), (int)(scale*150),(int)(scale*100));
        add(text);




		JLabel gameDeck = new JLabel();
		gameDeckUp = new JLabel();

		
        gameDeck.setOpaque(true);
        
        if(GAME1.GameDeck.isEmpty()){
        	gameDeck.setBackground(Color.lightGray);
        }
        else{
        	gameDeck.setBackground(Color.red);
        }
        if(!GAME1.GameDeckUp.isEmpty()){
             gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));                 
        }
        else{
            gameDeckUp.setOpaque(true);
            gameDeckUp.setBackground(Color.lightGray);
        }
        


        //listener pre game deck, presuvaju sa karty na gameDeckUp na kliknuti
	    gameDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GAME1.deckToUp();
                    if(!GAME1.GameDeck.isEmpty()){
                        gameDeck.setBackground(Color.red);
                    }
                    else{
                        gameDeck.setBackground(Color.lightGray);
                        gameDeck.revalidate();
                    }
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    revalidate();
            }

        });


	    gameDeckUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                	
                gameUpFlag=1;
            }

        });


		//add cards
		add(gameDeck); 
		add(gameDeckUp);

		//set cards position, so first we add everything then set positions
		gameDeck.setBounds((int)((40+insets.left)*scale), (int)((70+ insets.top)*scale), (int)(scale*125),(int)(scale*181));
		gameDeckUp.setBounds((int)((40+181+insets.left)*scale), (int)((70+ insets.top)*scale), (int)(scale*125),(int)(scale*181));

		//add target deck labels
		JLabel target1 = new JLabel();
		JLabel target2 = new JLabel();
		JLabel target3 = new JLabel();
		JLabel target4 = new JLabel();


        if(!GAME1.targetArray[0].isEmpty()){
            target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));             
        }
        else{
            target1.setOpaque(true);
            target1.setBackground(Color.lightGray); 
        }

        if(!GAME1.targetArray[1].isEmpty()){
            target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));             
        }
        else{
            target2.setOpaque(true);
            target2.setBackground(Color.lightGray); 
        }

        if(!GAME1.targetArray[2].isEmpty()){
            target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));             
        }
        else{
            target3.setOpaque(true);
            target3.setBackground(Color.lightGray); 
        }

        if(!GAME1.targetArray[3].isEmpty()){
            target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));             
        }
        else{
            target4.setOpaque(true);
            target4.setBackground(Color.lightGray); 
        }
		



        //listenery pre target balicky TODO pre dalsie operacie

        target1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameUpFlag==1){
                    gameUpFlag=0;
                    GAME1.gameDeckUpToTarget(0);
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));//obrazok sa nacita
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[0].isEmpty()){
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));
                        target1.revalidate();
                        
                    }
                    else{
                        target1.setIcon(null);
                        target1.setOpaque(true);
                        target1.setBackground(Color.lightGray);
                        target1.revalidate();
                    }

                    revalidate();

                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,0);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                        workingStacks[workingNum].repaint();
                        tmp.revalidate();
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125),(int)(scale*181), Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].repaint();
                        gameDeckUp.revalidate();

                        
                    }
                    
                    
                    //repaint game up

                    if(!GAME1.targetArray[0].isEmpty()){
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance((int)(scale*125),  (int)(scale*181), Image.SCALE_SMOOTH)));
                        target1.revalidate();
                        
                    }
                    else{
                        target1.setIcon(null);
                        target1.setOpaque(true);
                        target1.setBackground(Color.lightGray);
                        target1.revalidate();//prekreslenie?
                    }


                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                revalidate();
                workingNum=-1;
                
            }

        });

       target2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameUpFlag==1){
                    gameUpFlag=0;
                    GAME1.gameDeckUpToTarget(1);
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[1].isEmpty()){
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target2.revalidate();
                        
                    }
                    else{
                        target2.setIcon(null);
                        target2.setOpaque(true);
                        target2.setBackground(Color.lightGray);
                        target2.revalidate();
                    }
                    revalidate();
                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                } 
                else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,1);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                        workingStacks[workingNum].repaint();
                    	tmp.revalidate();
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].repaint();
                        gameDeckUp.revalidate();
                        
                    }
                    
                    


                    //repaint game up

                    if(!GAME1.targetArray[1].isEmpty()){
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target2.revalidate();
                        
                    }
                    else{
                        target2.setIcon(null);
                        target2.setOpaque(true);
                        target2.setBackground(Color.lightGray);
                        target2.revalidate();//prekreslenie?
                    }
                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                }


                revalidate();
                workingNum=-1;   
                
            }

        });

       target3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameUpFlag==1){
                    gameUpFlag=0;
                    GAME1.gameDeckUpToTarget(2);
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[2].isEmpty()){
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target3.revalidate();
                        
                    }
                    else{
                        target3.setIcon(null);
                        target3.setOpaque(true);
                        target3.setBackground(Color.lightGray);
                        target3.revalidate();
                    }
                    revalidate();
                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,2);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                        workingStacks[workingNum].repaint();
                        tmp.revalidate();
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].repaint();
                        gameDeckUp.revalidate();
                        
                    }
                    
                    


                    //repaint game up

                    if(!GAME1.targetArray[2].isEmpty()){
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target3.revalidate();
                        
                    }
                    else{
                        target3.setIcon(null);
                        target3.setOpaque(true);
                        target3.setBackground(Color.lightGray);
                        target3.revalidate();//prekreslenie?
                    }
                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                revalidate();
                workingNum=-1;     
                
            }

        });

       target4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameUpFlag==1){
                    gameUpFlag=0;
                    GAME1.gameDeckUpToTarget(3);
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[3].isEmpty()){
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target4.revalidate();
                        
                    }
                    else{
                        target4.setIcon(null);
                        target4.setOpaque(true);
                        target4.setBackground(Color.lightGray);
                        target4.revalidate();
                    }

                    revalidate();
                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,3);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                        workingStacks[workingNum].repaint();
                        tmp.revalidate();
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
              
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener());
                        workingStacks[workingNum].repaint();
                       
                        
                    }
                    
                    gameDeckUp.revalidate();


                    //repaint game up

                    if(!GAME1.targetArray[3].isEmpty()){
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target4.revalidate();
                        
                    }
                    else{
                        target4.setIcon(null);
                        target4.setOpaque(true);
                        target4.setBackground(Color.lightGray);
                        target4.revalidate();//prekreslenie?
                    }
                    if(GAME1.targetArray[0].size()==13 && GAME1.targetArray[1].size() == 13 && GAME1.targetArray[2].size() == 13 && GAME1.targetArray[3].size() == 13){
                        JOptionPane.showMessageDialog(Solitaire.this,"Congratulation. You won the game.","WIN",JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                //getContentPane().repaint();
                revalidate();
                workingNum=-1;     
                
            }

        });


       this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                workingNum=-1;
                gameUpFlag=0;
            }

        });





        //pridavanie target balickov
		add(target1);
		add(target2);
		add(target3);
		add(target4);

        //nastavenie pozicie
		target1.setBounds((int)(scale*(insets.left+700)),(int)(scale* (70+ insets.top)), (int)(scale*125),(int)(scale*181));
		target2.setBounds((int)(scale*(700+40+125+insets.right)), (int)(scale *(70+ insets.top)), (int)(scale*125),(int)(scale*181));
		target3.setBounds((int)(scale*(125+40+125+700+40+insets.right)), (int)(scale*(70+ insets.top)), (int)(scale*125),(int)(scale*181));
		target4.setBounds((int)(scale*(125+40+125+125+700+40+40+insets.right)), (int)(scale*(70+ insets.top)), (int)(scale*125),(int)(scale*181));



		
        
        for(int i=0;i<7;i++){
            workingStacks[i] = new workingPanel();
            workingStacks[i].addIndex(i);//add th eindex of pane to object 
            add(workingStacks[i]);
            workingStacks[i].setBounds((int)(scale*(insets.left+40+(i*200))), (int)(scale*(insets.top+400)),(int)(scale*125),(int)(scale*600));
            //listener pre layered pane, aby sa vedelo na ktore sa kliklo
            
            workingStacks[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            });


            //ak je prazdny stack sivy label
            guiCard tmp = new guiCard();
            tmp.setIndex(i);
            tmp.setOpaque(true);
            tmp.setBackground(Color.lightGray);
            tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
            workingStacks[i].add(tmp,new Integer(1),0);
            tmp.addMouseListener(new CustomMouseListener()); 
            tmp.revalidate();
            //vykreslenie karticiek
            for(int j = 0;j<GAME1.workingArray[i].size();j++){//prejdeme vsetky karty v stacku a vykreslime potrebne
                guiCard tmp2 = new guiCard();
                tmp2.addObj(GAME1.workingArray[i].get(j));//prida objekt karty k gui karty, aby sa vedelo ze na co sa kliklo
                tmp2.setIndex(i);
                if(GAME1.workingArray[i].get(j).face()){//ak je tvarou hore tak obrazok sa vykresli
                    
                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(j).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                    tmp2.setBounds(0,(int)(scale*j*30),(int)(scale*125),(int)(scale*181));
                    workingStacks[i].add(tmp2,new Integer(j+2),j+1);
                }
                else{//ak je false tak zadna strana
                    tmp2.setOpaque(true);
                    tmp2.setBackground(Color.RED);
                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                    tmp2.setBounds(0,(int)(scale*j*30),(int)(scale*125),(int)(scale*181));
                    workingStacks[i].add(tmp2,new Integer(j+2),j+1);
                }
                tmp2.addMouseListener(new CustomMouseListener()); 
                gameDeckUp.revalidate();

            }

        }










		//some shit for creating and indexing the GRID
		

        //add buttons
        JButton save = new JButton("Save game");
        save.setBounds((int)(scale*10),(int)(scale*5),(int)(scale*250),(int)(scale*30));
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser saveFile = new JFileChooser();
                int retVal = saveFile.showSaveDialog(Solitaire.this);
                if(retVal == JFileChooser.APPROVE_OPTION){
                    saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    File file = saveFile.getCurrentDirectory();
                    String path = file.getAbsolutePath();
                    path = path + "/" + saveFile.getSelectedFile().getName();
                    //System.out.println(path);
                    write_game(path,GAME1);
                }else if(retVal == JFileChooser.CANCEL_OPTION){
                    ;
                }

                
            }

        });

        JButton load = new JButton("Load game");
        load.setBounds((int)(scale*270),(int)(scale*5),(int)(scale*250),(int)(scale*30));
        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    JFileChooser loadFile = new JFileChooser();
                    int retVal = loadFile.showOpenDialog(Solitaire.this);
                    
                    if(retVal == JFileChooser.APPROVE_OPTION){
                    
                        File file = loadFile.getSelectedFile();
                        String path = file.getAbsolutePath();
                        System.out.println(path);
                        GAME1 = load_game(path);
                        if(!GAME1.GameDeck.isEmpty()){
                            gameDeck.setBackground(Color.red);
                        }
                        else{
                            gameDeck.setBackground(Color.lightGray);
                            gameDeck.revalidate();
                        }
                        //repaint game deck up
                        if(!GAME1.GameDeckUp.isEmpty()){
                            gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            
                        }
                        else{
                            gameDeckUp.setIcon(null);
                            gameDeckUp.setOpaque(true);
                            gameDeckUp.setBackground(Color.lightGray);
                            gameDeckUp.revalidate();
                        }
                        //repatint targets
                        //target 1
                        if(!GAME1.targetArray[0].isEmpty()){
                            target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            target1.revalidate();
                            
                        }
                        else{
                            target1.setIcon(null);
                            target1.setOpaque(true);
                            target1.setBackground(Color.lightGray);
                            target1.revalidate();//prekreslenie?
                        }
                        //target 2
                        if(!GAME1.targetArray[1].isEmpty()){
                            target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            target2.revalidate();
                            
                        }
                        else{
                            target2.setIcon(null);
                            target2.setOpaque(true);
                            target2.setBackground(Color.lightGray);
                            target2.revalidate();//prekreslenie?
                        }
                        //target 3
                        if(!GAME1.targetArray[2].isEmpty()){
                            target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            target3.revalidate();
                            
                        }
                        else{
                            target3.setIcon(null);
                            target3.setOpaque(true);
                            target3.setBackground(Color.lightGray);
                            target3.revalidate();//prekreslenie?
                        }
                        //target 4
                        if(!GAME1.targetArray[3].isEmpty()){
                            target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            target4.revalidate();
                            
                        }
                        else{
                            target4.setIcon(null);
                            target4.setOpaque(true);
                            target4.setBackground(Color.lightGray);
                            target4.revalidate();//prekreslenie?
                        }
                        //repaint workings


                        for(int i = 0; i < 7;i++){
                            workingStacks[i].removeAll();
                            if(GAME1.workingArray[i].isEmpty()){
                                guiCard tmp = new guiCard();
                                tmp.setIndex(i);
                                tmp.setOpaque(true);
                                tmp.setBackground(Color.lightGray);
                                tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                                tmp.addMouseListener(new CustomMouseListener());
                                workingStacks[i].add(tmp,new Integer(1),0);
                                workingStacks[i].repaint();
                            }
                            for(int k=0;k<GAME1.workingArray[i].size();k++){
                            guiCard tmp2 = new guiCard();
                            tmp2.addObj(GAME1.workingArray[i].get(k));
                            tmp2.setIndex(i);
                            if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                                tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                tmp2.revalidate();
                            }
                            else{//ak je false tak zadna strana
                                tmp2.setOpaque(true);
                                tmp2.setBackground(Color.RED);
                                tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                tmp2.revalidate();
                            }
                            workingStacks[i].repaint();
                            tmp2.addMouseListener(new CustomMouseListener());
                            gameDeckUp.revalidate();
                            
                        }
                        }

                        revalidate();


                    }else if(retVal == JFileChooser.CANCEL_OPTION){
                        ;
                    }



                
            }

        });
        JButton undo = new JButton("Undo");
        undo.setBounds((int)(scale*530),(int)(scale*5),(int)(scale*170),(int)(scale*30));
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    GAME1.undo(); 
                    
                    //repaint game deck
                    if(!GAME1.GameDeck.isEmpty()){
                        gameDeck.setBackground(Color.red);
                    }
                    else{
                        gameDeck.setBackground(Color.lightGray);
                        gameDeck.revalidate();
                    }
                    //repaint game deck up
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    //repatint targets
                    //target 1
                    if(!GAME1.targetArray[0].isEmpty()){
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target1.revalidate();
                        
                    }
                    else{
                        target1.setIcon(null);
                        target1.setOpaque(true);
                        target1.setBackground(Color.lightGray);
                        target1.revalidate();//prekreslenie?
                    }
                    //target 2
                    if(!GAME1.targetArray[1].isEmpty()){
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target2.revalidate();
                        
                    }
                    else{
                        target2.setIcon(null);
                        target2.setOpaque(true);
                        target2.setBackground(Color.lightGray);
                        target2.revalidate();//prekreslenie?
                    }
                    //target 3
                    if(!GAME1.targetArray[2].isEmpty()){
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target3.revalidate();
                        
                    }
                    else{
                        target3.setIcon(null);
                        target3.setOpaque(true);
                        target3.setBackground(Color.lightGray);
                        target3.revalidate();//prekreslenie?
                    }
                    //target 4
                    if(!GAME1.targetArray[3].isEmpty()){
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target4.revalidate();
                        
                    }
                    else{
                        target4.setIcon(null);
                        target4.setOpaque(true);
                        target4.setBackground(Color.lightGray);
                        target4.revalidate();//prekreslenie?
                    }
                    //repaint workings


                    for(int i = 0; i < 7;i++){
                        workingStacks[i].removeAll();
                        if(GAME1.workingArray[i].isEmpty()){
                            guiCard tmp = new guiCard();
                            tmp.setIndex(i);
                            tmp.setOpaque(true);
                            tmp.setBackground(Color.lightGray);
                            tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                            tmp.addMouseListener(new CustomMouseListener());
                            workingStacks[i].add(tmp,new Integer(1),0);
                            workingStacks[i].repaint();
                        }
                        for(int k=0;k<GAME1.workingArray[i].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[i].get(k));
                        tmp2.setIndex(i);
                        if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        workingStacks[i].repaint();
                        tmp2.addMouseListener(new CustomMouseListener());
                        gameDeckUp.revalidate();
                        
                    }
                    }

                revalidate();
            }

        });
        JButton hint = new JButton("Hint");
        hint.setBounds((int)(scale*710),(int)(scale*5),(int)(scale*170),(int)(scale*30));
        hint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String toPrint = GAME1.hint(); 
                JOptionPane.showMessageDialog(Solitaire.this,toPrint,"Hint",JOptionPane.INFORMATION_MESSAGE);
                
            }

        });
        JButton newGame = new JButton("New game");
        newGame.setBounds((int)(scale*890),(int)(scale*5),(int)(scale*250),(int)(scale*30));
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GAME1 = new Game();
                if(!GAME1.GameDeck.isEmpty()){
                        gameDeck.setBackground(Color.red);
                    }
                    else{
                        gameDeck.setBackground(Color.lightGray);
                        gameDeck.revalidate();
                    }
                    //repaint game deck up
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    //repatint targets
                    //target 1
                    if(!GAME1.targetArray[0].isEmpty()){
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target1.revalidate();
                        
                    }
                    else{
                        target1.setIcon(null);
                        target1.setOpaque(true);
                        target1.setBackground(Color.lightGray);
                        target1.revalidate();//prekreslenie?
                    }
                    //target 2
                    if(!GAME1.targetArray[1].isEmpty()){
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target2.revalidate();
                        
                    }
                    else{
                        target2.setIcon(null);
                        target2.setOpaque(true);
                        target2.setBackground(Color.lightGray);
                        target2.revalidate();//prekreslenie?
                    }
                    //target 3
                    if(!GAME1.targetArray[2].isEmpty()){
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target3.revalidate();
                        
                    }
                    else{
                        target3.setIcon(null);
                        target3.setOpaque(true);
                        target3.setBackground(Color.lightGray);
                        target3.revalidate();//prekreslenie?
                    }
                    //target 4
                    if(!GAME1.targetArray[3].isEmpty()){
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                        target4.revalidate();
                        
                    }
                    else{
                        target4.setIcon(null);
                        target4.setOpaque(true);
                        target4.setBackground(Color.lightGray);
                        target4.revalidate();//prekreslenie?
                    }
                    //repaint workings


                    for(int i = 0; i < 7;i++){
                        workingStacks[i].removeAll();
                        if(GAME1.workingArray[i].isEmpty()){
                            guiCard tmp = new guiCard();
                            tmp.setIndex(i);
                            tmp.setOpaque(true);
                            tmp.setBackground(Color.lightGray);
                            tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                            tmp.addMouseListener(new CustomMouseListener());
                            workingStacks[i].add(tmp,new Integer(1),0);
                            workingStacks[i].repaint();
                        }
                        for(int k=0;k<GAME1.workingArray[i].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[i].get(k));
                        tmp2.setIndex(i);
                        if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        workingStacks[i].repaint();
                        tmp2.addMouseListener(new CustomMouseListener());
                        gameDeckUp.revalidate();
                        
                    }
                    }

                    revalidate();
            }

        });


        add(save);
        add(load);
        add(undo);
        add(hint);
        add(newGame);


        
    }

   


    //mouselistener for cards on working stacks
    public class CustomMouseListener implements MouseListener{
      //private JLabel gameDeckUp;
      
     
      
      public void mouseClicked(MouseEvent e) {
                        guiCard asdf = (guiCard)e.getSource();

                        if(gameUpFlag==1){
                            GAME1.gameDeckUpToWorking(asdf.getIndex());
                            //repaint gamedeckup
                            if(!GAME1.GameDeckUp.isEmpty()){
                                gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                                gameDeckUp.revalidate();
                            }
                            else{
                                gameDeckUp.setIcon(null);
                                gameDeckUp.setOpaque(true);
                                gameDeckUp.setBackground(Color.lightGray);
                                gameDeckUp.revalidate();
                            }
                            Solitaire.this.revalidate();

                            
                            gameUpFlag=0;

                            


                            for(int k=0;k<GAME1.workingArray[asdf.getIndex()].size();k++){
                                guiCard tmp2 = new guiCard();
                                int i = asdf.getIndex();
                                tmp2.addObj(GAME1.workingArray[i].get(k));
                                tmp2.setIndex(i);
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                tmp2.addMouseListener(new CustomMouseListener());
                                workingStacks[i].repaint();

                            }
                             gameDeckUp.revalidate();
                            //getContentPane().repaint();
                            Solitaire.this.revalidate();
                        }
                        else 
                        if(workingNum==-1){
                            workingCard = asdf.getObj();//uloazime objekt karty
                            workingNum= asdf.getIndex();
                        }
                        else{
                            GAME1.WorkingToWorking(workingNum,asdf.getIndex(),workingCard);
                            workingStacks[asdf.getIndex()].removeAll();
                            Solitaire.this.revalidate();
                            if(GAME1.workingArray[asdf.getIndex()].isEmpty()){
                                guiCard tmp = new guiCard();
                                tmp.setIndex(workingNum);
                                tmp.setOpaque(true);
                                tmp.setBackground(Color.lightGray);
                                tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                                tmp.addMouseListener(new CustomMouseListener());
                                tmp.revalidate();
                                workingStacks[workingNum].add(tmp,new Integer(1),0);
                                workingStacks[workingNum].repaint();
                            }
                            Solitaire.this.revalidate();
                            for(int k=0;k<GAME1.workingArray[asdf.getIndex()].size();k++){
                                guiCard tmp2 = new guiCard();
                                int i = asdf.getIndex();
                                tmp2.addObj(GAME1.workingArray[i].get(k));
                                tmp2.setIndex(i);
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                tmp2.addMouseListener(new CustomMouseListener());
                                gameDeckUp.revalidate();
                               Solitaire.this.revalidate();
                               workingStacks[asdf.getIndex()].repaint();
                            }

                            workingStacks[workingNum].removeAll();
                            workingStacks[workingNum].repaint();

                            if(GAME1.workingArray[workingNum].isEmpty()){
                                guiCard tmp = new guiCard();
                                tmp.setIndex(workingNum);
                                tmp.setOpaque(true);
                                tmp.setBackground(Color.lightGray);
                                tmp.setBounds(0,0,(int)(scale*125),(int)(scale*181));//pozicia v ramci layeredpane
                                tmp.addMouseListener(new CustomMouseListener());
                                tmp.revalidate();
                                workingStacks[workingNum].add(tmp,new Integer(1),0);
                                workingStacks[workingNum].repaint();
                            }

                            for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                                //vyscistit layeredpane
                                //pridat sive pozadia ak prazdne
                                guiCard tmp2 = new guiCard();
                                tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                                tmp2.setIndex(workingNum);
                                if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance((int)(scale*125), (int)(scale*181), Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,(int)(scale*k*30),(int)(scale*125),(int)(scale*181));
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }

                                tmp2.addMouseListener(new CustomMouseListener());

                            }
                            //getContentPane().repaint();
                            Solitaire.this.revalidate();
                            workingStacks[workingNum].repaint();
                            workingNum=-1;
                        }

      }



    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
   }

   /**
    	* Loads the given file.
    	* @param	path path to the file
    	* @return	initialized game object
    */

   public static Game load_game(String path) {

        // READ FILE TO STRING (data[i]=one line from file)
        String[] data = new String[13];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                data[i]=line;
                i++;
            }
        } catch (IOException e) {
            return null;
        }

        // INITIALIZATION OF GD, GDUP, TA[], WA[]
        String[] cards;
        Card card;
        AbstractFactorySolitaire factory = new FactoryKlondike();//factory for creating decks and stacks

        CardDeck    GD   = new xCardDeck();
        CardDeck    GDUP = new xCardDeck();
        CardDeck[]  TA   = new CardDeck[4];
        CardStack[] WA   = new CardStack[7];

        for(int i = 0; i<7;i++){
            WA[i] = factory.createWorkingPack();
        }

        for(int i = 0; i<4;i++){
            TA[i] = factory.createTargetPack();
        }

        // FILLING GD, GDUP, TA[], WA[] WITH DATA
        for(int idx=0; idx<13; idx++) {
            if (idx == 0) { // GD
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(String code: cards) {
                        card = new xCard(code);
                        GD.put(card); // put card to GD
                    }
                }

            }
            else if (idx == 1) { // GDUP
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(String code: cards) {
                        card = new xCard(code);
                        GDUP.put(card); // put card to GDUP
                    }
                }

            }
            else if (1 < idx && idx < 6) { // target[]
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(String code: cards) {
                        card = new xCard(code);
                        TA[idx-2].put(card); // put card to TA[0-3]
                    }
                }

            }
            else { // working[]
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(int i=0; i<cards.length; i++) {
                        card = new xCard(cards[i]);
                        WA[idx-6].putEmpty(card); // put card to WA[0-6]
                    }
                }

            }
        }

        Game g = new Game(GD, GDUP, TA, WA);
        return g;
    }


    	/**
    	* Saves the given game to the given path.
    	* @param	path path to the file
    	* @param	game game object to be saved
    	* @return	exit code for detecting errors
    	*/
        public static int write_game(String path, Game game) {

        // create array of empty strings
        String[] data = new String[13];
        for(int i=0; i<13; i++) data[i]="";

        // fill the array with data
        for(int idx=0; idx<13; idx++) {
            if (idx == 0) { // GD
                if (game.GameDeck.isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.GameDeck.size(); i++) {
                        data[idx] += game.GameDeck.get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
            else if (idx == 1) { // GDUP
                if (game.GameDeckUp.isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.GameDeckUp.size(); i++) {
                        data[idx] += game.GameDeckUp.get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
            else if (1 < idx && idx <= 5) { // target[]
                if (game.targetArray[idx-2].isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.targetArray[idx-2].size(); i++) {
                        data[idx] += game.targetArray[idx-2].get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
            else { // working[]
                if (game.workingArray[idx-6].isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.workingArray[idx-6].size(); i++) {
                        data[idx] += game.workingArray[idx-6].get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
        }

        // write the data into file
        try{
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            for(int idx=0; idx<13; idx++) writer.print(data[idx]);
            writer.close();
        } catch (IOException e) {
            return 1;
        }

        return 0;
    }


    /**
    * Main method. Creating one game at the start, than more games if the user wants.
    * @param args  command line arguments
    */

    public static void main(String[] args) {

    	//GAME1 = new Game();
        int counter  = 1;
        if(counter==1){
            for(int i=0;i<2;i++){
                for(int j=0;j<2;j++){
                    whereIsGame[i][j]=false;
                }
            } 
        }
        

        SwingUtilities.invokeLater(() -> {
        Solitaire ex = new Solitaire(false,Solitaire.counter);

        JFrame board = new JFrame();
        board.setTitle("Solitaire");
        board.setSize(1400, 900);
        board.setLocationRelativeTo(null);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setPreferredSize(new Dimension(1400, 900));
        board.add(ex);
        board.getContentPane().setBackground(Color.green);
        



        Solitaire[][] gridPanel = new Solitaire[2][2];

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("New");
        JMenuItem newGame = new JMenuItem("Create new game");
        JMenuItem game1 = new JMenuItem("Exit game 1");
        JMenuItem game2 = new JMenuItem("Exit game 2");
        JMenuItem game3 = new JMenuItem("Exit game 3");
        JMenuItem game4 = new JMenuItem("Exit game 4");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //board.add(new Solitaire());

                if(oneGame){
                    oneGame=false;
                    board.remove(ex);
                    board.setLayout(new GridLayout(2,2));   
                    gridPanel[0][0] = new Solitaire(ex.GAME1,counter);
                    Solitaire.counter++;
                    Solitaire.games++;
                    board.add(gridPanel[0][0]);
                    whereIsGame[0][0]=true;
                    gridPanel[0][0].revalidate();
                    board.getContentPane().repaint();
                    
                }

                

                for(int m = 0; m < 2; m++) {
                    for(int n = 0; n < 2; n++) {
                      if(whereIsGame[m][n]==false){
                       //   board.remove(gridPanel[m][n]);
                          board.getContentPane().repaint();
                          gridPanel[m][n] = new Solitaire(true,(2*m+n)+1);
                          Solitaire.counter++;
                          Solitaire.games++;
                          board.add(gridPanel[m][n]);
                          whereIsGame[m][n]=true;
                          gridPanel[m][n].revalidate();
                          board.getContentPane().repaint();
                          return;
                          
                      }
  
                   }

                }


            }
        });
        game1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(whereIsGame[0][0]){
                    board.remove(gridPanel[0][0]);
                    whereIsGame[0][0]=false;
                    Solitaire.games--;
             		for(int m = 0; m < 2; m++) {
                    	for(int n = 0; n < 2; n++) {
                      		if(whereIsGame[m][n]==true){
   								gridPanel[m][n].revalidate();
                      		}
                      	}
                    }
                    board.getContentPane().repaint();
                }

            }
        });
        game2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(whereIsGame[0][1]){
                board.remove(gridPanel[0][1]);
                whereIsGame[0][1]=false;
                Solitaire.games--;
                for(int m = 0; m < 2; m++) {
                	for(int n = 0; n < 2; n++) {
                  		if(whereIsGame[m][n]==true){
								gridPanel[m][n].revalidate();
                  		}
                  	}
                }
                board.getContentPane().repaint();
            }
            }
        });
        game3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(whereIsGame[1][0]){
                board.remove(gridPanel[1][0]);
                whereIsGame[1][0]=false;
                Solitaire.games--;
                for(int m = 0; m < 2; m++) {
                	for(int n = 0; n < 2; n++) {
                  		if(whereIsGame[m][n]==true){
								gridPanel[m][n].revalidate();
                  		}
                  	}
                }
                board.getContentPane().repaint();
            }
            }
        });
        game4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(whereIsGame[1][1]){
                board.remove(gridPanel[1][1]);
                whereIsGame[1][1]=false;
                Solitaire.games--;
                for(int m = 0; m < 2; m++) {
                	for(int n = 0; n < 2; n++) {
                  		if(whereIsGame[m][n]==true){
								gridPanel[m][n].revalidate();
                  		}
                  	}
                }
                board.getContentPane().repaint();
            }
            }
        });
        menu.add(newGame);
        menu.add(game1);
        menu.add(game2);
        menu.add(game3);
        menu.add(game4);
        menubar.add(menu);
        board.setJMenuBar(menubar);


        board.pack();


        board.setVisible(true);

        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.getContentPane().repaint();
            }

        });


        });

    }
}