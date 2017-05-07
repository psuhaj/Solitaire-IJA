/**
 * @file       xCardStackColor.java
 * @brief      Card stack (target) implementation
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire.model.cards;

import java.util.Stack;

/**
 * Class for stack with color.
 */
public class xCardStackColor extends xCardDeck {

    private Card.Color color;

    /**
     * Constructs the object.
     */
    public xCardStackColor() {
        this.stack      = new Stack<Card>();
        this.stack_size = 0;
    }

    /**
     * Puts card to the stack if it meets the criteria.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    @Override
    public boolean put(Card card) {
        if (this.isEmpty()) { // is empty
            if (1 == card.value()) { // is ace
                this.stack_size++;
                this.stack.push(card);
                this.color = card.color();
                return true;
            }
            else {
                return false; // is not ace
            }
        }
        else { // is not empty
            if (this.color != card.color()) return false; // card does not match package color
            int difference = this.stack_size + 1;
            if (card.value() == difference) { // value is off by one
                this.stack_size++;
                this.stack.push(card);
                return true;
            }
            else { // value is not off by one
                return false;
            }
        }
    }

    /**
     * Checks conditions if the card can be putted on stack.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    @Override
    public boolean tryPut(Card card) {
        if (this.isEmpty()) { // is empty
            if (1 == card.value()) { // is ace
                return true;
            }
            else {
                return false; // is not ace
            }
        }
        else { // is not empty
            if (this.color != card.color()) return false; // card does not match package color
            int difference = this.stack_size + 1;
            if (card.value() == difference) { // value is off by one
                return true;
            }
            else { // value is not off by one
                return false;
            }
        }
    }


}