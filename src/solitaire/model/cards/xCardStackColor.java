package solitaire.model.cards;

import java.util.Stack;

public class xCardStackColor extends xCardDeck {

	private Card.Color color;
	
	// constructor
	public xCardStackColor() {
		this.stack      = new Stack<Card>();
		this.stack_size = 0;
	}

	@Override
	public boolean put(Card card) {
		if (this.isEmpty()) { // is empty
			if (1 == card.value()) { // is ace
				this.stack_size++;
				this.stack.push(card);
				this.color = card.color();
				return true;
			}
			else {
				return false; // is not ace
			}
		}
		else { // is not empty
			if (this.color != card.color()) return false; // card does not match package color
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


	public boolean tryPut(Card card) {
		if (this.isEmpty()) { // is empty
			if (1 == card.value()) { // is ace
				return true;
			}
			else {
				return false; // is not ace
			}
		}
		else { // is not empty
			if (this.color != card.color()) return false; // card does not match package color
			int difference = this.stack_size + 1;
			if (card.value() == difference) { // value is off by one
				return true;
			}
			else { // value is not off by one
				return false;
			}
		}
	}


}