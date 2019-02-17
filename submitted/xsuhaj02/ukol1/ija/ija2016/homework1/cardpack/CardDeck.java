package ija.ija2016.homework1.cardpack;
import java.util.Stack;

public class CardDeck{

    private int size;
    private int actualSize;
    private Stack<Card> deck;

    public CardDeck(int size){
        this.size=size;
        this.actualSize=0;
        this.deck = new Stack<Card>();
    }

    public static CardDeck createStandardDeck(){
        CardDeck standardDeck = new CardDeck(52);
        Card tmpCard;
        for(int i=1;i<=13;i++){
            tmpCard=new Card(Card.Color.CLUBS,i);
            standardDeck.put(tmpCard);
        }
        for(int i=1;i<=13;i++){
            tmpCard=new Card(Card.Color.DIAMONDS,i);
            standardDeck.put(tmpCard);
        }
        for(int i=1;i<=13;i++){
            tmpCard=new Card(Card.Color.HEARTS,i);
            standardDeck.put(tmpCard);
        }
        for(int i=1;i<=13;i++){
            tmpCard=new Card(Card.Color.SPADES,i);
            standardDeck.put(tmpCard);
        }
        return standardDeck;
    }


    public Card pop(){
        if (this.deck.empty()){
            return null;
        }
        else {
            Card top = this.deck.pop();
            this.actualSize--;
            return top;
        }
    }

    public void put(Card card){
        if(this.size == this.actualSize){
            throw new IndexOutOfBoundsException("Card deck full!\n");
        }
        else {
            this.deck.push(card);
            this.actualSize++;
        }
    }

    public int size(){
        return this.actualSize;
    }

}