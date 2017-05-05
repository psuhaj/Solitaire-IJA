package solitaire.model.cmder;

import solitaire.model.cards.*;

public class targetToWorking implements Commander {

    CardStack working;
    CardDeck target;

    public targetToWorking(CardStack working, CardDeck target) {
        this.working = working;
        this.target = target;
    }

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

    public void undo() {
        System.out.println("========== YOU HAVE CALLED: targetToWorking.undo() ==========");
    }

}
