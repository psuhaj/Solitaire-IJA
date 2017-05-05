package solitaire.model.cmder;

import solitaire.model.cards.*;

public class workingToTarget implements Commander {

    CardStack working;
    CardDeck target;

    public workingToTarget(CardStack working, CardDeck target) {
        this.working = working;
        this.target = target;
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
                Card tmp2 = this.working.pop();
                tmp2.turnFaceUp();
                this.working.putEmpty(tmp2);
            }
            retval = true;
        }

        return retval;
    }

    public void undo() {

        Card tmp = this.target.pop();
        this.working.get().turnFaceDown();
        this.working.putEmpty(tmp);

    }

}
