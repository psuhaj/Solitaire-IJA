package ija.ija2016.homework2.model.cards;
import java.util.Stack;

public class CardDeckClass implements CardDeck {

    protected int size;
    protected int actualSize;
    protected Stack<Card> stack;

    public CardDeckClass(){
    	
    }
    
    public CardDeckClass(int size){
        this.size=size;
        this.actualSize=0;
        this.stack = new Stack<Card>();
    }

    public static CardDeck createStandardDeck(){
        CardDeck standardDeck = new CardDeckClass(52);
        Card tmpCard;
        for(int i=1;i<=13;i++){
            tmpCard=new CardClass(Card.Color.CLUBS,i);
            standardDeck.put(tmpCard);
        }
        for(int i=1;i<=13;i++){
            tmpCard=new CardClass(Card.Color.DIAMONDS,i);
            standardDeck.put(tmpCard);
        }
        for(int i=1;i<=13;i++){
            tmpCard=new CardClass(Card.Color.HEARTS,i);
            standardDeck.put(tmpCard);
        }
        for(int i=1;i<=13;i++){
            tmpCard=new CardClass(Card.Color.SPADES,i);
            standardDeck.put(tmpCard);
        }
        return standardDeck;
    }


    public Card pop(){
        if (this.stack.empty()){
            return null;
        }
        else {
            Card top = this.stack.pop();
            this.actualSize--;
            return top;
        }
    }

    public boolean put(Card card){
        if(this.size == this.actualSize){
            return false;
        }
        else {
            this.stack.push(card);
            this.actualSize++;
            return true;
        }
    }

    public Card get(){
        if (this.stack.empty()){
            return null;
        }
        else {
            Card top = this.stack.peek();
            return top;
        }
    }

    public Card get(int index){
        Card c;
        Stack<Card> tmpDeck = new Stack<Card>();
        for(int i = 0;i<index;i++){
            tmpDeck.push(this.stack.pop());
        }
        c = tmpDeck.peek();
        for(int i = 0;i<index;i++){
            this.stack.push(tmpDeck.pop());
        }
        return c;
    }

    public boolean isEmpty(){
        if(this.actualSize==0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size(){
        return this.actualSize;
    }

}