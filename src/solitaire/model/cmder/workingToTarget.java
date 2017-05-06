package solitaire.model.cmder;

import solitaire.model.cards.*;

public class workingToTarget implements Commander {

    CardStack working;
    CardDeck target;
    boolean facechange;

    public workingToTarget(CardStack working, CardDeck target) {
        this.working = working;
        this.target = target;
        this.facechange = false;
    }

    public boolean execute() {

        boolean retval = false;

        Card tmp = this.working.pop();
        boolean success = this.target.put(tmp);

        // if cant put card on target
        if (!success) {
            this.working.putEmpty(tmp);
        }
        else {
            //turn the top card on the working stack up
            if (!this.working.isEmpty()) {
                this.facechange = true;
                Card tmp2 = this.working.pop();
                tmp2.turnFaceUp();
                this.working.putEmpty(tmp2);
            }
            retval = true;
        }

        return retval;
    }

    public void undo() {

        if (this.facechange) this.working.get().turnFaceDown();
        Card tmp = this.target.pop();
        this.working.putEmpty(tmp);

    }

}
