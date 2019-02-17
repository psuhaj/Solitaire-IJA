/**
 * @file       guiCard.java
 * @brief      guiCard class implementation
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire;
import javax.swing.*;
import solitaire.model.cards.*;

/**
* Class for card in gui.
*/

public class guiCard extends JLabel {
	private Card card;
	private int index;

	/**
	* Adds the card object from logic to the gui representation of card.
	* @param	crd 	card object to be saves
	*/
	public void addObj(Card crd){
		this.card=crd;
	}

	/**
	* Gets the card object from the gui representation of card.
	* @return Card object.
	*/
	public Card getObj(){
		return this.card;
	}

	/**
	* Adds the cards working stack index to the gui representation of card.
	* @param	idx 	index of the working stack
	*/
	public void setIndex(int idx){
		this.index=idx;
	}

	/**
	* Gets the cards working stack index in gui.
	* @return Card index.
	*/
	public int getIndex(){
		return this.index;
	}

}