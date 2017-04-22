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
        
        
        
        
    }
    
}
