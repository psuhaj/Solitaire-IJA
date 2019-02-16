package ija.ija2016.homework1.cardpack;

public class CardStack {

	Card stack[];
	int stack_top;

	// initialize a stack
	public CardStack(int size) {
		this.stack      = new Card[size];
		this.stack_top  = -1;
	}

	// insert one card to stack
	public void put(Card card) {
		this.stack_top++;
		this.stack[this.stack_top] = card;
	}

	// insert a sequence of cards to stack
	public void put(CardStack stack) {
		for (int i=0; i<=stack.stack_top; i++) {
			this.put(stack.stack[i]);
		}
	}

	// check if the stack is empty
	public boolean isEmpty() {
		return (this.stack_top == 0) ? true : false;
	}

	// returns the actual size of stack
	public int size() {
		return this.stack_top+1;
	}

	// remove a sequence of cards from the given card to the stack top
	public CardStack takeFrom(Card card) {

		// find the position of the card in stack
		int idx = -2;
		for (int i=0; i<=this.stack_top; i++) {
			if (this.stack[i].equals(card)) {
				idx = i;
				break;
			}
		}

		// card not found in stack
		if (idx == -2) return null;

		int size = (this.stack_top - idx) + 1;
		CardStack new_stack = new CardStack(size);

		// save the sequence of the cards to new stack
		for (int i=idx; i<=this.stack_top; i++) {
			new_stack.put(this.stack[i]);
		}

		// remove the seqence of cards
		this.stack_top=idx-1;
		return new_stack;
	}

	public boolean equals(Object stack) {
		if (stack == null) return false;
		if (this == stack) return true;
		if (!(stack instanceof CardStack)) return false;
		CardStack c = (CardStack)stack;
		if (this.stack_top != c.stack_top) return false;
		boolean x;
		for (int i=0; i<this.stack_top; i++) {
			x=this.stack[i].equals(c.stack[i]);
			if (x==false) return false;
		}
		return true;
	}

}