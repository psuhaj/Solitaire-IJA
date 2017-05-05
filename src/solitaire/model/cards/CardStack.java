package solitaire.model.cards;

public interface CardStack extends CardDeck {

	public boolean put(CardStack stack);
    public boolean putEmpty(Card card);
    public boolean tryPut(Card card);
	public CardStack pop(Card card);

}