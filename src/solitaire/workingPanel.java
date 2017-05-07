/**
 * @file       workingPanel.java
 * @brief      workingPanel class implementation
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire;
import javax.swing.*;


/**
* Class for working stack in gui.
*/

public class workingPanel extends JLayeredPane {
	private int index;

	/**
	* Adds the index of working stack to its gui representation.
	* @param	idx 	index of the working stack
	*/
	public void addIndex(int idx){
		this.index=idx;
	}

	/**
	* Gets the index of working stack from its gui representation.
	* @return Index of the working stack.
	*/
	public int getIndex(){
		return this.index;
	}

}