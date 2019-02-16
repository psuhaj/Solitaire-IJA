/**
 * @file       CardStack.java
 * @brief      Card stack interface
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire.model.cards;

/**
 * Interface of card stack.
 */
public interface CardStack extends CardDeck {

    /**
     * Puts stack to stack if it meets the criteria.
     *
     * @param      stack  The stack
     *
     * @return     Success of the action.
     */
    public boolean put(CardStack stack);

    /**
     * Function pops out stack from the given card to stack top from the stack.
     *
     * @param      card  The card
     *
     * @return     Null if card is not in the stack, stack otherwise.
     */
    public CardStack pop(Card card);

    /**
     * Checks conditions if the card can be putted on stack.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    public boolean tryPut(Card card);

    /**
     * Put card to stack without checking the conditions.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    public boolean putEmpty(Card card);

    /**
     * Put stack to stack without checking the conditions.
     *
     * @param      stack  The stack
     */
    public void putEmpty(CardStack stack);

}
