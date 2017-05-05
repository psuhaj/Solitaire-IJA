package solitaire.model.cards;

public interface CardStack extends CardDeck {

	public boolean put(CardStack stack);
	public CardStack pop(Card card);
    public boolean tryPut(Card card);
    public boolean putEmpty(Card card);
	public void putEmpty(CardStack stack);

}