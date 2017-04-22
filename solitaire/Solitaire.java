/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solitaire;

import java.util.Collections;
import solitaire.model.board.*;
import solitaire.model.cards.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author adrian and peto
 */
public class Solitaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //create card factory
        AbstractFactorySolitaire factory = new FactoryKlondike();

        //Create deck for game
        CardDeck GameDeck = factory.createCardDeck();

        //for cards which are get from GameDeck
        CardDeck GameDeckUp = new xCardDeck();

        //create working stacks
        CardDeck[] targetArray = new CardDeck[4];
        CardStack[] workingArray = new CardStack[7];

        //create working stacks
        for(int i = 0; i<7;i++){
            workingArray[i] = factory.createWorkingPack();
            for(int j=0;j<=i;j++){
                workingArray[i].putEmpty(GameDeck.pop());
                //put face up
               if(i==j){
                    Card tmp=workingArray[i].pop();
                    tmp.turnFaceUp();
                    workingArray[i].putEmpty(tmp);
                }
            }
        }


        //create target packs
        for(int i = 0; i<4;i++){
            targetArray[i] = factory.createTargetPack();
        }


        print_game(workingArray, targetArray, GameDeck, GameDeckUp);
    }

    public static void  print_game(CardStack[] Working, CardDeck[] Target, CardDeck GD, CardDeck GDUP) {

        Card top_card;
        int size;
        String[] data = new String[13];
        String str;
        CardDeck deck;
        CardStack stack;

        // TARGET decks
        for(int c=0; c<4; c++) {

            deck = Target[c];
            str   = "TARGET  [" + String.valueOf(c+1) + "] = ";

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(X) ";
                }
                str = str + "(" + top_card.toString() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[c] = str;
        }

         // WORKING stacks
        for(int c=0; c<7; c++) {

            stack = Working[c];
            str   = "WORKING [" + String.valueOf(c+1) + "] = ";

            if (stack.isEmpty()==false) {
                size     = stack.size();
                top_card = stack.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(X) ";
                }
                str = str + "(" + top_card.toString() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[4+c] = str;
        }

        // GD, GDUP decks
        for(int c=0; c<2; c++) {

            if (c==0) {
                deck = GD;
                str   = "GD          = ";
            }
            else {
                deck = GDUP;
                str   = "GD-UP       = ";
            }

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(X) ";
                }
                str = str + "(" + top_card.toString() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[11+c] = str;
        }

        // print out data
        for(int c=0; c<13; c++) {
            if (c==4 || c==11) System.out.println();
            System.out.print(data[c]);
        }

    }

}
