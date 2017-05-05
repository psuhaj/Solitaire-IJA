package solitaire;

import java.io.*;
import java.util.Collections;
import solitaire.model.game.*;

import java.util.Scanner; // for printing, TODO - remove it
import java.util.regex.*; // for printing, TODO - remove it
import solitaire.model.board.*; // for printing, TODO - remove it
import solitaire.model.cards.*; // for printing, TODO - remove it

public class Solitaire {

    public static void print(Object... args){for(Object o: args)System.out.print(o);}
    public static void echo(Object... args){for(Object o: args)System.out.print(o);System.out.println();}

    static Game GAME1;
    //static Game GAME2;
    //static Game GAME3;
    //static Game GAME4;

    public static void main(String[] args) {

        GAME1 = new Game();
        //GAME2 = new Game();
        //GAME3 = new Game();
        //GAME4 = new Game();

        run(GAME1);
        //write_load_game_test(GAME1);

    }

    public static void run(Game GAME1) {
        Scanner scan = new Scanner(System.in);

        String str, val1, val2, val3, val4;
        int num1, num2, num3;
        boolean n1, n2, n3, flag;
        char c;

        Pattern regex = Pattern.compile("[\\s]*([abcdefghu])(?:[\\s]+(\\d+))?(?:[\\s]+(\\d+))?(?:[\\s]+(\\d+))?[\\s]*");
        Matcher matcher;

        print_game_all(GAME1.workingArray, GAME1.targetArray, GAME1.GameDeck, GAME1.GameDeckUp);
        System.out.println("================================");

        while(scan.hasNextLine()) {
            n1=n2=n3=false;
            str = scan.nextLine();
            matcher = regex.matcher(str);
            if (matcher.matches()) {
                val1 = matcher.group(1); // A-G
                val2 = matcher.group(2); // number 1
                val3 = matcher.group(3); // number 2
                val4 = matcher.group(4); // number 3
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
                flag=true;
                switch (c) {
                    case 'a':
                        if (n1 && n2 && !n3) GAME1.workingToTarget(num1, num2);
                        else {
                            System.out.println("ERROR: Please enter: num1 & num2. [[ GAME1.workingToTarget(num1, num2) ]]");
                            flag=false;
                        }
                        break;
                    case 'b':
                        if (n1 && n2 && !n3) GAME1.targetToWorking(num1, num2);
                        else {
                            System.out.println("ERROR: Please enter: num1 & num2. [[ GAME1.targetToWorking(num1, num2) ]]");
                            flag=false;
                        }
                        break;
                    case 'c':
                        if (n1 && !n2 && !n3) GAME1.gameDeckUpToTarget(num1);
                        else {
                            System.out.println("ERROR: Please enter: num1. [[ GAME1.gameDeckUpToTarget(num1) ]]");
                            flag=false;
                        }
                        break;
                    case 'd':
                        if (n1 && !n2 && !n3) GAME1.gameDeckUpToWorking(num1);
                        else {
                            System.out.println("ERROR: Please enter: num1. [[ GAME1.gameDeckUpToWorking(num1) ]]");
                            flag=false;
                        }
                        break;
                    case 'e':
                        if (n1 && n2 && !n3) GAME1.TargetToTarget(num1, num2);
                        else {
                            System.out.println("ERROR: Please enter: num1 & num2. [[ GAME1.TargetToTarget(num1, num2) ]]");
                            flag=false;
                        }
                        break;
                    case 'f':
                        if (n1 && n2 && n3) GAME1.WorkingToWorking(num1, num2, GAME1.workingArray[num1].get(num3));
                        else {
                            System.out.println("ERROR: Please enter: num1 & num2 & num3. [[ GAME1.WorkingToWorking(num1, num2, num3) ]]");
                            flag=false;
                        }
                        break;
                    case 'g':
                        if (!n1 && !n2 && !n3) GAME1.deckToUp();
                        else {
                            System.out.println("ERROR: Please do not enter numbers. [[ GAME1.deckToUp() ]]");
                            flag=false;
                        }
                        break;
                    case 'u':
                        if (!n1 && !n2 && !n3) GAME1.undo();
                        else {
                            System.out.println("ERROR: Please do not enter numbers. [[ GAME1.undo() ]]");
                            flag=false;
                        }
                        break;
                    default:
                            echo("----------------------------------------------------------");
                            echo("W -> T : a n n   : game.workingToTarget(num1, num2);");
                            echo("T -> W : b n n   : game.targetToWorking(num1, num2);");
                            echo("G -> T : c n     : game.gameDeckUpToTarget(num1);");
                            echo("G -> W : d n     : game.gameDeckUpToWorking(num1);");
                            echo("T -> T : e n n   : game.TargetToTarget(num1, num2);");
                            echo("W -> W : f n n n : game.WorkingToWorking(num1, num2, num3);");
                            echo("flip   : g       : game.deckToUp();");
                            echo("undo   : u       : game.undo();");
                            echo("----------------------------------------------------------");
                            flag=false;
                        break;
                }
                if (flag) {
                    System.out.println("================================");
                    print_game_all(GAME1.workingArray, GAME1.targetArray, GAME1.GameDeck, GAME1.GameDeckUp);
                    System.out.println("================================");
                }
            }
            else {
                System.out.println("Wrong input, please repeat again.");
            }

        }
    }

