package ija.ija2016.homework2.model.cards;

public interface CardStack extends CardDeck{
    public CardStack pop(Card card);
    public boolean put(CardStack stack);
}