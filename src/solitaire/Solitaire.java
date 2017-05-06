package solitaire;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.util.Collections;
import solitaire.model.game.*;
import java.util.Scanner; // for printing, TODO - remove it
import java.util.regex.*; // for printing, TODO - remove it
import solitaire.model.board.*; // for printing, TODO - remove it
import solitaire.model.cards.*; // for printing, TODO - remove it
import solitaire.*;
//import ija.elements.AllMainObjects;

public class Solitaire extends JFrame {
	//public static int gameFlag=0;
	public /*static*/ int gameUpFlag=0;
    public /*static*/ int targetNum=-1;
    public /*static*/ int workingNum=-1;
    public /*static*/ Card workingCard;

   // public static int targetIndex=-1;
   // public static int i,j;
    public  workingPanel[] workingStacks = new workingPanel[7];


    //public JLabel gameDeckUp;


	public Game GAME1;// = new Game();

    public Solitaire() {
        GAME1=new Game();
        initUI(this.workingStacks);
    }

    private void initUI(workingPanel[] workingStacks) {
        

    	JPanel panel = new JPanel();
    	setTitle("Solitaire");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400, 900));

    	//JPanel panelGrid = new JPanel();
    	
    	//GridLayout layout = new GridLayout(2,7);

    	/*layout.setHgap(10);
      	layout.setVgap(10);*/


    	//layout is null, so we can position the elements correctly
    	panel.setLayout(null);
    	//set the background color
        panel.setBackground(Color.green);


        
		        

        /*File file = new File("cards/2_of_clubs.png");
        BufferedImage image=null;
        try{
        	image = ImageIO.read(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		Image img = image.getScaledInstance(125, 181, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(img);
		JLabel test = new JLabel(imageIcon);*/


		//create jlabel for image
		//JLabel test2 = new JLabel();
		//read img
		//test2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("cards/3_of_clubs.png")).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
	    

        //create buttons






		JLabel gameDeck = new JLabel();
		JLabel gameDeckUp = new JLabel();

		//gameDeck.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeck.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
		
        gameDeck.setOpaque(true);
        gameDeck.setBackground(Color.red);
        gameDeckUp.setOpaque(true);
		gameDeckUp.setBackground(Color.lightGray);


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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        
                    }
                    else{
                        gameDeckUp.setIcon(null);
                        gameDeckUp.setOpaque(true);
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    getContentPane().repaint();
            }

        });


	    gameDeckUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                	
                gameUpFlag=1;
            }

        });


		//add cards
		panel.add(gameDeck); 
		panel.add(gameDeckUp);

		//set cards position, so first we add everything then set positions
		Insets insets = panel.getInsets();//get borders?
		gameDeck.setBounds(40+insets.left, 70+ insets.top, 125,181);
		gameDeckUp.setBounds(40+181+insets.left, 70+ insets.top, 125,181);

		//add target deck labels
		JLabel target1 = new JLabel();
		JLabel target2 = new JLabel();
		JLabel target3 = new JLabel();
		JLabel target4 = new JLabel();

		target1.setOpaque(true);
		target1.setBackground(Color.lightGray);
		target2.setOpaque(true);
		target2.setBackground(Color.lightGray);
		target3.setOpaque(true);
		target3.setBackground(Color.lightGray);
		target4.setOpaque(true);
		target4.setBackground(Color.lightGray);



        //listenery pre target balicky TODO pre dalsie operacie

        target1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameUpFlag==1){
                    gameUpFlag=0;
                    GAME1.gameDeckUpToTarget(0);
                    if(!GAME1.GameDeckUp.isEmpty()){
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));//obrazok sa nacita
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[0].isEmpty()){
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target1.revalidate();
                        
                    }
                    else{
                        target1.setIcon(null);
                        target1.setOpaque(true);
                        target1.setBackground(Color.lightGray);
                        target1.revalidate();//prekreslenie?
                    }
                    getContentPane().repaint();//prekreslenie

                }
                else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,0);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    
                    


                    //repaint game up

                    if(!GAME1.targetArray[0].isEmpty()){
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target1.revalidate();
                        
                    }
                    else{
                        target1.setIcon(null);
                        target1.setOpaque(true);
                        target1.setBackground(Color.lightGray);
                        target1.revalidate();//prekreslenie?
                    }
                }

                getContentPane().repaint();
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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[1].isEmpty()){
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target2.revalidate();
                        
                    }
                    else{
                        target2.setIcon(null);
                        target2.setOpaque(true);
                        target2.setBackground(Color.lightGray);
                        target2.revalidate();
                    }
                    getContentPane().repaint();

                } 
                else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,1);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            //JLabel tmp2 = new JLabel();
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    
                    


                    //repaint game up

                    if(!GAME1.targetArray[1].isEmpty()){
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target2.revalidate();
                        
                    }
                    else{
                        target2.setIcon(null);
                        target2.setOpaque(true);
                        target2.setBackground(Color.lightGray);
                        target2.revalidate();//prekreslenie?
                    }
                }

                getContentPane().repaint();
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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[2].isEmpty()){
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target3.revalidate();
                        
                    }
                    else{
                        target3.setIcon(null);
                        target3.setOpaque(true);
                        target3.setBackground(Color.lightGray);
                        target3.revalidate();
                    }
                    getContentPane().repaint();

                }
                else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,2);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            //JLabel tmp2 = new JLabel();
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    
                    


                    //repaint game up

                    if(!GAME1.targetArray[2].isEmpty()){
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target3.revalidate();
                        
                    }
                    else{
                        target3.setIcon(null);
                        target3.setOpaque(true);
                        target3.setBackground(Color.lightGray);
                        target3.revalidate();//prekreslenie?
                    }
                }

                getContentPane().repaint();
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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        gameDeckUp.revalidate();
                    }
                    else{
                        gameDeckUp.setBackground(Color.lightGray);
                        gameDeckUp.revalidate();
                    }
                    if(!GAME1.targetArray[3].isEmpty()){
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target4.revalidate();
                        
                    }
                    else{
                        target4.setIcon(null);
                        target4.setOpaque(true);
                        target4.setBackground(Color.lightGray);
                        target4.revalidate();
                    }
                    getContentPane().repaint();

                }
            else if(workingNum!=-1){
                    GAME1.workingToTarget(workingNum,3);
                    workingStacks[workingNum].removeAll();
                    if(GAME1.workingArray[workingNum].isEmpty()){
                        guiCard tmp = new guiCard();
                        tmp.setIndex(workingNum);
                        tmp.setOpaque(true);
                        tmp.setBackground(Color.lightGray);
                        tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                        tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                        workingStacks[workingNum].add(tmp,new Integer(1),0);
                    }

                    for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                        tmp2.setIndex(workingNum);
                        if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            //JLabel tmp2 = new JLabel();
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    
                    


                    //repaint game up

                    if(!GAME1.targetArray[3].isEmpty()){
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        target4.revalidate();
                        
                    }
                    else{
                        target4.setIcon(null);
                        target4.setOpaque(true);
                        target4.setBackground(Color.lightGray);
                        target4.revalidate();//prekreslenie?
                    }
                }

                getContentPane().repaint();
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
		panel.add(target1);
		panel.add(target2);
		panel.add(target3);
		panel.add(target4);

        //nastavenie pozicie
		target1.setBounds(insets.left+700, 70+ insets.top, 125,181);
		target2.setBounds(700+40+125+insets.right, 70+ insets.top, 125,181);
		target3.setBounds(125+40+125+700+40+insets.right, 70+ insets.top, 125,181);
		target4.setBounds(125+40+125+125+700+40+40+insets.right, 70+ insets.top, 125,181);



		/*JLayeredPane working1 = new JLayeredPane();
		panel.add(working1);
		working1.setBounds(insets.left+40, insets.top+400,125,600);
		JLabel tmp = new JLabel();
		tmp.setOpaque(true);
		tmp.setBackground(Color.lightGray);
		tmp.setBounds(0,0,125,181);
		working1.add(tmp,new Integer(1),0);

		JLabel tmp2 = new JLabel();
		tmp2.setOpaque(true);
		tmp2.setBackground(Color.RED);
        tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
		tmp2.setBounds(0,30,125,181);
		working1.add(tmp2,new Integer(2),1);*/



        //vytvorenie 7 stackov pre working
        //workingPanel[] workingStacks = new workingPanel[7];
        
        for(int i=0;i<7;i++){
            workingStacks[i] = new workingPanel();
            workingStacks[i].addIndex(i);//add th eindex of pane to object 
            panel.add(workingStacks[i]);
            workingStacks[i].setBounds(insets.left+40+(i*200), insets.top+400,125,600);
            //listener pre layered pane, aby sa vedelo na ktore sa kliklo
            
            workingStacks[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /*if(workingNum==-1){
                    workingPanel asd = (workingPanel)e.getSource();
                    workingNum=asd.getIndex();//get index of panel
                }
                else{
                    workingPanel asd = (workingPanel)e.getSource();
                    //zavolame funkciu na presun z jedneho stacku na druhy
                    GAME1.WorkingToWorking(workingNum,asd.getIndex(),workingCard);
                    System.out.println(workingNum);  
                    System.out.println(asd.getIndex());
                    System.out.println(workingCard.value());
                    //repaint?????
                           /// JLabel tmpc = new JLabel();
                            //tmpc.setOpaque(true);
                            //tmpc.setBackground(Color.lightGray);
                            //tmpc.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                            //workingStacks[asd.getIndex()].add(tmpc,new Integer(1),0);
                            
                            for(int k=0;k<GAME1.workingArray[asd.getIndex()].size();k++){
                                guiCard tmp2 = new guiCard();
                                int i = asd.getIndex();
                                tmp2.addObj(GAME1.workingArray[i].get(k));
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                tmp2.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        guiCard asdf = (guiCard)e.getSource();
                                        workingCard = asdf.getObj();//uloazime objekt karty
                                        System.out.println(workingCard.value());
                                    }
                                });

                            }
                            workingStacks[workingNum].removeAll();
                            if(GAME1.workingArray[workingNum].isEmpty()){
                                JLabel tmp = new JLabel();
                                tmp.setOpaque(true);
                                tmp.setBackground(Color.lightGray);
                                tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                                workingStacks[workingNum].add(tmp,new Integer(1),0);
                            }
                            for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                                //vyscistit layeredpane
                                //Component[] components = workingArray[workingNum].getComponents();
                                //pridat sive pozadia ak prazdne
                                guiCard tmp2 = new guiCard();
                                tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                                if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                //getContentPane().repaint();
                                //tmp2.revalidate();
                                tmp2.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        guiCard asdf = (guiCard)e.getSource();
                                        workingCard = asdf.getObj();//uloazime objekt karty
                                        System.out.println(workingCard.value());
                                    }
                                });

                            }
                            getContentPane().repaint();
                            workingNum=-1;
                }*/
            }
            });


            //ak je prazdny stack sivy label
            guiCard tmp = new guiCard();
            tmp.setIndex(i);
            tmp.setOpaque(true);
            tmp.setBackground(Color.lightGray);
            tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
            workingStacks[i].add(tmp,new Integer(1),0);
            //vykreslenie karticiek
            for(int j = 0;j<GAME1.workingArray[i].size();j++){//prejdeme vsetky karty v stacku a vykreslime potrebne
                guiCard tmp2 = new guiCard();
                tmp2.addObj(GAME1.workingArray[i].get(j));//prida objekt karty k gui karty, aby sa vedelo ze na co sa kliklo
                tmp2.setIndex(i);
                if(GAME1.workingArray[i].get(j).face()){//ak je tvarou hore tak obrazok sa vykresli
                    
                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(j).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                    tmp2.setBounds(0,j*30,125,181);
                    workingStacks[i].add(tmp2,new Integer(j+2),j+1);
                }
                else{//ak je false tak zadna strana
                    //JLabel tmp2 = new JLabel();
                    tmp2.setOpaque(true);
                    tmp2.setBackground(Color.RED);
                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                    tmp2.setBounds(0,j*30,125,181);
                    workingStacks[i].add(tmp2,new Integer(j+2),j+1);
                }
                tmp2.addMouseListener(new CustomMouseListener(gameDeckUp)); //{
                gameDeckUp.revalidate();
                   //@Override
                    //public void mouseClicked(MouseEvent e) {
                       
                        /*guiCard asdf = (guiCard)e.getSource();

                            
                        if(workingNum==-1){
                            //guiCard asdf = (guiCard)e.getSource();
                            workingCard = asdf.getObj();//uloazime objekt karty
                            workingNum= asdf.getIndex();
                            System.out.println(workingCard.value());
                        }
                        else{
                            System.out.println(workingCard.value());
                            GAME1.WorkingToWorking(workingNum,asdf.getIndex(),workingCard);
                            for(int k=0;k<GAME1.workingArray[asdf.getIndex()].size();k++){
                                guiCard tmp2 = new guiCard();
                                int i = asdf.getIndex();
                                tmp2.addObj(GAME1.workingArray[i].get(k));
                                tmp2.setIndex(i);
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                tmp2.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        guiCard asdf = (guiCard)e.getSource();
                                        workingCard = asdf.getObj();//uloazime objekt karty
                                        System.out.println(workingCard.value());
                                    }
                                });

                            }

                            workingStacks[workingNum].removeAll();

                            if(GAME1.workingArray[workingNum].isEmpty()){
                                JLabel tmp = new JLabel();
                                tmp.setOpaque(true);
                                tmp.setBackground(Color.lightGray);
                                tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                                workingStacks[workingNum].add(tmp,new Integer(1),0);
                            }

                            for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                                //vyscistit layeredpane
                                //Component[] components = workingArray[workingNum].getComponents();
                                //pridat sive pozadia ak prazdne
                                guiCard tmp2 = new guiCard();
                                tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                                tmp2.setIndex(workingNum);
                                if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                //getContentPane().repaint();
                                //tmp2.revalidate();
                                

                                //tmp2.addMouseListener(new MouseAdapter() {
                               //     @Override
                                //    public void mouseClicked(MouseEvent e) {
                                        





                                //    }
                                //});

                            }
                            getContentPane().repaint();
                            workingNum=-1;
                        }*/















                            //guiCard asdf = (guiCard)e.getSource();
                            //workingCard = asdf.getObj();//uloazime objekt karty
                            //System.out.println(workingCard.value());
                            /*for(int k=0;k<GAME1.workingArray[i].size();k++){
                                JLabel tmp2 = new JLabel();
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                }
                                tmp2.revalidate();

                            }

                            for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                                JLabel tmp2 = new JLabel();
                                if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                }
                                tmp2.revalidate();

                            }
                            getContentPane().repaint();
                            workingNum=-1;
                            workingIndex=-1;*/
                        //}
                
                    //}

                //});
            }

        }










		//some shit for creating and indexing the GRID
		/*
		JPanel[][] gridPanel = new JPanel[2][2];
		setLayout(new GridLayout(2,2));
		gridPanel[0][0]=panel;
		add(gridPanel[0][0]);
		gridPanel[0][1]=new JPanel();
		add(gridPanel[0][1]);
		for(int m = 1; m < 2; m++) {
   			for(int n = 0; n < 2; n++) {
		      gridPanel[m][n] = new JPanel();
		      add(gridPanel[m][n]);
		   }
		}
*/

        //add buttons
        JButton save = new JButton("Save game");
        save.setBounds(10,5,140,30);
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //String filename = JOptionPane.showInputDialog(panel,"Enter filename to be saved", null);
                //System.out.println(filename);  
                JFileChooser saveFile = new JFileChooser();
                saveFile.showSaveDialog(panel);
                saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file = saveFile.getCurrentDirectory();
                String path = file.getAbsolutePath();
                path = path + "/" + saveFile.getSelectedFile().getName();
                System.out.println(path);
                write_game(path,GAME1);
                
            }

        });
        JButton load = new JButton("Load game");
        load.setBounds(160,5,140,30);
        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    JFileChooser loadFile = new JFileChooser();
                    loadFile.showOpenDialog(panel);
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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        
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
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                            tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                            tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                            workingStacks[i].add(tmp,new Integer(1),0);
                        }
                        for(int k=0;k<GAME1.workingArray[i].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[i].get(k));
                        tmp2.setIndex(i);
                        if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            //JLabel tmp2 = new JLabel();
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    }



                    getContentPane().repaint();

                
            }

        });
        JButton undo = new JButton("Undo");
        undo.setBounds(310,5,80,30);
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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        
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
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                            tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                            tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                            workingStacks[i].add(tmp,new Integer(1),0);
                        }
                        for(int k=0;k<GAME1.workingArray[i].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[i].get(k));
                        tmp2.setIndex(i);
                        if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            //JLabel tmp2 = new JLabel();
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    }



                    getContentPane().repaint();
               
            }

        });
        JButton hint = new JButton("Hint");
        hint.setBounds(400,5,80,30);
        hint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String toPrint = GAME1.hint(); 
                /*JFrame hintWind = new JFrame("HINT");
                hintWind.setSize(500, 300);
                hintWind.setLocationRelativeTo(null);
                hintWind.setDefaultCloseOperation(EXIT_ON_CLOSE);
                hintWind.setPreferredSize(new Dimension(500, 300));*/
                //hintWind.setBounds(500,200,500,300);
                //add(hintWind);
                JOptionPane.showMessageDialog(panel,toPrint,"Hint",JOptionPane.INFORMATION_MESSAGE);
                
            }

        });
        JButton newGame = new JButton("New game");
        newGame.setBounds(490,5,140,30);
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
                        gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        
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
                        target1.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[0].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target2.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[1].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target3.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[2].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                        target4.setIcon(new ImageIcon(new ImageIcon(GAME1.targetArray[3].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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
                            tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                            tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                            workingStacks[i].add(tmp,new Integer(1),0);
                        }
                        for(int k=0;k<GAME1.workingArray[i].size();k++){
                        guiCard tmp2 = new guiCard();
                        tmp2.addObj(GAME1.workingArray[i].get(k));
                        tmp2.setIndex(i);
                        if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                        else{//ak je false tak zadna strana
                            //JLabel tmp2 = new JLabel();
                            tmp2.setOpaque(true);
                            tmp2.setBackground(Color.RED);
                            tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                        }
                       
                        tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                        gameDeckUp.revalidate();
                        
                    }
                    }



                    getContentPane().repaint();
                
            }

        });


        panel.add(save);
        panel.add(load);
        panel.add(undo);
        panel.add(hint);
        panel.add(newGame);



		add(panel);
        pack();



        //some shit for resizing,TODO

       /* @Override
        addComponentListener(new ComponentListener() {
    		public void componentResized(ComponentEvent e) {
        		target1.setBounds(insets.left+700, 70+ insets.top, 125,181);
				target2.setBounds(700+40+125+insets.right, 70+ insets.top, 125,181);
				target3.setBounds(125+40+125+700+40+insets.right, 70+ insets.top, 125,181);
				target4.setBounds(125+40+125+125+700+40+40+insets.right, 70+ insets.top, 125,181);          
    			pack();
    		}
		});*/

        //create listener and add
       /* ComponentListener listener = new ComponentAdapter() {
     		public void componentResized(ComponentEvent e) {
     			Dimension newSize = e.getComponent().getBounds().getSize();
				setSize(newSize);
				setPreferredSize(newSize); 
     			Insets insets = panel.getInsets();
        		target1.setBounds((700)*((int)(newSize.getWidth()/1400)), 70+ insets.top, 125,181);
				target2.setBounds(700+40+125+insets.right, 70+ insets.top, 125,181);
				target3.setBounds(125+40+125+700+40+insets.right, 70+ insets.top, 125,181);
				target4.setBounds((125+40+125+125+700+40+40+insets.right)*2, 70+ insets.top, 125,181);     
    			//pack();
      		}
      	};
      	addComponentListener(listener);*/
