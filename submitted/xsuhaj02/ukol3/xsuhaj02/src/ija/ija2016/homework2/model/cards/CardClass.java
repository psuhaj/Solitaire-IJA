package ija.ija2016.homework2.model.cards;

public class CardClass implements Card {

    private int value;
    private Card.Color color;
    private boolean face;


    public CardClass(Card.Color c, int value) {//konstruktor
        this.color = c;
        this.value = value;
        this.face=false;
    }


    public Card.Color color() {
        return this.color;
    }

    public int value() {
        return this.value;
    }

    public int compareValue(Card c){
        CardClass tmpCard = (CardClass)c;
        int value = this.value - tmpCard.value;
        return value;
    }

    public boolean isTurnedFaceUp(){
        return this.face;
    }

    public boolean similarColorTo(Card c){
        CardClass tmpCard = (CardClass)c;
        if(tmpCard.color==this.color || tmpCard.color==Card.Color.HEARTS && this.color == Card.Color.DIAMONDS || tmpCard.color==Card.Color.DIAMONDS && this.color == Card.Color.HEARTS ||
                tmpCard.color==Card.Color.CLUBS && this.color == Card.Color.SPADES || tmpCard.color==Card.Color.SPADES && this.color == Card.Color.CLUBS){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean turnFaceUp(){
        if(this.face==false){
            this.face=true;
            return true;
        }
        else{
            return false;
        }

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

        CardClass card = (CardClass) object;

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
