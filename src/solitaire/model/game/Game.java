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
    public Commander commander;


    // constructor
    public Game() {

        this.commander  = new Commander();
        AbstractFactorySolitaire factory = new FactoryKlondike();

        this.GameDeck     = factory.createCardDeck();
        this.GameDeckUp   = new xCardDeck();
        this.targetArray  = new CardDeck[4];
        this.workingArray = new CardStack[7];

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


    public Game(CardDeck GD, CardDeck GDUP, CardDeck[] TA, CardStack[] WA) {
        this.GameDeck     = GD;
        this.GameDeckUp   = GDUP;
        this.targetArray  = TA;
        this.workingArray = WA;
        this.commander    = new Commander();
    }


    public void workingToTarget(int workIndex,int targetIndex) {

        Card tmp = this.workingArray[workIndex].pop();
        boolean success = this.targetArray[targetIndex].put(tmp);

        // if cant put card on target
        if (!success) {
            this.workingArray[workIndex].putEmpty(tmp);
        }
        else {
        	//turn the top card on the working stack up
            if (!this.workingArray[workIndex].isEmpty()) {
                Card tmp2 = this.workingArray[workIndex].pop();
                tmp2.turnFaceUp();
                this.workingArray[workIndex].putEmpty(tmp2);
            }
            this.commander.cmd_do(Commander.enum_cmd.W_T); // TODO = remove this, wont be used propably
        }
    }


    public void targetToWorking(int targetIndex,int workIndex) {

        Card tmp = this.targetArray[targetIndex].pop();
        boolean success = this.workingArray[workIndex].put(tmp);

        //if cant put card on target
        if (!success) {
            this.targetArray[targetIndex].put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.T_W); // TODO = remove this, wont be used propably
        }
    }


    public void gameDeckUpToTarget(int targetIndex) {

        Card tmp = this.GameDeckUp.pop();
        boolean success = this.targetArray[targetIndex].put(tmp);

        //if cant put card on target
        if (!success) {
            this.GameDeckUp.put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.GU_T); // TODO = remove this, wont be used propably
        }
    }


    public void gameDeckUpToWorking(int workIndex) {

        Card tmp = this.GameDeckUp.pop();
        boolean success = this.workingArray[workIndex].put(tmp);

        //if cant put card on target
        if (!success) {
            this.GameDeckUp.put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.GU_W); // TODO = remove this, wont be used propably
        }
    }


    public void TargetToTarget(int targetIndex1,int targetIndex2) {

        Card tmp = this.targetArray[targetIndex1].pop();
        boolean success = this.targetArray[targetIndex2].put(tmp);

        //if cant put card on target
        if (!success) {
            this.targetArray[targetIndex1].put(tmp);
        }
        else {
            this.commander.cmd_do(Commander.enum_cmd.T_T); // TODO = remove this, wont be used propably
        }
    }


    public void WorkingToWorking(int workIndex1,int workIndex2,int number) {

        Card card = this.workingArray[workIndex1].get(number);

        if (!card.face()) return; // do nothing if the card we want to move from is facedown

        boolean success = this.workingArray[workIndex2].put(card);

        if (success) {
            this.commander.cmd_do(Commander.enum_cmd.W_W); // TODO = remove this, wont be used propably
            this.workingArray[workIndex2].pop();
            CardStack tmp = this.workingArray[workIndex1].pop(card);
            this.workingArray[workIndex2].put(tmp);
            if (!this.workingArray[workIndex1].isEmpty()) {
                Card tmp2 = this.workingArray[workIndex1].pop();
                tmp2.turnFaceUp();
                this.workingArray[workIndex1].putEmpty(tmp2);
            }
        }
    }


    public void deckToUp() {

        //if deck is empty
        if (this.GameDeck.isEmpty()) {
            int size=this.GameDeckUp.size();
            for (int i = 0; i<size;i++) {
                Card tmp2 = this.GameDeckUp.pop();
                tmp2.turnFaceDown();
                this.GameDeck.put(tmp2);
            }
            // here is missing commander.cmd_to() !!!!!!!!!!
            // TODO peter's: GE_GU, // gameDeckEMPTY => gameDeckUP
            // TODO place correctly: this.commander.cmd_do(Commander.enum_cmd.GE_GU);
        }
        else {
            Card tmp = this.GameDeck.pop();
            tmp.turnFaceUp();
            this.GameDeckUp.put(tmp);
            this.commander.cmd_do(Commander.enum_cmd.G_GU); // TODO = remove this, wont be used propably
        }
    }


    // for printing, TODO - remove it
    public String print_command() {
        return this.commander.cmd_undo().toString();
    }


}
