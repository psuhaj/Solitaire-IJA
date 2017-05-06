package solitaire.model.cmder;

import solitaire.model.cards.*;

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
            	this.facechange = true;
                Card tmp2 = this.working1.pop();
                tmp2.turnFaceUp();
                this.working1.putEmpty(tmp2);
            }
        }

        return retval;
    }

    public void undo() {

    	System.out.println("UNDO: this.working1.get().turnFaceDown();");
        if (this.facechange) this.working1.get().turnFaceDown();
        System.out.println("UNDO: this.card = "+card);
        System.out.println("UNDO: this.working2.pop(card);");
        CardStack tmp = this.working2.pop(card);
        System.out.println("UNDO: this.working1.putEmpty(tmp);");
        this.working1.putEmpty(tmp);

    }

}
