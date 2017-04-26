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
import java.io.IOException;
import java.io.File;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

//import ija.elements.AllMainObjects;

public class gui extends JFrame {

    public gui() {

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


        
        

        File file = new File("cards/2_of_clubs.png");
        BufferedImage image=null;
        try{
        	image = ImageIO.read(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		Image img = image.getScaledInstance(125, 181, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(img);
		JLabel test = new JLabel(imageIcon);


		//create jlabel for image
		JLabel test2 = new JLabel();
		//read img
		test2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("cards/3_of_clubs.png")).getImage().getScaledInstance(128, 181, Image.SCALE_SMOOTH)));
		

		//add cards
		panel.add(test); 
		panel.add(test2);

		//set cards position, so first we add everything then set positions
		Insets insets = panel.getInsets();//get borders?
		test.setBounds(40+insets.left, 70+ insets.top, 125,181);
		test2.setBounds(40+181+insets.left, 70+ insets.top, 125,181);

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

		panel.add(target1);
		panel.add(target2);
		panel.add(target3);
		panel.add(target4);

		target1.setBounds(insets.left+700, 70+ insets.top, 125,181);
		target2.setBounds(700+40+125+insets.right, 70+ insets.top, 125,181);
		target3.setBounds(125+40+125+700+40+insets.right, 70+ insets.top, 125,181);
		target4.setBounds(125+40+125+125+700+40+40+insets.right, 70+ insets.top, 125,181);



		JLayeredPane working1 = new JLayeredPane();
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
		tmp2.setBounds(0,50,125,181);
		working1.add(tmp2,new Integer(2),10);



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



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            gui ex = new gui();
            ex.setVisible(true);

        });
    }
}