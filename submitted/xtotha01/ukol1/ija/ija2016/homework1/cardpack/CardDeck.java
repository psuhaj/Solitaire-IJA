package ija.ija2016.homework1.cardpack;

public class CardDeck {

	private Card carddeck[];
	private int  carddeck_top;

	public CardDeck(int size) {
		this.carddeck      = new Card[size];
		this.carddeck_top  = 0;
	}

	public static CardDeck createStandardDeck() {
		CardDeck std_deck = new CardDeck(52);
		Card card;
		for (int i = 1; i<=13; i++) {
			card = new Card(Card.Color.CLUBS,i);
			std_deck.put(card);
		}
		for (int i = 1; i<=13; i++) {
			card = new Card(Card.Color.DIAMONDS,i);
			std_deck.put(card);
		}
		for (int i = 1; i<=13; i++) {
			card = new Card(Card.Color.HEARTS,i);
			std_deck.put(card);
		}
		for (int i = 1; i<=13; i++) {
			card = new Card(Card.Color.SPADES,i);
			std_deck.put(card);
		}
		return std_deck;
	}

	public int size() {
		return this.carddeck_top;
	}

	public void put(Card card) {
		this.carddeck[this.carddeck_top]=card;
		this.carddeck_top++;
	}

	public Card pop() {
		if (this.carddeck_top==0) return null;
		else {
			Card card = this.carddeck[this.carddeck_top-1];
			this.carddeck_top--;
			return card;
		}
	}

}