import java.util.Iterator;
import java.util.TreeSet;

public class Pack {

    private Card melded;
    private Player player;
    private Computer computer;
    private CardColor actualCardColor = melded.getColor();
    private CardType actualCardType = melded.getType();

    public TreeSet<Card> cardPack = new TreeSet<>();

    public Pack(TreeSet<Card> cardPack, Card melded) {
        this.cardPack = cardPack;
        this.melded = melded;
    }

    public Pack() {
        melded = cardPack.last();
    }

     public Pack(Computer computer, Player player) {
        this.computer = computer;
        this.player = player;
    }

    public void add(Card card) {
        if(cardPack.size() < 32) {
            cardPack.add(card);
        } else{
            System.out.println("Cannot add card to the pack");
        }
    }

    public boolean addCardsFromFile(String name) {
        try {
            TreeSet<Card> cards = Cards1.readCardsFromFile(name);
            if (cardPack.size() + cards.size() > 32) {
                System.out.println("The limit is 32 cards");
                return false;
            }
            cardPack.addAll(cards);
            System.out.println("Pack contains" + cards.size());
            return true;
        } catch (Exception e) {
            System.err.println("Error loading cards from file: " + e.getMessage());
            return false;
        }
    }

    public void startCards(){
        Card[] firstEightCards = new Card[8];
        Iterator<Card> iterator = cardPack.iterator();

        for (int i = 0; i < 8 && iterator.hasNext(); i++) {
            firstEightCards[i] = iterator.next();
        }

        for (int i = 0; i < 8; i += 2) {
            player.playerPack.add(firstEightCards[i]);
            cardPack.remove(firstEightCards[i]);
        }

        for (int i = 1; i < 8; i += 2) {
            computer.computerPack.add(firstEightCards[i]);
            cardPack.remove(firstEightCards[i]);
        }
    }

    public void mendedIsSeven(){
        if (computer.isTurn()) {
            Card[] firstTwoCards = new Card[2];
            Iterator<Card> iterator = cardPack.iterator();

            for (int i = 0; i < 2 && iterator.hasNext(); i++) {
                firstTwoCards[i] = iterator.next();
            }
            if (getActualCardType().equals(CardType.SEVEN)) {
                for (int i = 0; i < 2; i++) {
                    computer.computerPack.add(firstTwoCards[i]);
                    cardPack.remove(firstTwoCards[i]);
                }
            }
        } else if (player.isTurn()) {
            Card[] firstTwoCards = new Card[2];
            Iterator<Card> iterator = cardPack.iterator();

            for (int i = 0; i < 2 && iterator.hasNext(); i++) {
                firstTwoCards[i] = iterator.next();
            }
            if (getActualCardType().equals(CardType.SEVEN)) {
                for (int i = 0; i < 2; i++) {
                    player.playerPack.add(firstTwoCards[i]);
                    cardPack.remove(firstTwoCards[i]);
                }
            }
        }
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

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card getMelded() {
        return melded;
    }

    public void setMelded(Card melded) {
        this.melded = melded;
    }
}
