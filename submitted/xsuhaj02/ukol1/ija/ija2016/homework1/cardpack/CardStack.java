package ija.ija2016.homework1.cardpack;
import java.util.Stack;
public class CardStack {

    private int size;
    private int actualSize;
    private Stack<Card> stack;

    public CardStack(int size){
        this.size=size;
        this.actualSize=0;
        this.stack = new Stack<Card>();
    }


    public boolean isEmpty(){
        return this.stack.empty();
    }


    public void put(Card card){
        if(this.size == this.actualSize){
            throw new IndexOutOfBoundsException("Card stack full!\n");
        }
        else {
            this.actualSize++;
            this.stack.push(card);
        }
    }


    public void put(CardStack stack){
        Stack<Card> tmpStack= new Stack<Card>();
        while(!stack.isEmpty()){
            tmpStack.push(stack.stack.pop());
        }
        while(!tmpStack.empty()){
            if(this.size == this.actualSize){
                throw new IndexOutOfBoundsException("Card stack full!\n");
            }
            else{
                this.put(tmpStack.pop());
                this.actualSize++;
            }
        }
    }

    public int size(){
        return this.actualSize;
    }

    public CardStack takeFrom(Card card){
        Stack<Card> tmpStack= new Stack<Card>();
        int position = this.stack.search(card);
        CardStack returnStack = new CardStack(position);
        if(position==-1){
            return null;
        }
        else{
            for(int i=0;i<position;i++){
                tmpStack.push(this.stack.pop());
                this.actualSize--;
            }

            for(int i=0;i<position;i++){
                returnStack.put(tmpStack.pop());
            }

        }
        return returnStack;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CardStack cardStack = (CardStack) object;

        if (size != cardStack.size) return false;
        if (actualSize != cardStack.actualSize) return false;
        if (stack != null ? !stack.equals(cardStack.stack) : cardStack.stack != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + size;
        result = 31 * result + actualSize;
        result = 31 * result + (stack != null ? stack.hashCode() : 0);
        return result;
    }


}