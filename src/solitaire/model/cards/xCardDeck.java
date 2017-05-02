package solitaire.model.cards;

import java.util.Stack;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class xCardDeck implements CardDeck {

	public Stack<Card> stack;
	public int stack_size;

	// constructor
	public xCardDeck() {
		this.stack      = new Stack<Card>();
		this.stack_size = 0;
	}

	// EMPTY
	@Override
	public boolean isEmpty() {
		return this.stack.empty();
	}

	// SIZE
	@Override
	public int size() {
		return this.stack.size();
	}

	// PUT CARD
	@Override
	public boolean put(Card card) {
		this.stack.push(card);
		this.stack_size++;
		return true;
	}

	// POP CARD
	@Override
	public Card pop() {
		if (this.isEmpty()) return null;
		else {
			this.stack_size--;
			return this.stack.pop();
		}
	}

	// GET CARD (dont pop)
	@Override
	public Card get() {
		if (this.isEmpty()) return null;
		else return this.stack.peek();
	}

	// GET INDEXED CARD
	@Override
	public Card get(int index) {
		if (this.isEmpty()) return null;
		else if (0 <= index && index < this.size()) return this.stack.get(index);
		else return null;
	}

	// MAKE STANDARD-CARD-PACKAGE
	@Override
	public CardDeck createStandardDeck() {
		CardDeck stdDeck = new xCardDeck();
		Card card;
		try{
			for (int i = 1; i<=13; i++) {
				card = new xCard(Card.Color.CLUBS,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_clubs.png")));
				stdDeck.put(card);
			}
			for (int i = 1; i<=13; i++) {
				card = new xCard(Card.Color.DIAMONDS,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_diamonds.png")));
				stdDeck.put(card);
			}
			for (int i = 1; i<=13; i++) {
				card = new xCard(Card.Color.HEARTS,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_hearts.png")));
				stdDeck.put(card);
			}
			for (int i = 1; i<=13; i++) {
				card = new xCard(Card.Color.SPADES,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_spades.png")));
				stdDeck.put(card);
			}
			//return stdDeck;
		}
		catch(IOException e){
				e.printStackTrace();
		}
		return stdDeck;
	}

}