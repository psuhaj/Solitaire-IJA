package ija.ija2016.homework2.model.cards;
import java.util.Stack;

public class CardStackClass extends CardDeckClass implements CardStack{

	public CardStackClass(){
    }
	
    public CardStackClass(int size){
        this.size=size;
        this.actualSize=0;
        this.stack = new Stack<Card>();
    }


    public boolean isEmpty(){
        return this.stack.empty();
    }

    @Override
    public boolean put(Card card){
        if(this.size == this.actualSize){
            return false;
        }
        //ak je zasobnik prazdny
        if(this.isEmpty()){
        	if(card.value() != 13) {
        		return false;
        	}
        	else{
        		this.actualSize++;
                this.stack.push(card);
                return true;
        	}
        }
        //akk zasobnik neni prazdny treba skontrolovat farbu a value
        else {
        	if(card.similarColorTo(this.stack.peek())){
        		return false;
        	}
        	if(card.compareValue(this.stack.peek()) != -1){
        		return false;
        	}
        	this.actualSize++;
            this.stack.push(card);
            return true;   
        }
    }


    public boolean put(CardStack stack){
        Stack<Card> tmpStack= new Stack<Card>();
        CardStackClass stack2 = (CardStackClass) stack;
        if(this.stack.isEmpty()){
        	if(stack2.stack.peek().value() == 13){
        		while(!stack2.isEmpty()){
                    tmpStack.push(stack2.stack.pop());
                }
                while(!tmpStack.empty()){
                    if(this.size == this.actualSize){
                        return false;
                    }
                    else{
                        this.stack.push(tmpStack.pop());
                        this.actualSize++;
                    }
                }
                return true;
        	}
        	else{
        		return false;
        	}
        }
        else{
        	int cmp = stack2.stack.get(0).compareValue(this.stack.peek());
        	if(cmp != -1){
        		return false;
        	}
        	if(stack2.stack.get(0).similarColorTo(this.stack.peek())){
        		return false;
        	}
        	 while(!stack2.isEmpty()){
                 tmpStack.push(stack2.stack.pop());
             }
             while(!tmpStack.empty()){
                 if(this.size == this.actualSize){
                     return false;
                 }
                 else{
                	 this.stack.push(tmpStack.pop());
                     this.actualSize++;
                 }
             }
             return true;
        }
    }


    public int size(){
        return this.actualSize;
    }

    public CardStack pop(Card card){
        Stack<Card> tmpStack= new Stack<Card>();
        int position = this.stack.search(card);
        CardStackClass returnStack = new CardStackClass(position);
        if(position==-1){
            return null;
        }
        else{
            for(int i=0;i<position;i++){
                tmpStack.push(this.stack.pop());
                this.actualSize--;
                returnStack.actualSize++;
            }

            for(int i=0;i<position;i++){
                returnStack.stack.push(tmpStack.pop());
            }

        }
        return returnStack;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CardStackClass cardStack = (CardStackClass) object;

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