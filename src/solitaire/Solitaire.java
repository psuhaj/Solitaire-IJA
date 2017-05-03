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
//import ija.elements.AllMainObjects;

public class Solitaire extends JFrame {
	//public static int gameFlag=0;
	public static int gameUpFlag=0;
    public static int targetNum=-1;
    public static int workingNUm=-1;

	static Game GAME1;

    public Solitaire() {

        initUI();
    }

    private void initUI() {

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
	    
		JLabel gameDeck = new JLabel();
		JLabel gameDeckUp = new JLabel();

		//gameDeck.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeck.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
		
        gameDeck.setOpaque(true);
        gameDeck.setBackground(Color.red);
        gameDeckUp.setOpaque(true);
		gameDeckUp.setBackground(Color.lightGray);


		/*MouseListener listener = new DragMouseAdapter();
        test.addMouseListener(listener);
        test2.addMouseListener(listener);

        test.setTransferHandler(new TransferHandler("icon"));
        test2.setTransferHandler(new TransferHandler("icon"));
*/

        //listener pre game deck, presuvaju sa karty na gameDeckUp na kliknuti
	    gameDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GAME1.deckToUp();
                    if(!GAME1.GameDeck.isEmpty()){
                        //gameDeck.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeck.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                        gameDeck.setBackground(Color.red);
                    }
                    else{
                        //gameDeck.setIcon(null);
                        //gameDeck.setOpaque(true);
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
                    //gameDeck.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeck.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                    //gameDeckUp.setIcon(new ImageIcon(new ImageIcon(GAME1.GameDeckUp.get().getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
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


        //listenery pre target balicky

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
                
            }

        });


       this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    
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
        JLayeredPane[] workingStacks = new JLayeredPane[7];
        
        for(int i=0;i<7;i++){
            workingStacks[i] = new JLayeredPane();
            panel.add(workingStacks[i]);
            workingStacks[i].setBounds(insets.left+40+(i*200), insets.top+400,125,600);
            JLabel tmp = new JLabel();
            tmp.setOpaque(true);
            tmp.setBackground(Color.lightGray);
            tmp.setBounds(0,0,125,181);//pozicia v ramci layeredpane
            workingStacks[i].add(tmp,new Integer(1),0);
            for(int j = 0;j<GAME1.workingArray[i].size();j++){//prejdeme vsetky karty v stacku a vykreslime potrebne
                if(GAME1.workingArray[i].get(j).face()){//ak je tvarou hore
                    JLabel tmp2 = new JLabel();
                    tmp2.setIcon(new ImageIcon(new ImageIcon(GAME1.workingArray[i].get(j).getCardImage()).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
                    tmp2.setBounds(0,j*30,125,181);
                    workingStacks[i].add(tmp2,new Integer(j+2),j+1);
                }
                else{
                    JLabel tmp2 = new JLabel();
                    tmp2.setOpaque(true);
                    tmp2.setBackground(Color.RED);
                    tmp2.setBorder(BorderFactory.createLineBorder(Color.black));
                    tmp2.setBounds(0,j*30,125,181);
                    workingStacks[i].add(tmp2,new Integer(j+2),j+1);
                }
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


    public static void main(String[] args) {

    	GAME1 = new Game();

        SwingUtilities.invokeLater(() -> {
            Solitaire ex = new Solitaire();
            ex.setVisible(true);

        });





    }
}