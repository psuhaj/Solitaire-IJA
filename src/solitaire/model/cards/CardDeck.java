package solitaire.model.cards;

/**
 * Interface for card deck.
 */
public interface CardDeck {

    /**
     * Obtain size of deck.
     *
     * @return     Size of deck.
     */
    public int size();

    /**
     * Puts one card to deck and enlarge by one the deck size.
     *
     * @param      card  The card
     *
     * @return     True value on success.
     */
    public boolean put(Card card);

    /**
     * Pop out top card of the deck.
     *
     * @return     Null if empety, card otherwise.
     */
    public Card pop();

    /**
     * Get the top card of the deck. The card is left in deck, it is not popped out.
     *
     * @return     Card on deck's top, null if deck is empty.
     */
    public Card get();

    /**
     * Get the card on the current index in deck.
     *
     * @param      index  The index of card.
     *
     * @return     Null if deck is empty, card on index otherwise.
     */
    public Card get(int index);

    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty();

    /**
     * Creates a standard deck of 52 cards.
     *
     * @return     Standard deck.
     */
    public CardDeck createStandardDeck();

    /**
     * Checks conditions if the card can be putted on stack.
     *
     * @param      card  The card
     *
     * @return     Success of the action.
     */
    public boolean tryPut(Card card);

}
