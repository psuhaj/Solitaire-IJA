package ija.ija2016.homework1.cardpack;


public class Card {

    private int value;
    private Card.Color color;

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


    public Card(Card.Color c, int value) {//konstruktor
        this.color = c;
        this.value = value;
    }


    public Card.Color color() {
        return this.color;
    }

    public int value() {
        return this.value;
    }


    //convert card to string from its' value and color
    public String toString() {
        String tmp = "";
        switch (this.value) {//case for ace,jack,queen,king
            case 1:
                tmp += "A";
                break;
            case 11:
                tmp += "J";
                break;
            case 12:
                tmp += "Q";
                break;
            case 13:
                tmp += "K";
                break;
            default:
                tmp += Integer.toString(this.value);
                break;
        }

        tmp += "(" + this.color.toString() + ")";

        return tmp;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Card card = (Card) object;

        if (value != card.value) return false;
        if (color != null ? !color.equals(card.color) : card.color != null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result=1;
        result = 31 * result + value;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}