package solitaire.model.board;

import solitaire.model.cards.Card;
import solitaire.model.cards.CardDeck;
import solitaire.model.cards.CardStack;

abstract public class AbstractFactorySolitaire {

	public AbstractFactorySolitaire() {
		super();
	}

	public abstract CardDeck createCardDeck();
	public abstract Card createCard(Card.Color color, int value);
	public abstract CardDeck createTargetPack();
	public abstract CardStack createWorkingPack();
}