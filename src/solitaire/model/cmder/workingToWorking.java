package solitaire.model.cmder;

import solitaire.model.cards.*;

public class workingToWorking {

    CardStack working1;
    CardStack working2;
    int number;

    public workingToWorking(CardStack working1, CardStack working2, int number) {
        this.working1 = working1;
        this.working2 = working2;
        this.number   = number;
    }

    public boolean execute() {

        boolean retval = false;

        Card card = this.working1.get(number);

        if (!card.face()) return false; // do nothing if the card we want to move from is facedown

        boolean success = this.working2.put(card);

        if (success) {
            retval = true;
            this.working2.pop();
            CardStack tmp = this.working1.pop(card);
            this.working2.put(tmp);
            if (!this.working1.isEmpty()) {
                Card tmp2 = this.working1.pop();
                tmp2.turnFaceUp();
                this.working1.putEmpty(tmp2);
            }
        }

        return retval;
    }

    public void undo() {
        System.out.print("========== YOU HAVE CALLED: workingToWorking.undo() ==========");
    }

}
