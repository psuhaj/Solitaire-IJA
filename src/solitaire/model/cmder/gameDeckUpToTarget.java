package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from waste deck to target deck.
 */
public class gameDeckUpToTarget implements Commander {

    CardDeck gameDeckUp;
    CardDeck target;

    public gameDeckUpToTarget(CardDeck gameDeckUp, CardDeck target) {
        this.gameDeckUp = gameDeckUp;
        this.target = target;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
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

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        Card tmp = this.target.pop();
        this.gameDeckUp.put(tmp);

    }

}
