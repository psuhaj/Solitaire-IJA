package solitaire.model.cards;

import java.awt.image.BufferedImage;

/**
 * Interface for card.
 */
public interface Card {

    /**
     * Card's colors.
     */
    public static enum Color {
        CLUBS, DIAMONDS, HEARTS, SPADES;

        @Override
        public String toString() {
            switch(this) {
                case CLUBS    : return "C";
                case DIAMONDS : return "D";
                case HEARTS   : return "H";
                default       : return "S";
            }
        }
    };

    /**
     * Obtain card's color.
     *
     * @return     Card's color.
     */
    public Card.Color color();

    /**
     * Obtain card's value.
     *
     * @return     Card's value.
     */
    public int value();

    /**
     * Obtain card's face.
     *
     * @return     Card's face.
     */
    public boolean face();

    /**
     * Turn card's face to face-up.
     *
     * @return     True if the face was turned up, false otherwise.
     */
    public boolean turnFaceUp();

    /**
     * Turn card's face to face-down.
     *
     * @return     True if the face was turned down, false otherwise.
     */
    public boolean turnFaceDown();

    /**
     * Compares two cards based on their value
     *
     * @param      c     Comparing card.
     *
     * @return     Returns the value difference of cards.
     */
    public int compareValue(Card c);

    /**
     * Function compares two cards based on their color
     *
     * @param      c     Comparing card.
     *
     * @return     Returns the boolean value if the cards have the same color.
     */
    public boolean similarColorTo(Card c);

    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString();

    /**
     * Function codes the card into three characters.
     *
     * @return     Code of card depending on card's value, color and face.
     */
    public String code();

    /**
     * Returns a string representation of card as debug information.
     *
     * @return     String representation of the object for debug.
     */
    public String print_log();

    /**
     * Card's image.
     *
     * @return     Card's image.
     */
    public BufferedImage getCardImage();

}
