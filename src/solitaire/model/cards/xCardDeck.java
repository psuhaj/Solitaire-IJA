package solitaire.model.cards;

import java.util.Stack;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Class for deck.
 */
public class xCardDeck implements CardDeck {

    public Stack<Card> stack;
    public int stack_size;

    /**
     * Constructs the object deck.
     */
    public xCardDeck() {
        this.stack      = new Stack<Card>();
        this.stack_size = 0;
    }

    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.stack.empty();
    }

    /**
     * Obtain size of deck.
     *
     * @return     Size of deck.
     */
    @Override
    public int size() {
        return this.stack.size();
    }

    /**
     * Puts one card to deck and enlarge by one the deck size.
     *
     * @param      card  The card
     *
     * @return     True value on success.
     */
    @Override
    public boolean put(Card card) {
        this.stack.push(card);
        this.stack_size++;
        return true;
    }

    /**
     * Interface method must be implemented, here it is useless but needed because of error.
     *
     * @param      card  The card
     *
     * @return     True
     */
    @Override
    public boolean tryPut(Card card) {
        return true;
    }


    /**
     * Pop out top card of the deck.
     *
     * @return     Null if empety, card otherwise.
     */
    @Override
    public Card pop() {
        if (this.isEmpty()) return null;
        else {
            this.stack_size--;
            return this.stack.pop();
        }
    }

    /**
     * Get the top card of the deck. The card is left in deck, it is not popped out.
     *
     * @return     Card on deck's top, null if deck is empty.
     */
    @Override
    public Card get() {
        if (this.isEmpty()) return null;
        else return this.stack.peek();
    }

    /**
     * Get the card on the current index in deck.
     *
     * @param      index  The index of card.
     *
     * @return     Null if deck is empty, card on index otherwise.
     */
    @Override
    public Card get(int index) {
        if (this.isEmpty()) return null;
        else if (0 <= index && index < this.size()) return this.stack.get(index);
        else return null;
    }

    /**
     * Creates a standard deck of 52 cards.
     *
     * @return     Standard deck.
     */
    @Override
    public CardDeck createStandardDeck() {
        CardDeck stdDeck = new xCardDeck();
        Card card;
        try {
            for (int i = 1; i<=13; i++) {
                card = new xCard(Card.Color.CLUBS,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_clubs.png")));
                stdDeck.put(card);
            }
            for (int i = 1; i<=13; i++) {
                card = new xCard(Card.Color.DIAMONDS,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_diamonds.png")));
                stdDeck.put(card);
            }
            for (int i = 1; i<=13; i++) {
                card = new xCard(Card.Color.HEARTS,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_hearts.png")));
                stdDeck.put(card);
            }
            for (int i = 1; i<=13; i++) {
                card = new xCard(Card.Color.SPADES,i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_spades.png")));
                stdDeck.put(card);
            }
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        return stdDeck;
    }

}