    public static void write_load_game_test(Game GAME1) {

        echo();

        //echo("=================================================================");
        //print_game_all(GAME1.workingArray, GAME1.targetArray, GAME1.GameDeck, GAME1.GameDeckUp);
        //echo("=================================================================");

        //String path = "/home/suhaj/Dokumenty/IJA/Git solitaire/Solitaire-IJA/";
        String path = "/home/adrian/Documents/Data/IJA/Solitaire-IJA/";
        String name = "gamex.solitiare";

        //echo(path+name);
        //echo("=================================================================");

        //int retval;
        //retval = write_game(path+name,GAME1);
        //if (retval != 0 ) {
        //    echo("TODO!!! write_game returned 1"); // TODO error, could not write game to file !
        //}

        Game loaded = null;
        loaded = load_game(path+name);
        if (loaded != null) print_game_all(loaded.workingArray, loaded.targetArray, loaded.GameDeck, loaded.GameDeckUp);
        else echo("\nload_game() returned NULL");

        echo("\n\nWARNING LOG!\nPATH ="+path+name);

    }

    public static int write_game(String path, Game game) {

        // create array of empty strings
        String[] data = new String[13];
        for(int i=0; i<13; i++) data[i]="";

        // fill the array with data
        for(int idx=0; idx<13; idx++) {
            if (idx == 0) { // GD
                if (game.GameDeck.isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.GameDeck.size(); i++) {
                        data[idx] += game.GameDeck.get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
            else if (idx == 1) { // GDUP
                if (game.GameDeckUp.isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.GameDeckUp.size(); i++) {
                        data[idx] += game.GameDeckUp.get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
            else if (1 < idx && idx <= 5) { // target[]
                if (game.targetArray[idx-2].isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.targetArray[idx-2].size(); i++) {
                        data[idx] += game.targetArray[idx-2].get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
            else { // working[]
                if (game.workingArray[idx-6].isEmpty()) {
                    data[idx]="$\n";
                }
                else {
                    for(int i=0; i<game.workingArray[idx-6].size(); i++) {
                        data[idx] += game.workingArray[idx-6].get(i).code()+" ";
                    }
                    data[idx] += "\n";
                }
            }
        }

        // write the data into file
        try{
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            for(int idx=0; idx<13; idx++) writer.print(data[idx]);
            writer.close();
        } catch (IOException e) {
            return 1;
        }

        return 0;
    }

    public static Game load_game(String path) {

        // READ FILE TO STRING (data[i]=one line from file)
        String[] data = new String[13];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                data[i]=line;
                i++;
            }
        } catch (IOException e) {
            return null;
        }

        // INITIALIZATION OF GD, GDUP, TA[], WA[]
        String[] cards;
        Card card;
        AbstractFactorySolitaire factory = new FactoryKlondike();//factory for creating decks and stacks

        CardDeck    GD   = new xCardDeck();
        CardDeck    GDUP = new xCardDeck();
        CardDeck[]  TA   = new CardDeck[4];
        CardStack[] WA   = new CardStack[7];

        for(int i = 0; i<7;i++){
            WA[i] = factory.createWorkingPack();
        }

        for(int i = 0; i<4;i++){
            TA[i] = factory.createTargetPack();
        }

        // FILLING GD, GDUP, TA[], WA[] WITH DATA
        for(int idx=0; idx<13; idx++) {
            if (idx == 0) { // GD
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(String code: cards) {
                        card = new xCard(code);
                        GD.put(card); // put card to GD
                    }
                }

            }
            else if (idx == 1) { // GDUP
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(String code: cards) {
                        card = new xCard(code);
                        GDUP.put(card); // put card to GDUP
                    }
                }

            }
            else if (1 < idx && idx < 6) { // target[]
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(String code: cards) {
                        card = new xCard(code);
                        TA[idx-2].put(card); // put card to TA[0-3]
                    }
                }

            }
            else { // working[]
                if (data[idx].equals("$")) {
                    continue;
                }
                else {
                    cards = data[idx].split(" ");
                    for(int i=0; i<cards.length; i++) {
                        card = new xCard(cards[i]);
                        WA[idx-6].putEmpty(card); // put card to WA[0-6]
                    }
                }

            }
        }

        Game g = new Game(GD, GDUP, TA, WA);
        return g;
    }

//##########################################################################################################
// BEGIN OF PRINTING FUNCTIONS
//##########################################################################################################
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
                        str = str + "(" + tmp + ") ";
                    }
                    else str = str + "(X) ";
                    tmp = "";
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
                        str = str + "(" + tmp + ") ";
                    }
                    else str = str + "(X) ";
                    tmp = "";
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
            for(int i=0; i<size-1; i++) {
                str = str + "(X) ";
            }
            str = str + "(" + top_card.print_log() + ") ";
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
            for(int i=0; i<size; i++) {
               str = str + "(" + deck.get(i).print_log() + ") ";
            }
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

