package solitaire.model.game;


import java.util.Collections;
import solitaire.model.board.*;
import solitaire.model.cards.*;
import solitaire.model.cmder.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;


public class Game {


    private CardDeck GameDeck;
    private CardDeck GameDeckUp;
    private CardDeck[] targetArray;
    private CardStack[] workingArray;
    private Commander commander;
    private AbstractFactorySolitaire factory;


    // constructor
    public Game() {

        this.commander  = new Commander();
        this.factory    = new FactoryKlondike();

        this.GameDeck   = factory.createCardDeck();
        this.GameDeckUp = new xCardDeck();
        this.targetArray = new CardDeck[4];
        this.workingArray = new CardStack[7];

        // create working stacks
        for(int i = 0; i<7;i++){
            this.workingArray[i] = this.factory.createWorkingPack();
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
            this.targetArray[i] = this.factory.createTargetPack();
        }
    }

    /*
		TODO
		methods are called as method(working[3], target[3])

		do we need to FIXIT as 
		method(int a, int b) {
			working = this.working[a];
			target  = this.target[b];
			...
		}

    */

    public void workingToTarget(CardStack working,CardDeck target) {

        Card tmp = working.pop();
        boolean success = target.put(tmp);
        
        // if cant put card on target
        if (!success) {
            working.putEmpty(tmp);
        }
        else {
        	//turn the top card on the working stack up
            if (!working.isEmpty()) {
                Card tmp2 = working.pop();
                tmp2.turnFaceUp();
                working.putEmpty(tmp2);
            }
            this.commander.cmd_do(Commander.enum_cmd.W_T);
        }
        // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.W_T);
    }

    public void targetToWorking(CardStack working,CardDeck target) {
        
        Card tmp = target.pop();
        boolean success = working.put(tmp);
        
        //if cant put card on target
        if (!success) {
            target.put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.T_W);
        }
        // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.T_W);
    }

    public void gameDeckUpToTarget(CardDeck up,CardDeck target) {
        
        Card tmp = up.pop();
        boolean success = target.put(tmp);
        
        //if cant put card on target
        if (!success) {
            up.put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.GU_T);
        }
        // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.GU_T);
    }

    public void gameDeckUpToWorking(CardStack working,CardDeck up) {

        Card tmp = up.pop();
        boolean success = working.put(tmp);
        
        //if cant put card on target
        if (!success) {
            up.put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.GU_W);
        }
        // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.GU_W);
    }

    public void TargetToTarget(CardDeck target1,CardDeck target2) {
        
        Card tmp = target1.pop();
        boolean success = target2.put(tmp);
        
        //if cant put card on target
        if (!success) {
            target1.put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.T_T);
        }
        // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.T_T);
    }

    public void WorkingToWorking(CardStack working1,CardStack working2,int number) {
        
        Card card = working1.get(number);
        
        if (!card.face()) return; // do nothing if the card we want to move from is facedown

        boolean success = working2.put(card);

        if (success) {
            this.commander.cmd_do(Commander.enum_cmd.W_W);
            working2.pop();
            CardStack tmp = working1.pop(card);
            working2.put(tmp);
            if (!working1.isEmpty()) {
                Card tmp2 = working1.pop();
                tmp2.turnFaceUp();
                working1.putEmpty(tmp2);
            }
        }
        /*
        // TODO: remove this block comment or fix it

        CardStack tmp = working1.pop(card);
        boolean success = working2.put(tmp);
        //if cant put card on target
        if(!success){
            working1.put(tmp);
        }
        else{
            //turn the to card up on the stack from where cards were moved
            Card tmp2 = working1.pop();
            tmp2.turnFaceUp();
            working1.put(tmp2);
        }

        */
    }

    public void deckToUp(CardDeck gameDeck,CardDeck up) {
        
        //if deck is empty
        if (gameDeck.isEmpty()) {
            int size=up.size();
            for (int i = 0; i<size;i++) {
                Card tmp2 = up.pop();
                tmp2.turnFaceDown();
                gameDeck.put(tmp2);
            }
            // here is missing commander.cmd_to() !!!!!!!!!!
            // TODO peter's: GE_GU, // gameDeckEMPTY => gameDeckUP
            // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.GE_GU);
        }
        else {
            Card tmp = gameDeck.pop();
            tmp.turnFaceUp();
            up.put(tmp);
            this.commander.cmd_do(Commander.enum_cmd.G_GU);
        }
    }

    // TODO: UNDO methods (inverse methods)

}
