package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from waste deck to working stack.
 */
public class gameDeckUpToWorking implements Commander {

    CardStack working;
    CardDeck gameDeckUp;

    public gameDeckUpToWorking(CardDeck gameDeckUp, CardStack working) {
        this.working = working;
        this.gameDeckUp = gameDeckUp;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
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

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        Card tmp = this.working.pop();
        this.gameDeckUp.put(tmp);
        if (!this.working.isEmpty()) {
            this.working.get().turnFaceUp();
        }

    }

}
