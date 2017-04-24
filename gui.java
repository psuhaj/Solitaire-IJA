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


    	panel.setLayout(new GridLayout(2,7,10,10));

       // JButton quitButton = new JButton("Quit");

       // panel.add(quitButton);

        panel.setBackground(Color.green);


        /*JLabel card1 = new JLabel(new ImageIcon(getClass().getResource("cards/2_of_clubs.png")),JLabel.CENTER);
        //card.setPreferedSize(12,18);
        JLabel card2 = new JLabel(new ImageIcon(getClass().getResource("cards/3_of_clubs.png")));
        JLabel card3 = new JLabel(new ImageIcon(getClass().getResource("cards/4_of_clubs.png")));
        JLabel card4 = new JLabel(new ImageIcon(getClass().getResource("cards/5_of_clubs.png")));

       // panelGrid.add(card);
        //layout.add(card);
        panel.add(card1);
        panel.add(card2);
        panel.add(card3);
        panel.add(card4);*/

        JLabel[] cards = new JLabel[14];


        for(int i =0 ; i< 14; i++){
        	/*JLabel */cards[i] = new JLabel(new ImageIcon(getClass().getResource("cards/2_of_clubs.png")),JLabel.CENTER);
        	cards[i].setPreferredSize(new Dimension(20, 20));
        	panel.add(cards[i]);
        }



        add(panel);

        pack();

        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            gui ex = new gui();
            ex.setVisible(true);
        });
    }
}