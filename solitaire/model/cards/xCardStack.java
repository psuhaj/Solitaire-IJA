package solitaire.model.cards;

import java.util.Stack;
import java.util.Collections; // for stack reverse

public class xCardStack extends xCardDeck implements CardStack {

	// constructor
	public xCardStack() {
		this.stack      = new Stack<Card>();
		this.stack_size = 0;
	}

	// EMPTY
	@Override
	public boolean isEmpty() {
		return this.stack.empty();
		//return super.isEmpty();
	}

	// SIZE
	@Override
	public int size() {
		//return this.stack.size();
		return super.size();
	}

	@Override
	public boolean put(Card card) {
		if (this.isEmpty()) { // is empty
			if (13 == card.value()) { // is king
				super.put(card);
				return true;
			}
			else {
				return false; // is not king
			}
		}
		else { // is not empty
			int difference = this.get().compareValue(card);
			if (!this.get().similarColorTo(card) && difference == 1) { // different color and the value is off by one
				super.put(card);
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean put(CardStack stack) {
		if (stack==null) return false;
		xCardStack xs = (xCardStack) stack;
		if (this.stack.isEmpty()) { // field is empty => first must be 13(KING)
			if (13 == xs.stack.get(0).value()) { // bottom card of stack is king
				this.stack.addAll(xs.stack);
				return true;
			}
			else { // bottom card of stack is not king
				return false;
			}
		}
		else { // field is not empty => we must check stack(0) is off by one and it has different color
			int difference = this.stack.peek().value() - xs.stack.get(0).value();
			if (difference != 1) return false;
			else if (this.stack.peek().similarColorTo(xs.stack.get(0))) return false;
			else {
				this.stack.addAll(xs.stack);
				this.stack_size += xs.size(); // TODO FIX THIS, it makes problems
				return true;
			}
		}

	}

	@Override
	public CardStack pop(Card card) {
		int idx = this.stack.search(card);
		if (idx == -1) return null; // not found
		else {
			xCardStack xs = new xCardStack();
			for(int i=0; i<idx; i++) {
				this.stack_size--;
				xs.stack_size++;
				xs.stack.push(this.stack.pop());
			}
			Collections.reverse(xs.stack);
			return xs;
		}
	}

	/*
	//@Override
	public boolean equals(xCardStack stack) {
		if (stack == null) return false;
		if (this.size() != stack.size()) return false;
		boolean x;
		for (int i=0; i<this.size(); i++) {
			x=this.stack[i].equals(stack.getCard(i));
			if (x==false) return false;
		}
		return true;
	}*/

}