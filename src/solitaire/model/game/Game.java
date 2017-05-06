package solitaire.model.game;

import java.util.Collections;
import solitaire.model.board.*;
import solitaire.model.cards.*;
import solitaire.model.cmder.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for game.
 */
public class Game {

    public CardDeck GameDeck;
    public CardDeck GameDeckUp;
    public CardDeck[] targetArray;
    public CardStack[] workingArray;
    public Stack<Commander> history;

	/**
	 * Constructs the object.
	 */
    public Game() {

        AbstractFactorySolitaire factory = new FactoryKlondike();
        
        this.GameDeck     = factory.createCardDeck();
        this.GameDeckUp   = new xCardDeck();
        this.targetArray  = new CardDeck[4];
        this.workingArray = new CardStack[7];
        this.history      = new Stack<Commander>();
        
        // create working stacks
        for(int i = 0; i<7;i++){
            this.workingArray[i] = factory.createWorkingPack();
        }
        
        // add cards to working stacks
        for(int i=0;i<7;i++){
            for(int j=i;j<7;j++){
                this.workingArray[j].putEmpty(this.GameDeck.pop());
            }
        }
        
        //turn face up
        for(int i=0;i<7;i++){
            Card tmp=this.workingArray[i].pop();
            tmp.turnFaceUp();
            this.workingArray[i].putEmpty(tmp);
        }
        
        //create target packs
        for(int i = 0; i<4;i++){
            this.targetArray[i] = factory.createTargetPack();
        }
    }

    /**
     * Constructs the object from given values.
     *
     * @param      GD    Game deck.
     * @param      GDUP  Waste deck.
     * @param      TA    Target decks.
     * @param      WA    Working stacks.
     */
    public Game(CardDeck GD, CardDeck GDUP, CardDeck[] TA, CardStack[] WA) {
        this.GameDeck     = GD;
        this.GameDeckUp   = GDUP;
        this.targetArray  = TA;
        this.workingArray = WA;
        this.history      = new Stack<Commander>();
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from working stack to target deck.
     *
     * @param      idxWorking  The index of working stack.
     * @param      idxTarget   The index of target deck.
     */
    public void workingToTarget(int idxWorking,int idxTarget) {
        if (this.workingArray[idxWorking].isEmpty()) return;
        workingToTarget wtt = new workingToTarget(this.workingArray[idxWorking], this.targetArray[idxTarget]);
        boolean retval = wtt.execute();
        if (retval) history.push(wtt);
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from target deck to working stack.
     *
     * @param      idxTarget   The index of target deck.
     * @param      idxWorking  The index of working stack.
     */
    public void targetToWorking(int idxTarget,int idxWorking) {
    	if (this.targetArray[idxTarget].isEmpty()) return;
        targetToWorking ttw = new targetToWorking(this.workingArray[idxWorking], this.targetArray[idxTarget]);
        boolean retval = ttw.execute();
        if (retval) history.push(ttw);
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from waste deck to target deck.
     *
     * @param      idxTarget  The index target deck.
     */
    public void gameDeckUpToTarget(int idxTarget) {
    	if (this.GameDeckUp.isEmpty()) return;
        gameDeckUpToTarget gdutt = new gameDeckUpToTarget(this.GameDeckUp, this.targetArray[idxTarget]);
        boolean retval = gdutt.execute();
        if (retval) history.push(gdutt);
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from waste deck to working stack.
     *
     * @param      idxWorking  The index of working stack.
     */
    public void gameDeckUpToWorking(int idxWorking) {
    	if (this.GameDeckUp.isEmpty()) return;
        gameDeckUpToWorking gdutw = new gameDeckUpToWorking(this.GameDeckUp, this.workingArray[idxWorking]);
        boolean retval = gdutw.execute();
        if (retval) history.push(gdutw);
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from working stack 1 to working stack 2.
     *
     * @param      idxWorking1  The index of working stack 1
     * @param      idxWorking2  The index of working stack 2
     * @param      card         The card
     */
    public void WorkingToWorking(int idxWorking1, int idxWorking2, Card card) {
    	if (this.workingArray[idxWorking1].isEmpty()) return;
        workingToWorking wtw = new workingToWorking(this.workingArray[idxWorking1], this.workingArray[idxWorking2], card);
        boolean retval = wtw.execute();
        if (retval) history.push(wtw);
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from target deck 1 to target deck 2.
     *
     * @param      idxTarget1  The index of target deck 1
     * @param      idxTarget2  The index of target deck 2
     */
    public void TargetToTarget(int idxTarget1,int idxTarget2) {
    	if (this.targetArray[idxTarget1].isEmpty()) return;
        targetToTarget ttt = new targetToTarget(this.targetArray[idxTarget1], this.targetArray[idxTarget2]);
        boolean retval = ttt.execute();
        if (retval) history.push(ttt);
    }

    /**
     * Function creates a object representing command which will be executed.
     * If the execution returns true, the object is pushed to the stack of commands.
     * This function creates a object representing command which provides movement from game deck to waste deck.
     */
    public void deckToUp() {
        deckToUp dtu = new deckToUp(this.GameDeck, this.GameDeckUp);
        boolean retval = dtu.execute();
        if (retval) history.push(dtu);
    }

    /**
     * Function pop out the last object which was inserted into this stack and provide undo of object representing command.
     */
    public void undo() {
        if (!history.empty()) {
            this.history.peek().undo();
            this.history.pop();
        }
    }

    /**
     * Function go throught the game and find possible moves.
     *
     * @return     Message including hint.
     */
    public String hint() {

        int size;
        Card card;

        // find hint WORKING TO TARGET
        for(int i=0; i<7; i++) {
            if (!this.workingArray[i].isEmpty()) {
                card = this.workingArray[i].get();
                for(int j=0; j<4; j++) {
                    if (this.targetArray[j].tryPut(card)) {
                        return "Move from "+(i+1)+". working pile to "+(j+1)+". target pile.";
                    }
                }
            }
        }

        // find hint WORKING TO WORKING
        for(int i=0; i<7; i++) {
            if (!this.workingArray[i].isEmpty()) {
                size = this.workingArray[i].size();
                for(int j=size-1; j>=0; j--) {
                    card = this.workingArray[i].get(j);
                    if(card.face()) {
                        for(int k=0; k<7; k++) {
                            if (k != i) {
                                if (this.workingArray[k].tryPut(card)) {
                                    return "Move from "+(i+1)+". working pile to "+(k+1)+". working pile.";
                                }
                            }
                        }
                    }
                }
            }
        }

        // find hint GAMEDECKUP TO TARGET
        if (!this.GameDeckUp.isEmpty()) {
            card = this.GameDeckUp.get();
            for(int j=0; j<4; j++) {
                if (this.targetArray[j].tryPut(card)) {
                    return "Move from waste pile to "+(j+1)+". target pile.";
                }
            }
        }

        // find hint GAMEDECKUP TO WORKING
        if (!this.GameDeckUp.isEmpty()) {
            card = this.GameDeckUp.get();
            for(int i=0; i<7; i++) {
                if (this.workingArray[i].tryPut(card)) {
                    return "Move from waste pile to "+(i+1)+". working pile.";
                }
            }
        }

        // find hint GAMEDECK TO GAMEDECKUP
        if (!this.GameDeck.isEmpty()) { // GD is not empty
            return "Get next card to waste pile.";
        }
        else {
            if (!this.GameDeckUp.isEmpty()) { // GD is empty and GDUP is not empty
                return "Reload the waste pile.";
            }
            else {
                return "No more moves.";
            }
        }

    }

}
