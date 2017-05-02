package solitaire.model.board;

import solitaire.model.cards.*;
import java.util.Collections;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class FactoryKlondike extends AbstractFactorySolitaire {

	@Override
	public CardDeck createCardDeck() {
		xCardDeck array = new xCardDeck();
		try{
			for(int i=1; i<=13; i++) {
				array.put(createCard(Card.Color.HEARTS, i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_hearts.png"))));
			}
			for(int i=1; i<=13; i++) {
				array.put(createCard(Card.Color.SPADES, i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_spades.png"))));
			}
			for(int i=1; i<=13; i++) {
				array.put(createCard(Card.Color.DIAMONDS, i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_diamonds.png"))));
			}
			for(int i=1; i<=13; i++) {
				array.put(createCard(Card.Color.CLUBS, i,ImageIO.read(this.getClass().getResourceAsStream("/cards/"+i+"_of_clubs.png"))));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
                //shuffle the deck
        Collections.shuffle(array.stack);
		return array;
	}

	@Override
	public Card createCard(Card.Color color, int value, BufferedImage image) {
		if (!(color == Card.Color.HEARTS || color == Card.Color.SPADES || color == Card.Color.DIAMONDS || color == Card.Color.CLUBS)) return null;
		if (value<1 || 13<value) return null;
		else return new xCard(color, value,image);
	}

	@Override
	public CardDeck createTargetPack() {
		return new xCardStackColor();
	}

	@Override
	public CardStack createWorkingPack() {
		return new xCardStack();
	}


}