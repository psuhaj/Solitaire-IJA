package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from game deck to waste deck.
 */
public class deckToUp implements Commander {

    CardDeck gameDeck;
    CardDeck gameDeckUp;

    /**
     * Constructs the object.
     *
     * @param      gameDeck    The game deck.
     * @param      gameDeckUp  The waste deck.
     */
    public deckToUp(CardDeck gameDeck, CardDeck gameDeckUp) {
        this.gameDeck   = gameDeck;
        this.gameDeckUp = gameDeckUp;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
    public boolean execute() {

        boolean retval = false;

        if (!(this.gameDeck.isEmpty() && this.gameDeckUp.isEmpty())) { // if GD and GDUP are not empty
            if (this.gameDeck.isEmpty()) { // if GD is empty
                // replace all cards from GDUP to GD
                int size = this.gameDeckUp.size();
                for (int i = 0; i<size;i++) {
                    Card tmp2 = this.gameDeckUp.pop();
                    tmp2.turnFaceDown();
                    this.gameDeck.put(tmp2);
                }
                retval = true;
            }
            else {
                Card tmp = this.gameDeck.pop();
                tmp.turnFaceUp();
                this.gameDeckUp.put(tmp);
                retval = true;
            }
        }

        return retval;
    }

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        if (this.gameDeckUp.isEmpty()) {
            int size = this.gameDeck.size();
            for (int i = 0; i<size;i++) {
                Card tmp2 = this.gameDeck.pop();
                tmp2.turnFaceUp();
                this.gameDeckUp.put(tmp2);
            }
        }
        else {
            Card tmp = this.gameDeckUp.pop();
            tmp.turnFaceDown();
            this.gameDeck.put(tmp);
        }

    }

}