//===========================================================================
    public static void  print_game_all_hidden(CardStack[] Working, CardDeck[] Target, CardDeck GD, CardDeck GDUP) {

        Card top_card;
        int size;
        String[] data = new String[13];
        String str;
        CardDeck deck;
        CardStack stack;

        // TARGET decks
        for(int c=0; c<4; c++) {

            deck = Target[c];
            str   = "TARGET[" + String.valueOf(c+1) + "] {"+String.valueOf(deck.size())+"}\t= ";

            if (deck.isEmpty()==false) {
                size     = deck.size();
                top_card = deck.get();
                for(int i=0; i<size-1; i++) {
                   str = str + "(" + (deck.get(i)).print_log() + ") ";
                }
                str = str + "(" + top_card.print_log() + ")\n";
            }
            else {
                str = str + "empty\n";
            }

            data[c] = str;
        }

         // WORKING stacks
        for(int c=0; c<7; c++) {

            stack = Working[c];
            str   = "WORKING[" + String.valueOf(c+1) + "] {"+String.valueOf(stack.size())+"}\t= ";

            if (stack.isEmpty()==false) {
                size     = stack.size();
                top_card = stack.get();
                for(int i=0; i<size-1; i++) {
                    str = str + "(" + stack.get(i).print_log() + ") ";
                }
                str = str + "(" + top_card.print_log() + ")\n";
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
                    str = str + "(" + deck.get(i).print_log() + ") ";
                }
                str = str + "(" + top_card.print_log() + ")\n";
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
//##########################################################################################################
// END OF PRINTING FUNCTIONS
//##########################################################################################################

}
