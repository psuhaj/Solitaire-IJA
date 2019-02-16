package ija.ija2016.homework2.model.cards;

public interface Card {

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

	public Card.Color color();
	public int value();
	public boolean isTurnedFaceUp();
	public boolean turnFaceUp();
	public int compareValue(Card c);
	public boolean similarColorTo(Card c);
	public String toString();

}
