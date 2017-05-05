package solitaire.model.game;

import java.util.Collections;
import solitaire.model.board.*;
import solitaire.model.cards.*;
import solitaire.model.cmder.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public CardDeck GameDeck;
    public CardDeck GameDeckUp;
    public CardDeck[] targetArray;
    public CardStack[] workingArray;
    public Stack<Object> history;

    // first constructor
    public Game() {

        AbstractFactorySolitaire factory = new FactoryKlondike();
        
        this.GameDeck     = factory.createCardDeck();
        this.GameDeckUp   = new xCardDeck();
        this.targetArray  = new CardDeck[4];
        this.workingArray = new CardStack[7];
        this.history      = new Stack<Object>();
        
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

    // second constructor - because of load game
    public Game(CardDeck GD, CardDeck GDUP, CardDeck[] TA, CardStack[] WA) {
        this.GameDeck     = GD;
        this.GameDeckUp   = GDUP;
        this.targetArray  = TA;
        this.workingArray = WA;
        this.history      = new Stack<Object>();
    }


    public void workingToTarget(int workIndex,int targetIndex) {
        workingToTarget wtt = new workingToTarget(this.workingArray[workIndex], this.targetArray[targetIndex]);
        boolean retval = wtt.execute();
        if (retval) history.push(wtt);
    }


    public void targetToWorking(int targetIndex,int workIndex) {
        targetToWorking ttw = new targetToWorking(this.workingArray[workIndex], this.targetArray[targetIndex]);
        boolean retval = ttw.execute();
        if (retval) history.push(ttw);
    }


    public void gameDeckUpToTarget(int targetIndex) {
        gameDeckUpToTarget gdutt = new gameDeckUpToTarget(this.GameDeckUp, this.targetArray[targetIndex]);
        boolean retval = gdutt.execute();
        if (retval) history.push(gdutt);
    }


    public void gameDeckUpToWorking(int workIndex) {
        gameDeckUpToWorking gdutw = new gameDeckUpToWorking(this.GameDeckUp, this.workingArray[workIndex]);
        boolean retval = gdutw.execute();
        if (retval) history.push(gdutw);
    }


    public void WorkingToWorking(int workIndex1,int workIndex2,int number) {
        workingToWorking wtw = new workingToWorking(this.workingArray[workIndex1], this.workingArray[workIndex2], number);
        boolean retval = wtw.execute();
        if (retval) history.push(wtw);
    }


    public void TargetToTarget(int targetIndex1,int targetIndex2) {
        targetToTarget ttt = new targetToTarget(this.targetArray[targetIndex1], this.targetArray[targetIndex2]);
        boolean retval = ttt.execute();
        if (retval) history.push(ttt);
    }

    public void deckToUp() {
        deckToUp dtu = new deckToUp(this.GameDeck, this.GameDeckUp);
        boolean retval = dtu.execute();
        if (retval) history.push(dtu);
    }

    public void undo() {
        /*
        if (!history.empty()) {
            this.history.peek().undo();
            this.history.pop();
        }
        */
    }

}
