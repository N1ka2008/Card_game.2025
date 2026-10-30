package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for the main pack and its methods
 * The last card of cardPack is the melded card, played cards are added to the end, cards are drawn from the beginning
 */
public class Pack implements Serializable {

    public ArrayList<Card> cardPack = new ArrayList<>();

    private Card melded;
    private CardColor actualCardColor;
    private CardType actualCardType;
    private Random rd = new Random();

    public Pack() {
        this.cardPack = new ArrayList<>();
    }

    public Pack(ArrayList<Card> cardPack, Card melded) {
        this.cardPack = cardPack;
        this.melded = melded;
        if (melded != null) {
            this.actualCardColor = melded.getColor();
            this.actualCardType = melded.getType();
        }
    }

    /**
     * Method initializes melded card and sets the actual color and type
     * The game never starts with an effect card, so a number card is moved to the end of the pack first
     */
    public void initializeMelded() {
        if (!cardPack.isEmpty()) {
            for (int i = 0; i < cardPack.size(); i++) {
                if (cardPack.get(i).getType().isNumber()) {
                    Card number = cardPack.remove(i);
                    cardPack.add(number);
                    break;
                }
            }
            melded = cardPack.get(cardPack.size() - 1);
            this.actualCardColor = getMelded().getColor();
            this.actualCardType = getMelded().getType();
        }
    }

    /**
     * Method adds card to the main pack
     */
    public void add(Card card) {
        if(!cardPack.contains(card) && cardPack.size() < 44) {
            cardPack.add(card);
        } else {
            System.out.println("Cannot add card to the pack");
        }
    }

    /**
     * Method adds all cards from the file Cards.txt to the main pack
     */
    public boolean addCardsFromFile(String name) {
        try {
            ArrayList<Card> cards = (ArrayList<Card>) Cards1.readCardsFromFile(name);
            if (cardPack.size() + cards.size() > 44) {
                System.out.println("The limit is 44 cards");
                return false;
            }
            cardPack.addAll(cards);
            return true;
        } catch (Exception e) {
            System.err.println("Cannot load cards from file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method gives Player and Computer 4 start cards
     */
    public void dealStartCards(Participant first, Participant second) {
        for (int i = 0; i < 4; i++) {
            first.drawCard();
            second.drawCard();
        }
    }

    /**
     * Method takes the first card from the pack. The last card is the melded one, so it is never drawn
     * It returns null, if there is no card to draw
     */
    public Card drawCard() {
        if (cardPack.size() <= 1) {
            return null;
        }
        return cardPack.remove(0);
    }

    /**
     * Card can be played, if it has the same color or the same type as the actual card
     */
    public boolean canPlay(Card card) {
        return card.getColor() == actualCardColor || card.getType() == actualCardType;
    }

    /**
     * Method plays the card: it goes to the end of the pack (it is the new melded card), the actual color and type
     * are set, the effect is applied and the turn is passed.
     * It returns false (and changes nothing), if the card cannot be played
     */
    public boolean playCard(Participant who, Card card) {
        if (!who.getHand().contains(card) || !canPlay(card)) {
            return false;
        }

        who.getHand().remove(card);
        cardPack.add(card);
        melded = card;
        actualCardColor = card.getColor();
        actualCardType = card.getType();

        boolean opponentSkipped = meldedIsNightmare(who) || meldedIsParalysis(who) || meldedIsDejaVu(who);
        meldedIsAwakening(who);
        meldedIsTwist();

        if (!opponentSkipped) {
            passTurn(who);
        }
        return true;
    }

    /**
     * Method gives the opponent 2 cards, if melded card is nightmare. The opponent also skips the turn
     */
    private boolean meldedIsNightmare(Participant who) {
        if (!actualCardType.equals(CardType.NIGHTMARE)) {
            return false;
        }
        Participant other = who.getOpponent();
        int drawn = other.drawCards(2);
        System.out.println("NIGHTMARE! " + other.getName() + " draws " + drawn + " cards and skips the turn.");
        return true;
    }

    /**
     * Method skips the turn of the opponent, if melded card is paralysis
     */
    private boolean meldedIsParalysis(Participant who) {
        if (!actualCardType.equals(CardType.PARALYSIS)) {
            return false;
        }
        System.out.println("PARALYSIS! " + who.getOpponent().getName() + " is frozen and skips the turn.");
        return true;
    }

    /**
     * Method reverses the direction, if melded card is deja vu. With two players it works as a skipped turn
     */
    private boolean meldedIsDejaVu(Participant who) {
        if (!actualCardType.equals(CardType.DEJAVU)) {
            return false;
        }
        System.out.println("DEJA VU! The direction reverses, " + who.getName() + " plays again.");
        return true;
    }

    /**
     * Method lets Player or Computer choose the new color, if melded card is awakening
     */
    private boolean meldedIsAwakening(Participant who) {
        if (!actualCardType.equals(CardType.AWAKENING)) {
            return false;
        }
        actualCardColor = who.chooseColor();
        System.out.println(who.getName() + " woke up and chose the color: " + actualCardColor);
        return true;
    }

    /**
     * Method changes the actual color to a random different one, if melded card is twist
     */
    private boolean meldedIsTwist() {
        if (!actualCardType.equals(CardType.TWIST)) {
            return false;
        }
        CardColor newColor;
        do {
            newColor = CardColor.values()[rd.nextInt(CardColor.values().length)];
        } while (newColor == actualCardColor);
        actualCardColor = newColor;
        System.out.println("TWIST! The color randomly changed to: " + actualCardColor);
        return true;
    }

    /**
     * Method gives the turn to the opponent
     */
    public void passTurn(Participant who) {
        who.setTurn(false);
        who.getOpponent().setTurn(true);
    }

    public CardType getActualCardType() {
        return actualCardType;
    }

    public void setActualCardType(CardType actualCardType) {
        this.actualCardType = actualCardType;
    }

    public CardColor getActualCardColor() {
        return actualCardColor;
    }

    public void setActualCardColor(CardColor actualCardColor) {
        this.actualCardColor = actualCardColor;
    }

    public Card getMelded() {
        return melded;
    }

    public void setMelded(Card melded) {
        this.melded = melded;
    }

    public ArrayList<Card> getCardPack() {
        return cardPack;
    }

    public void setCardPack(ArrayList<Card> cardPack) {
        this.cardPack = cardPack;
    }
}