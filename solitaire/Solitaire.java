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
 * @author adrian & peto
 */
public class Solitaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Hello, World");
        
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
        }
        
        
        
        //create target packs
        for(int i = 0; i<4;i++){
            targetArray[i] = factory.createWorkingPack();
        }
        
        
        
    }
    
}
