/**
 * @file       workingToTarget.java
 * @brief      workingToTarget command implementation
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from working stack to target deck.
 */
public class workingToTarget implements Commander {

    CardStack working;
    CardDeck target;
    boolean facechange;

    public workingToTarget(CardStack working, CardDeck target) {
        this.working = working;
        this.target = target;
        this.facechange = false;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
    public boolean execute() {

        boolean retval = false;

        Card tmp = this.working.pop();
        if (!this.working.isEmpty()) {
            if (this.working.get().face() == false) {
                this.facechange = true;
            }
        }
        boolean success = this.target.put(tmp);

        // if cant put card on target
        if (!success) {
            this.working.putEmpty(tmp);
        }
        else {
            //turn the top card on the working stack up
            if (!this.working.isEmpty()) {
                Card tmp2 = this.working.pop();
                tmp2.turnFaceUp();
                this.working.putEmpty(tmp2);
            }
            retval = true;
        }

        return retval;
    }

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        if (this.facechange) this.working.get().turnFaceDown();
        Card tmp = this.target.pop();
        this.working.putEmpty(tmp);

    }

}
