import java.util.Iterator;
import java.util.TreeSet;

public class Pack {

    public TreeSet<Card> cardPack = new TreeSet<>();

    private Card melded;
    private Player player;
    private Computer computer;
    private CardColor actualCardColor;
    private CardType actualCardType;



    public Pack() {
        this.cardPack = new TreeSet<>();
    }

    public Pack(TreeSet<Card> cardPack, Card melded) {
        this.cardPack = cardPack;
        this.melded = melded;
        if (melded != null) {
            this.actualCardColor = melded.getColor();
            this.actualCardType = melded.getType();
        }
    }

    public void initializeMelded() {
        if (!cardPack.isEmpty()) {
            melded = cardPack.last();
            this.actualCardColor = getMelded().getColor();
            this.actualCardType = getMelded().getType();
        }
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

    public void meldedIsSeven(){
        if(getActualCardType().equals(CardType.SEVEN)) {
            if (player.isTurn()) {
                Card[] firstTwoCardsPlayer = new Card[2];
                Iterator<Card> iterator2 = cardPack.iterator();

                for (int i = 0; i < 2 && iterator2.hasNext(); i++) {
                    firstTwoCardsPlayer[i] = iterator2.next();
                }
                for (int i = 0; i < 2; i++) {
                    player.playerPack.add(firstTwoCardsPlayer[i]);
                    cardPack.remove(firstTwoCardsPlayer[i]);
                }
            } else {
                Card[] firstTwoCardsComputer = new Card[2];
                Iterator<Card> iterator = cardPack.iterator();

                for (int i = 0; i < 2 && iterator.hasNext(); i++) {
                    firstTwoCardsComputer[i] = iterator.next();
                }
                for (int i = 0; i < 2; i++) {
                    computer.computerPack.add(firstTwoCardsComputer[i]);
                    cardPack.remove(firstTwoCardsComputer[i]);
                }
            }
        }
    }

    public void meldedIsA(){
        if(getActualCardType().equals(CardType.A)){
            if(player.isTurn()) {
                player.setTurn(false);
            }else{
                computer.setTurn(false);
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
