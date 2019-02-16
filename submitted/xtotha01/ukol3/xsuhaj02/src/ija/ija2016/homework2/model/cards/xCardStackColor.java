package ija.ija2016.homework2.model.cards;

import java.util.Stack;

public class xCardStackColor extends xCardDeck {

	private Card.Color color;
	
	// constructor
	public xCardStackColor(Card.Color color) {
		this.stack      = new Stack<Card>();
		this.stack_size = 0;
		this.color      = color;
	}

	@Override
	public boolean put(Card card) {
		if (this.color != card.color()) return false; // card does not match package color
		if (this.isEmpty()) { // is empty
			if (1 == card.value()) { // is ace
				this.stack_size++;
				super.stack.push(card);
				return true;
			}
			else {
				return false; // is not ace
			}
		}
		else { // is not empty
			int difference = this.stack_size + 1;
			if (card.value() == difference) { // value is off by one
				this.stack_size++;
				this.stack.push(card);
				return true;
			}
			else { // value is not off by one
				return false;
			}
		}
	}

}