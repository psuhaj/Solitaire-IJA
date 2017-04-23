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
import java.util.Scanner;
import java.util.regex.*;
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
        }
        
        //add cards to working stacks
        for(int i=0;i<7;i++){
            for(int j=i;j<7;j++){
                workingArray[j].putEmpty(GameDeck.pop());
            }
        }
              
        //turn face up
        for(int i=0;i<7;i++){
            Card tmp=workingArray[i].pop();
            tmp.turnFaceUp();
            workingArray[i].putEmpty(tmp);
        }
        

        //create target packs
        for(int i = 0; i<4;i++){
            targetArray[i] = factory.createTargetPack();
        }

        Scanner scan = new Scanner(System.in);
        String str, val1, val2, val3, val4;
        int num1, num2, num3;
        boolean n1, n2, n3;
        n1=n2=n3=false;
        char c;
        Pattern regex = Pattern.compile("[\\s]*([ABCDEFG])(?:[\\s]+(\\d+))?(?:[\\s]+(\\d+))?(?:[\\s]+(\\d+))?[\\s]*");
        Matcher matcher;
        
        print_game_all(workingArray, targetArray, GameDeck, GameDeckUp);
        System.out.println("================================");

        while(scan.hasNextLine()) {
        	str = scan.nextLine();
        	matcher = regex.matcher(str);        	
        	if (matcher.matches()) {
	        	val1 = matcher.group(1); // A-G
	        	val2 = matcher.group(2); // number 1
	        	val3 = matcher.group(3); // number 2
	        	val4 = matcher.group(4); // number 3
	        	/*System.out.println();
	        	System.out.println(val1);
	        	System.out.println(val2);
	        	System.out.println(val3);
	        	System.out.println(val4);*/
	        	if (val2!=null) {
	        		num1 = Integer.parseInt(val2);
	        		n1 = true;
	        	}
	        	else num1=0;
	        	if (val3!=null) {
	        		num2 = Integer.parseInt(val3);
	        		n2 = true;
	        	}
	        	else num2=0;
	        	if (val4!=null) {
	        		num3 = Integer.parseInt(val4);
	        		n3 = true;
	        	}
	        	else num3=0;
	        	c = val1.charAt(0);
	        	switch (c) {
	        		case 'A':
	        			if (n1 && n2) workingToTarget(workingArray[num1], targetArray[num2]);
	        			else System.out.println("workingToTarget(workingArray[num1], targetArray[num2])");
	        			break;
	        		case 'B':
	        			if (n1 && n2) targetToWorking(workingArray[num1], targetArray[num2]);
	        			else System.out.println("targetToWorking(workingArray[num1], targetArray[num2])");
	        			break;
	        		case 'C':
	        			if (n1) gameDeckUpToTarget(GameDeckUp, targetArray[num1]);
	        			else System.out.println("gameDeckUpToTarget(GameDeckUp, targetArray[num1])");
	        			break;
	        		case 'D':
	        			if (n1) gameDeckUpToWorking(workingArray[num1], GameDeckUp);
	        			else System.out.println("gameDeckUpToWorking(workingArray[num1], GameDeckUp)");
	        			break;
	        		case 'E':
	        			if (n1 && n2) TargetToTarget(targetArray[num1], targetArray[num2]);
	        			else System.out.println("TargetToTarget(targetArray[num1], targetArray[num2])");
	        			break;
	        		case 'F':
	        			if (n1 && n2 && n3) WorkingToWorking(workingArray[num1], workingArray[num2], num3);
	        			else System.out.println("WorkingToWorking(workingArray[num1], workingArray[num2], num3)");
	        			break;
	        		default: // G
	        			deckToUp(GameDeck, GameDeckUp);
	        			break;
	        	}
		    }
		    else {
		        System.out.println("Wrong input repeat again");
		    }
		    System.out.println("================================");
		    print_game_all(workingArray, targetArray, GameDeck, GameDeckUp);
		    System.out.println("================================");
    	}

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
            str   = "TARGET[" + String.valueOf(c) + "] {"+String.valueOf(deck.size())+"}\t= ";

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
            str   = "WORKING[" + String.valueOf(c) + "] {"+String.valueOf(stack.size())+"}\t= ";

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
                str   = "GD    {"+String.valueOf(deck.size())+"}\t= ";
            }
            else {
                deck = GDUP;
                str   = "GD-UP {"+String.valueOf(deck.size())+"}\t= ";
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


    public static void  print_game_all(CardStack[] Working, CardDeck[] Target, CardDeck GD, CardDeck GDUP) {



        Card top_card;
        int size;
        String[] data = new String[13];
        String str, tmp;
        CardDeck deck;
        CardStack stack;



        // TARGET decks ==============================================================================
        for(int c=0; c<4; c++) {

            deck = Target[c];
            str   = "TARGET[" + String.valueOf(c) + "] {"+String.valueOf(deck.size())+"}\t= ";

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size; i++) {
                	tmp = deck.get(i).print_log();
                	if (tmp.charAt(tmp.length()-2) == 'U') {
                		str = str + "(" + top_card.print_log() + ")";
                	}
                	else str = str + "(X) ";
                }
            }
            else {
                str = str + "empty";
            }
            data[c] = str;
            str = "";
        }




         // WORKING stacks ==============================================================================
        for(int c=0; c<7; c++) {

            stack = Working[c];
            str   = "WORKING[" + String.valueOf(c) + "] {"+String.valueOf(stack.size())+"}\t= ";

            if (stack.isEmpty()==false) {
                size     = stack.size();
                top_card = stack.get();
                for(int i=0; i<size; i++) {
                	tmp = stack.get(i).print_log();
                	if (tmp.charAt(tmp.length()-2) == 'U') {
                		str = str + "(" + top_card.print_log() + ")";
                	}
                	else str = str + "(X) ";
                }
            }
            else {
                str = str + "empty";
            }

            data[4+c] = str;
            str = "";
        }




        // GD deck ========================================================================================
        deck = GD;
        str   = "GD    {"+String.valueOf(deck.size())+"}\t= ";
        if (deck.isEmpty()==false) {
            size     = deck.size();
            top_card = deck.get();
            for(int i=0; i<size; i++) {
            	str = str + "(X) ";
            }
    		str = str + "(" + top_card.print_log() + ")";
        }
        else {
            str = str + "empty";
        }
        data[11] = str;
        str = "";





        // GDUP decks ========================================================================================
        deck = GDUP;
        str   = "GD-UP {"+String.valueOf(deck.size())+"}\t= ";
        if (deck.isEmpty()==false) {
            size     = deck.size();
            top_card = deck.get();
            for(int i=0; i<size-1; i++) {
                str = str + "(" + deck.get(i).print_log() + ") ";
            }
            tmp = top_card.print_log();
            if (tmp.charAt(tmp.length()-2)== 'U') {
            	str = str + "(" + top_card.print_log() + ")";
            }
            else str = str + "(X) ";
        }
        else {
            str = str + "empty";
        }
        data[12] = str;
        str = "";





        // print out data =====================================================================================
        for(int c=0; c<13; c++) {
            if (c==4 || c==11) System.out.println();
            System.out.println(data[c]);
        }

    }
    
    //methods for moving cards
    public static void workingToTarget(CardStack working,CardDeck target){
        Card tmp = working.pop();
        boolean success = target.put(tmp);
        //if cant put card on target
        if(!success){
            working.putEmpty(tmp);
        }
    }
    
    public static void targetToWorking(CardStack working,CardDeck target){
        Card tmp = target.pop();
        boolean success = working.put(tmp);
        //if cant put card on target
        if(!success){
            target.put(tmp);
        }
    }

    public static void gameDeckUpToTarget(CardDeck up,CardDeck target){
        Card tmp = up.pop();
        boolean success = target.put(tmp);
        //if cant put card on target
        if(!success){
            up.put(tmp);
        }
    }
    
    public static void gameDeckUpToWorking(CardStack working,CardDeck up){
        Card tmp = up.pop();
        boolean success = working.put(tmp);
        //if cant put card on target
        if(!success){
            working.putEmpty(tmp);
        }
    }
    
    public static void TargetToTarget(CardDeck target1,CardDeck target2){
        Card tmp = target1.pop();
        boolean success = target2.put(tmp);
        //if cant put card on target
        if(!success){
            target1.put(tmp);
        }
    }
    
    public static void WorkingToWorking(CardStack working1,CardStack working2,int number){
        /*CardStack tmp = working1.pop(card);
        boolean success = working2.put(tmp);
        //if cant put card on target
        if(!success){
            working1.put(tmp);
        }*/
    }
    
    public static void deckToUp(CardDeck gameDeck,CardDeck up){
        //if deck is empty
        if(gameDeck.isEmpty()){
            for(int i = 0; i<up.size();i++){
                Card tmp2 = up.pop();
                tmp2.turnFaceDown();
                gameDeck.put(tmp2);
            }
        }
        else{
            Card tmp = gameDeck.pop();
            tmp.turnFaceUp();
            up.put(tmp);
        }
        
    }
}
