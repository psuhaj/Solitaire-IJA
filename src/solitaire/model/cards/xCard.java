package solitaire.model.cards;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class for card.
 */
public class xCard implements Card {

    // Card's atributes
    private Card.Color color;
    private int value;
    private boolean face;
    private BufferedImage image;

    /**
     * Constructs the card.
     *
     * @param      color  The card's color.
     * @param      value  The card's value.
     * @param      image  The card's image.
     */
    public xCard(Card.Color color, int value, BufferedImage image) {
        this.color = color;
        this.value = value;
        this.face  = false; // true = faceUP, false = faceDOWN
        this.image = image;
    }

    /**
     * Constructs the card from it's code.
     *
     * @param      code  The code.
     */
    public xCard(String code) {
        String imgColor;
        switch (code.charAt(0)) {
            case 'A' :
                this.value = 1;
                break;
            case 'J' :
                this.value = 11;
                break;
            case 'Q' :
                this.value = 12;
                break;
            case 'K' :
                this.value = 13;
                break;
            case 'T' :
                this.value = 10;
                break;
            default :
                this.value = Character.getNumericValue(code.charAt(0));
                break;
        }
        switch (code.charAt(1)) {
            case 'H': {
                this.color = Card.Color.HEARTS;
                imgColor = "hearts";
                break;
                }
            case 'S':
                this.color = Card.Color.SPADES;
                imgColor = "spades";
                break;
            case 'D':
                this.color = Card.Color.DIAMONDS;
                imgColor = "diamonds";
                break;
            default:
                this.color = Card.Color.CLUBS;
                imgColor = "clubs";
                break;
        }
        switch (code.charAt(2)) {
            case 'U': {
                this.face = true;
                break;
                }
            default:
                this.face = false;
                break;
        }
        try{
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/cards/"+this.value+"_of_"+imgColor+".png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the card's color.
     *
     * @return     Card's color.
     */
    @Override
    public Card.Color color() {
        return this.color;
    }

    /**
     * Gets the card's image.
     *
     * @return     The card's image.
     */
    public BufferedImage getCardImage(){
        return this.image;
    }

    /**
     * Gets the card's value.
     *
     * @return     Card's value.
     */
    @Override
    public int value() {
        return this.value;
    }

    /**
     * Gets the card's face.
     *
     * @return     Card's face.
     */
    @Override
    public boolean face() {
        return this.face;
    }

    /**
     * Turns card up.
     *
     * @return     True on success of action turning face up, false if it cannot be provided.
     */
    @Override
    public boolean turnFaceUp() {
        if (this.face == false) {
            this.face = true;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Turns card down.
     *
     * @return     True on success of action turning face down, false if it cannot be provided.
     */
    public boolean turnFaceDown() {
        if (this.face == true) {
            this.face = false;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Compares two cards based on their value.
     *
     * @param      c     Comparing card.
     *
     * @return     Returns the value difference of cards.
     */
    @Override
    public int compareValue(Card c) {
        xCard xc = (xCard) c;
        return this.value() - xc.value();
    }

    /**
     * Function compares two cards based on their color
     *
     * @param      c     Comparing card.
     *
     * @return     Returns the boolean value if the cards have the same color.
     */
    @Override
    public boolean similarColorTo(Card c) {
        xCard xc = (xCard) c;
        if ( (this.color() == Color.HEARTS || this.color() == Color.DIAMONDS) && (xc.color() == Color.HEARTS || xc.color() == Color.DIAMONDS) ) { // both are RED
            return true;
        }
        else if ( (this.color() == Color.CLUBS || this.color() == Color.SPADES) && (xc.color() == Color.CLUBS || xc.color() == Color.SPADES) ) { // both are BLACK
            return true;
        }
        else { // one is RED one is BLACK
            return false;
        }
    }

    /**
     * Returns a string representation of card as debug information.
     *
     * @return     String representation of the object for debug.
     */
    public String print_log() {
        String str;
        switch (this.value) {
            case 1 :
                str = " A";
                break;
            case 11 :
                str = " J";
                break;
            case 12 :
                str = " Q";
                break;
            case 13 :
                str = " K";
                break;
            default :
                if (this.value != 10 ) str = " "+String.valueOf(this.value);
                else str = String.valueOf(this.value);
                break;
        }
        switch (this.color) {
            case HEARTS: {
                str = str+"_Heart__";
                break;
                }
            case SPADES:
                str = str+"_Spade__";
                break;
            case DIAMONDS:
                str = str+"_Diamond";
                break;
            default:
                str = str+"_Club___";
                break;
        }
        if (face) {
            str = str+"_U ";
        }
        else {
            str = str+"__ ";
        }
        return str;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    @Override
    public String toString(){
        String str;
        switch (this.value) {
            case 1 :
                str = "A";
                break;
            case 11 :
                str = "J";
                break;
            case 12 :
                str = "Q";
                break;
            case 13 :
                str = "K";
                break;
            default :
                str = String.valueOf(this.value);
                break;
        }
        switch (this.color) {
            case HEARTS: {
                str = str+"_Heart";
                break;
                }
            case SPADES:
                str = str+"_Spade";
                break;
            case DIAMONDS:
                str = str+"_Diamond";
                break;
            default:
                str = str+"_Club";
                break;
        }
        if (face) {
            str = str+"_UP";
        }
        else {
            str = str+"_DOWN";
        }
        return str;
    }

    /**
     * Function codes the card into three characters.
     *
     * @return     Code of card depending on card's value, color and face.
     */
    @Override
    public String code(){
        String str;
        switch (this.value) {
            case 1 :
                str = "A";
                break;
            case 10 :
                str = "T"; // TEN
                break;
            case 11 :
                str = "J";
                break;
            case 12 :
                str = "Q";
                break;
            case 13 :
                str = "K";
                break;
            default :
                str = String.valueOf(this.value);
                break;
        }
        switch (this.color) {
            case HEARTS: {
                str = str+"H";
                break;
                }
            case SPADES:
                str = str+"S";
                break;
            case DIAMONDS:
                str = str+"D";
                break;
            default:
                str = str+"C";
                break;
        }
        if (face) {
            str = str+"U";
        }
        else {
            str = str+"D";
        }
        return str;
    }

}
