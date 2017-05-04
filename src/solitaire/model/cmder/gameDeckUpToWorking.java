package solitaire.model.cmder;

import solitaire.model.cards.*;

public class gameDeckUpToWorking {

	CardStack working;
	CardDeck gameDeckUp;

	public gameDeckUpToWorking(CardStack working, CardDeck gameDeckUp) {
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
		System.out.print("========== YOU HAVE CALLED: gameDeckUpToWorking.undo() ==========");
	}

}
