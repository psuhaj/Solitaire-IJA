package solitaire.model.cards;

import java.util.Stack;
import java.util.Collections; // for stack reversing

/**
 * Class for stack.
 */
public class xCardStack extends xCardDeck implements CardStack {

    /**
     * Constructs the object.
     */
    public xCardStack() {
        this.stack      = new Stack<Card>();
        this.stack_size = 0;
    }

    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.stack.empty();
        //return super.isEmpty();
    }

    /**
     * Obtain size of stack.
     *
     * @return     Size of stack.
     */
    @Override
    public int size() {
        //return this.stack.size();
        return super.size();
    }

    /**
     * Put card to stack without checking the conditions.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    @Override
    public boolean putEmpty(Card card){
        return super.put(card);
    }

    /**
     * Put stack to stack without checking the conditions.
     *
     * @param      stack  The stack
     */
    @Override
    public void putEmpty(CardStack stack){
        xCardStack xs = (xCardStack) stack;
        this.stack.addAll(xs.stack);
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
            if (13 == card.value()) { // is king
                return true;
            }
            else {
                return false; // is not king
            }
        }
        else { // is not empty
            int difference = this.get().compareValue(card);
            if (!this.get().similarColorTo(card) && difference == 1) { // different color and the value is off by one
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Puts card to stack if it meets the criteria.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    @Override
    public boolean put(Card card) {
        if (this.isEmpty()) { // is empty
            if (13 == card.value()) { // is king
                super.put(card);
                return true;
            }
            else {
                return false; // is not king
            }
        }
        else { // is not empty
            int difference = this.get().compareValue(card);
            if (!this.get().similarColorTo(card) && difference == 1) { // different color and the value is off by one
                super.put(card);
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Puts stack to stack if it meets the criteria.
     *
     * @param      stack  The stack
     *
     * @return     Success of the action.
     */
    @Override
    public boolean put(CardStack stack) {
        if (stack==null) return false;
        xCardStack xs = (xCardStack) stack;
        if (this.stack.isEmpty()) { // field is empty => first must be 13(KING)
            if (13 == xs.stack.get(0).value()) { // bottom card of stack is king
                this.stack.addAll(xs.stack);
                return true;
            }
            else { // bottom card of stack is not king
                return false;
            }
        }
        else { // field is not empty => we must check stack(0) is off by one and it has different color
            int difference = this.stack.peek().value() - xs.stack.get(0).value();
            if (difference != 1) return false;
            else if (this.stack.peek().similarColorTo(xs.stack.get(0))) return false;
            else {
                this.stack.addAll(xs.stack);
                this.stack_size += xs.size(); // TODO FIX THIS, it makes problems
                return true;
            }
        }

    }

    /**
     * Function pops out stack from the given card to stack top from the stack.
     *
     * @param      card  The card
     *
     * @return     Null if card is not in the stack, stack otherwise.
     */
    @Override
    public CardStack pop(Card card) {
        int idx = this.stack.search(card);
        if (idx == -1) return null; // not found
        else {
            xCardStack xs = new xCardStack();
            for(int i=0; i<idx; i++) {
                this.stack_size--;
                xs.stack_size++;
                xs.stack.push(this.stack.pop());
            }
            Collections.reverse(xs.stack);
            return xs;
        }
    }

}
