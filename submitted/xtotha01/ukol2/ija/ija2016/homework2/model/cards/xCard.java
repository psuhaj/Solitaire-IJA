package ija.ija2016.homework2.model.cards;

public class xCard implements Card {

	// Card's atributes
	private Card.Color color;
	private int value;
	private boolean face;

	// constructor
	public xCard(Card.Color c, int value) {
		this.color = c;
		this.value = value;
		this.face  = false; // (true = faceUP),(false = faceDOWN)
	}

	// GET COLOR
	@Override
	public Card.Color color() {
		return this.color;
	}

	// GET VALUE
	@Override
	public int value() {
		return this.value;
	}

	// GET FACE
	@Override
	public boolean isTurnedFaceUp() {
		return this.face;
	}

	// TURN_UP return true, CANNOT_TURN_UP return false
	@Override
	public boolean turnFaceUp() {
		if (this.face == false) {
			this.face = true;
			return true;
		}
		else {
			return false;
		}
	}

	// return the value difference of cards with the same color
	@Override
	public int compareValue(Card c) {
		xCard xc = (xCard) c;
		return this.value() - xc.value();
	}

	// TWO CARDS ARE SAME COLOR ( (Hearts + Diamonds) || (Clubs + Spades) )
	@Override
	public boolean similarColorTo(Card c) {
		xCard xc = (xCard) c;
		if ( (this.color() == Color.HEARTS || this.color() == Color.DIAMONDS) && (xc.color() == Color.HEARTS || xc.color() == Color.DIAMONDS) ) { // both are RED
			return true;
		}
		else if ( (this.color() == Color.CLUBS || this.color() == Color.SPADES) && (xc.color() == Color.CLUBS || xc.color() == Color.SPADES) ) { // both are BLACK
			return true;
		}
		else { // one is RED one is BLACK
			return false;
		}
	}

	/*
	// print card
	@Override
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
	*/

	// compare two card objects
	public boolean equals(Object o) {
		if (o == null) return false;
		else if (getClass() != o.getClass()) return false;
		else if (this == o) return true;
		xCard xc = (xCard) o;
		if (this.color() != xc.color()) return false;
		if (this.value() != xc.value()) return false;
		if (this.face != xc.face) return false;
		return true;
	}

	/*
	// compare two cards
	public boolean equals(xCard c) {
		if (c == null) return false;
		return (c.color() == this.color() && c.value() == this.value()) ? true : false;
	}
	
	public boolean equals(xCardStack s) {
		if (s == null) return false;
		Card c = s.getCard(s.size()-1);
		return (this.color == c.color() && this.value == c.value());
	}*/
	
	// make hash for card, necessary for equals
	@Override
	public int hashCode() {
		int x = 0;
		switch (this.color) {
			case HEARTS :
				x = 0;
				break;
			case DIAMONDS :
				x = 1000;
				break;
			case SPADES :
				x = 2000;
				break;
			default :
				x = 3000;
				break;
		}
        return (this.value * 10) + x + (face ? 1 : 0);
	}

}
