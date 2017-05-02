package solitaire.model.board;

import solitaire.model.cards.Card;
import solitaire.model.cards.CardDeck;
import solitaire.model.cards.CardStack;
import java.awt.image.BufferedImage;

abstract public class AbstractFactorySolitaire {

	public AbstractFactorySolitaire() {
		super();
	}

	public abstract CardDeck createCardDeck();
	public abstract Card createCard(Card.Color color, int value, BufferedImage image);
	public abstract CardDeck createTargetPack();
	public abstract CardStack createWorkingPack();
}