package solitaire.model.cmder;

import solitaire.model.cards.*;

public class deckToUp implements Commander {

    CardDeck gameDeck;
    CardDeck gameDeckUp;

    public deckToUp(CardDeck gameDeck, CardDeck gameDeckUp) {
        this.gameDeck   = gameDeck;
        this.gameDeckUp = gameDeckUp;
    }

    public boolean execute() {

        boolean retval = false;

        if (!(this.gameDeck.isEmpty() && this.gameDeckUp.isEmpty())) {
            //if deck is empty
            if (this.gameDeck.isEmpty()) {
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

    public void undo() {
        System.out.println("========== YOU HAVE CALLED: deckToUp.undo() ==========");
    }

}
