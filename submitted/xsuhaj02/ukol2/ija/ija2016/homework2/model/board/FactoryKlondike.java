package ija.ija2016.homework2.model.board;

import ija.ija2016.homework2.model.cards.*;

public class FactoryKlondike extends AbstractFactorySolitaire{


    public Card createCard(Card.Color color, int value){
        if(value < 1 || value > 13){
            return null;
        }
        if(color == Card.Color.CLUBS || color == Card.Color.DIAMONDS || color == Card.Color.HEARTS || color == Card.Color.SPADES){
            Card card = new CardClass(color,value);
            return card;
        }
        else{
            return null;
        }
    }

    public CardDeck createCardDeck(){
        CardDeck deck = CardDeckClass.createStandardDeck();
        return deck;
    }

    public CardDeck createTargetPack(Card.Color color){
        CardDeck targetPack = new CardStackClassColor(13,color);
        return targetPack;
    }

    public CardStack createWorkingPack(){
        CardStack stack = new CardStackClass(13);
        return stack;
    }



}