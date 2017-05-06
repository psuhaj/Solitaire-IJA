package solitaire;
import javax.swing.*;



public class workingPanel extends JLayeredPane {
	private int index;

	public void addIndex(int idx){
		this.index=idx;
	}


	public int getIndex(){
		return this.index;
	}

}