package solitaire;
import javax.swing.*;
import solitaire.model.cards.*;



public class guiCard extends JLabel {
	private Card card;
	private int index;

	public void addObj(Card crd){
		this.card=crd;
	}

	public Card getObj(){
		return this.card;
	}

	public void setIndex(int idx){
		this.index=idx;
	}

	public int getIndex(){
		return this.index;
	}

}