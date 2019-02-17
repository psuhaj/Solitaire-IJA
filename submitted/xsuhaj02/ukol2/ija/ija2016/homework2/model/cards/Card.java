package ija.ija2016.homework2.model.cards;

public interface Card {
    public Card.Color color();
    public int compareValue(Card c);
    public boolean isTurnedFaceUp();
    public boolean similarColorTo(Card c);
    public boolean turnFaceUp();
    public int value();


    public static enum Color
    {

        CLUBS, DIAMONDS, HEARTS, SPADES;


        @Override
        public String toString() {
            switch (this) {
                case CLUBS:
                    return "C";
                case DIAMONDS:
                    return "D";
                case HEARTS:
                    return "H";
                case SPADES:
                    return "S";
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

}
