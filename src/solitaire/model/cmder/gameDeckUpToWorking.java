package solitaire.model.cmder;

import solitaire.model.cards.*;

public class gameDeckUpToWorking implements Commander {

    CardStack working;
    CardDeck gameDeckUp;

    public gameDeckUpToWorking(CardDeck gameDeckUp, CardStack working) {
        this.working = working;
        this.gameDeckUp = gameDeckUp;
    }

    public boolean execute() {

        boolean retval = false;

        Card tmp = this.gameDeckUp.pop();
        boolean success = this.working.put(tmp);

        //if cant put card on target
        if (!success) {
            this.gameDeckUp.put(tmp);
        }
        else {
            retval = true;
        }

        return retval;
    }

    public void undo() {

        Card tmp = this.working.pop();
        this.gameDeckUp.put(tmp);
        if (!this.working.isEmpty()) {
        	this.working.get().turnFaceUp();
        }

    }

}
