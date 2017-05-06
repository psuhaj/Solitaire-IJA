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

    public Card.Color color();
    public int value();
    public boolean face();
    public boolean turnFaceUp();
    public boolean turnFaceDown();
    public int compareValue(Card c);
    public boolean similarColorTo(Card c);
    public String toString();
    public String code();
    public String print_log();
    public BufferedImage getCardImage();

}
