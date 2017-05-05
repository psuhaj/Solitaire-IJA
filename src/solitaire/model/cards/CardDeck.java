package solitaire.model.cards;

public interface CardDeck {

	public int size();
	public boolean put(Card card);
	public Card pop();
	public Card get();
	public Card get(int index);
	public boolean tryPut(Card card);
	public boolean isEmpty();
	public CardDeck createStandardDeck();

}