package ija.ija2016.homework2.model.cards;

public interface CardDeck{
    public Card get();
    public Card get(int index);
    public boolean isEmpty();
    public Card pop();
    public boolean put(Card card);
    public int size();
}
