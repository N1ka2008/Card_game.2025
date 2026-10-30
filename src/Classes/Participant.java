package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Participant implements Serializable {
    private String name;
    private boolean turn;
    public Pack pack;
    public Participant opponent;
    public ArrayList<Card> hand = new ArrayList<>();

    protected Participant(String name) {
        this.name = name;
    }

    public Card drawCard() {
        Card card = pack.drawCard();
        if (card != null) {
            hand.add(card);
        }
        return card;
    }

    public int drawCards(int count) {
        int drawn = 0;
        for (int i = 0; i < count; i++) {
            if (drawCard() != null) {
                drawn++;
            }
        }
        return drawn;
    }

    public Card findCard(CardColor color, CardType type){
        for (Card card : hand) {
            if (card.getColor() == color && card.getType() == type) {
                return card;
            }
        }
        return null;
    }

    public abstract CardColor chooseColor();

    public String getName() {
        return name;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Participant getOpponent() {
        return opponent;
    }

    public void setOpponent(Participant opponent) {
        this.opponent = opponent;
    }

}
