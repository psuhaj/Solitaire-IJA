package solitaire.model.board;

import solitaire.model.cards.Card;
import solitaire.model.cards.CardDeck;
import solitaire.model.cards.CardStack;
import java.awt.image.BufferedImage;

/**
 * Class for abstract factory solitaire.
 */
abstract public class AbstractFactorySolitaire {

    /**
     * Constructs the object.
     */
    public AbstractFactorySolitaire() {
        super();
    }

    /**
     * Creates a card deck.
     *
     * @return     Created card deck.
     */
    public abstract CardDeck createCardDeck();

    /**
     * Creates a card.
     *
     * @param      color  The card's color.
     * @param      value  The card's value.
     * @param      image  The card's image.
     *
     * @return     Created card, null on wrong parameters.
     */
    public abstract Card createCard(Card.Color color, int value, BufferedImage image);

    /**
     * Creates a target pack/deck.
     *
     * @return     Created target deck.
     */
    public abstract CardDeck createTargetPack();

    /**
     * Creates a working pack/stack.
     *
     * @return     Created working stack.
     */
    public abstract CardStack createWorkingPack();

}
