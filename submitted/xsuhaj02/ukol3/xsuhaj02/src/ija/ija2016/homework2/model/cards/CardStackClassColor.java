package ija.ija2016.homework2.model.cards;

import java.util.Stack;

public class CardStackClassColor extends CardDeckClass{

	private Card.Color color;
	
	public CardStackClassColor (int size, Card.Color color){
        this.size=size;
        this.actualSize=0;
        this.stack = new Stack<Card>();
        this.color = color;
    }

	public boolean put(Card card){
		if(this.isEmpty()){
			if(card.value() != 1){
				return false;
			}
			else{
				if(this.color != card.color()){
					return false;
				}
				this.stack.push(card);
				this.actualSize++;
				return true;
			}
		}
		else{
			if(this.color != card.color()){
				return false;
			}
			if(this.stack.peek().compareValue(card) != -1){
				return false;
			}
			this.stack.push(card);
			this.actualSize++;
			return true;
			
		}
	}

}
