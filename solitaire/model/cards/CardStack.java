package solitaire.model.cards;

public interface CardStack extends CardDeck {

	public boolean put(CardStack stack);
	public CardStack pop(Card card);

}