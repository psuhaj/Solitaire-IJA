/**
 * @file       targetToWorking.java
 * @brief      targetToWorking command implementation
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from target deck to working stack.
 */
public class targetToWorking implements Commander {

    CardStack working;
    CardDeck target;

    public targetToWorking(CardStack working, CardDeck target) {
        this.working = working;
        this.target = target;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
    public boolean execute() {

        boolean retval = false;

        Card tmp = this.target.pop();
        boolean success = this.working.put(tmp);

        //if cant put card on target
        if (!success) {
            this.target.put(tmp);
        }
        else {
            retval = true;
        }

        return retval;
    }

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        Card tmp = this.working.pop();
        this.target.put(tmp);

    }

}
