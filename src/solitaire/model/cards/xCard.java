package solitaire.model.cards;

import java.awt.image.BufferedImage;

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
     * Constructs the object card.
     *
     * @param      c      Card's color.
     * @param      value  Card's value.
     * @param      image  Card's image.
     */
    public xCard(Card.Color c, int value, BufferedImage image) {
        this.color = c;
        this.value = value;
        this.face  = false; // (true = faceUP),(false = faceDOWN)
        this.image = image;
    }

    /**
     * Constructs the object.
     *
     * @param      code  Card's code in three characters.
     */
    public xCard(String code) {
        switch (code.charAt(0)) { // first character including value
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
        switch (code.charAt(1)) { // second character including color
            case 'H': {
                this.color = Card.Color.HEARTS;
                break;
                }
            case 'S':
                this.color = Card.Color.SPADES;
                break;
            case 'D':
                this.color = Card.Color.DIAMONDS;
                break;
            default:
                this.color = Card.Color.CLUBS;
                break;
        }
        switch (code.charAt(2)) { // third character including face
            case 'U': {
                this.face = true;
                break;
                }
            default:
                this.face = false;
                break;
        }
    }

    /**
     * Card's color.
     *
     * @return     Card's color.
     */
    @Override
    public Card.Color color() {
        return this.color;
    }

    /**
     * Card's image.
     *
     * @return     Card's image.
     */
    @Override
    public BufferedImage getCardImage(){
        return this.image;
    }

    /**
     * Card's value.
     *
     * @return     Card's value.
     */
    @Override
    public int value() {
        return this.value;
    }

    /**
     * Card's face.
     *
     * @return     Card's face.
     */
    @Override
    public boolean face() {
        return this.face;
    }

    /**
     * Function turns face up if the card's face was down.
     *
     * @return     If the card's face was turned up returns true else false.
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
     * Function turns face down if the card's face was up.
     *
     * @return     If the card's face was turned down returns true else false.
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
     * Function compares two cards based on their value.
     *
     * @param      c     Comparing card.
     *
     * @return     Returns the value difference of cards with the same color.
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
