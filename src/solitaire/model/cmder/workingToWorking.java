package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from working stack to working stack.
 */
public class workingToWorking implements Commander {

    CardStack working1;
    CardStack working2;
    Card card;
    boolean facechange;

    public workingToWorking(CardStack working1, CardStack working2, Card card) {
        this.working1   = working1;
        this.working2   = working2;
        this.card       = card;
        this.facechange = false;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
    public boolean execute() {

        boolean retval = false;

        if (!card.face()) return false;

        boolean success = this.working2.put(card);

        if (success) {
            retval = true;
            this.working2.pop();
            CardStack tmp = this.working1.pop(card); // get stack
            this.working2.put(tmp); // put stack
            if (!this.working1.isEmpty()) { // turn Down to UP
                if (!this.working1.get().face()) this.facechange = true;
                Card tmp2 = this.working1.pop();
                tmp2.turnFaceUp();
                this.working1.putEmpty(tmp2);
            }
        }

        return retval;
    }

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        if (this.facechange) this.working1.get().turnFaceDown();
        CardStack tmp = this.working2.pop(card);
        this.working1.putEmpty(tmp);

    }

}
