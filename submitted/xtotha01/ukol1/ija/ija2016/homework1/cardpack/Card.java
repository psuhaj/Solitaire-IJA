package ija.ija2016.homework1.cardpack;

public class Card {

	public static enum Color {
		CLUBS, DIAMONDS, HEARTS, SPADES;

		@Override
		public String toString() {
			switch(this) {
				case CLUBS    : return "C";
				case DIAMONDS : return "D";
				case HEARTS   : return "H";
				default       : return "S";
			}
		}
	 };

	private Color color;
	private int value;

	// sets the color and the value of the card
	public Card(Card.Color c, int value) {
		switch (c) {
			case HEARTS:
				this.color = c;
				break;
			case SPADES:
				this.color = c;
				break;
			case DIAMONDS:
				this.color = c;
				break;
			default:
				this.color = c;
				break;
		}
		this.value = value;
	}

	// returns the color of the card
	public Card.Color color() {
		return this.color;
	}

	// returns the value of the card
	public int value() {
		return this.value;
	}

	public String toString(){
		String str;
		switch (this.value) {
			case 1 :
				str = "A";
				break;
			case 11 :
				str = "J";
				break;
			case 12 :
				str = "Q";
				break;
			case 13 :
				str = "K";
				break;
			default :
				str = String.valueOf(this.value);
				break;
		}
		switch (this.color) {
			case HEARTS: {
				str = str+"(H)";
				return str;
				}
			case SPADES:
				str = str+"(S)";
				return str;
			case DIAMONDS:
				str = str+"(D)";
				return str;
			default:
				str = str+"(C)";
				return str;
		}
	}

	// compare two cards
	public boolean equals(Object card) {
		if (card == null) return false;
		if (this == card) return true;
		if (!(card instanceof Card)) return false;
		Card c = (Card)card;
		return (c.color == this.color && c.value == this.value) ? true : false;
	}

	public int hashCode() {
		int x = 0;
		switch (this.color) {
			case HEARTS :
				x = 0;
				break;
			case DIAMONDS :
				x = 100;
				break;
			case SPADES :
				x = 200;
				break;
			default :
				x = 300;
				break;
		}
		int result = this.value * 10 + x;
        return result;
	}

}
