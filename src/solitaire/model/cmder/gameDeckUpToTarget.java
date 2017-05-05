package solitaire.model.cmder;

import solitaire.model.cards.*;

public class gameDeckUpToTarget implements Commander {

	CardDeck gameDeckUp;
	CardDeck target;

	public gameDeckUpToTarget(CardDeck gameDeckUp, CardDeck target) {
		this.gameDeckUp = gameDeckUp;
		this.target = target;
	}

	public boolean execute() {
		
		boolean retval = false;

		Card tmp = this.gameDeckUp.pop();
        boolean success = this.target.put(tmp);

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
		System.out.println("========== YOU HAVE CALLED: gameDeckUpToTarget.undo() ==========");
	}

}