//add(panel);
      //	pack();

        
    }

   /* class DragMouseAdapter extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        JComponent c = (JComponent) e.getSource();
	        TransferHandler handler = c.getTransferHandler();
	        handler.exportAsDrag(c, e, TransferHandler.COPY);
	    }
	}
*/


    //mouselistener for cards on working stacks
    public class CustomMouseListener implements MouseListener{
      private JLabel gameDeckUp;
      
      public CustomMouseListener(JLabel gameDU){
           this.gameDeckUp=gameDU;
      
      }
      
      public void mouseClicked(MouseEvent e) {
                        guiCard asdf = (guiCard)e.getSource();

                        if(gameUpFlag==1){
                            //System.out.println("geci");
                            //repaint gamedeckup
                            if(!GAME1.GameDeckUp.isEmpty()){
                                gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                gameDeckUp.revalidate();
                            }
                            else{
                                gameDeckUp.setBackground(Color.lightGray);
                                gameDeckUp.revalidate();
                            }

                            //create card on working stack and repaint
                            /*int i = asdf.getIndex();
                            int k = GAME1.workingArray[i].size()-1;
                            GAME1.gameDeckUpToWorking(i);
                            guiCard tmp2 = new guiCard();
                            tmp2.addObj(GAME1.workingArray[i].get(k));
                            tmp2.setIndex(i);
                            tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                            tmp2.setBounds(0,k*30,125,181);
                            workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                            tmp2.revalidate();
                            getContentPane().repaint();
                            gameUpFlag=0;
                            System.out.println("gecii");*/
                            gameUpFlag=0;
                            GAME1.gameDeckUpToWorking(asdf.getIndex());
                            for(int k=0;k<GAME1.workingArray[asdf.getIndex()].size();k++){
                                guiCard tmp2 = new guiCard();
                                int i = asdf.getIndex();
                                tmp2.addObj(GAME1.workingArray[i].get(k));
                                tmp2.setIndex(i);
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                                gameDeckUp.revalidate();

                            }
                            
                        }
                        else 
                        if(workingNum==-1){
                            //guiCard asdf = (guiCard)e.getSource();
                            workingCard = asdf.getObj();//uloazime objekt karty
                            workingNum= asdf.getIndex();
                            //System.out.println(workingCard.value());
                        }
                        else{
                            //System.out.println(workingCard.value());
                            GAME1.WorkingToWorking(workingNum,asdf.getIndex(),workingCard);
                            for(int k=0;k<GAME1.workingArray[asdf.getIndex()].size();k++){
                                guiCard tmp2 = new guiCard();
                                int i = asdf.getIndex();
                                tmp2.addObj(GAME1.workingArray[i].get(k));
                                tmp2.setIndex(i);
                                if(GAME1.workingArray[i].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[i].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                                gameDeckUp.revalidate();

                            }

                            workingStacks[workingNum].removeAll();

                            if(GAME1.workingArray[workingNum].isEmpty()){
                                guiCard tmp = new guiCard();
                                tmp.setIndex(workingNum);
                                tmp.setOpaque(true);
                                tmp.setBackground(Color.lightGray);
                                tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
                                tmp.addMouseListener(new CustomMouseListener(gameDeckUp));
                                workingStacks[workingNum].add(tmp,new Integer(1),0);
                            }

                            for(int k=0;k<GAME1.workingArray[workingNum].size();k++){
                                //vyscistit layeredpane
                                //Component[] components = workingArray[workingNum].getComponents();
                                //pridat sive pozadia ak prazdne
                                guiCard tmp2 = new guiCard();
                                tmp2.addObj(GAME1.workingArray[workingNum].get(k));
                                tmp2.setIndex(workingNum);
                                if(GAME1.workingArray[workingNum].get(k).face()){//ak je tvarou hore tak obrazok sa vykresli
                                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[workingNum].get(k).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                else{//ak je false tak zadna strana
                                    //JLabel tmp2 = new JLabel();
                                    tmp2.setOpaque(true);
                                    tmp2.setBackground(Color.RED);
                                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                                    tmp2.setBounds(0,k*30,125,181);
                                    workingStacks[workingNum].add(tmp2,new Integer(k+2),k+1);
                                    tmp2.revalidate();
                                }
                                //getContentPane().repaint();
                                //tmp2.revalidate();
                                

                                tmp2.addMouseListener(new CustomMouseListener(gameDeckUp));
                                //gameDeckUp.revalidate();
                                    //System.out.println("geciike");
                            }
                            getContentPane().repaint();
                            workingNum=-1;
                        }
//System.out.println("gecicei");
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




    public static void main(String[] args) {

    	//GAME1 = new Game();

        SwingUtilities.invokeLater(() -> {
            Solitaire ex = new Solitaire();
            //ex.GAME1 = new Game();
            ex.setVisible(true);

        });

    }
}