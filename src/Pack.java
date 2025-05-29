import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class for the main pack and its methods
 */
public class Pack implements Serializable {

    public ArrayList<Card> cardPack = new ArrayList<>();

    private Card melded;
    private Player player;
    private Computer computer;
    private CardColor actualCardColor;
    private CardType actualCardType;
    private boolean skipTurn = false;
    private boolean specialEfect = false;

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
     */
    public void initializeMelded() {
        if (!cardPack.isEmpty()) {
            melded = cardPack.get(cardPack.size() - 1);
            this.actualCardColor = getMelded().getColor();
            this.actualCardType = getMelded().getType();
        }
    }

     public Pack(Computer computer, Player player) {
        this.computer = computer;
        this.player = player;
    }

    /**
     * Method adds card to the main pack
     */
    public void add(Card card) {
        if(!cardPack.contains(card)) {
            if (cardPack.size() < 32) {
                cardPack.add(card);
            } else {
                System.out.println("Cannot add card to the pack");
            }
        }
        System.out.println("something went wrong");
    }

    /**
     * Method adds all cards from the file Cards.txt to the main pack
     */
    public boolean addCardsFromFile(String name) {
        try {
            ArrayList<Card> cards = (ArrayList<Card>) Cards1.readCardsFromFile(name);
            if (cardPack.size() + cards.size() > 32) {
                System.out.println("The limit is 32 cards");
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

    /**
     * Method gives Player or Computer 2 cards, if melded card is seven
     */
    public boolean meldedIsSeven(){
        if(melded == null || !melded.getType().equals(CardType.SEVEN) || isSpecialEfect()) {
            return false;
        }

        if(player == null || computer == null) {
            System.out.println("Player or computer not initialized");
            return false;
        }

        System.out.println("SEVEN played! Drawing 2 cards for current player.");
        specialEfect = true;

        if (player.isTurn()) {
            Card[] firstTwoCardsPlayer = new Card[2];
            Iterator<Card> iterator2 = cardPack.iterator();

            for (int i = 0; i < 2 && iterator2.hasNext(); i++) {
                firstTwoCardsPlayer[i] = iterator2.next();
            }
            for (int i = 0; i < 2; i++) {
                if(firstTwoCardsPlayer[i] != null) {
                    player.playerPack.add(firstTwoCardsPlayer[i]);
                    cardPack.remove(firstTwoCardsPlayer[i]);
                }
            }
            player.setTurn(false);
            computer.setTurn(true);
            System.out.println("Player drew 2 cards");
        } else {
            Card[] firstTwoCardsComputer = new Card[2];
            Iterator<Card> iterator = cardPack.iterator();

            for (int i = 0; i < 2 && iterator.hasNext(); i++) {
                firstTwoCardsComputer[i] = iterator.next();
            }
            for (int i = 0; i < 2; i++) {
                if(firstTwoCardsComputer[i] != null) {
                    computer.computerPack.add(firstTwoCardsComputer[i]);
                    cardPack.remove(firstTwoCardsComputer[i]);
                }
            }
            computer.setTurn(false);
            player.setTurn(true);
            System.out.println("Computer drew 2 cards");
        }
        return true;
    }

    /**
     * Method wont let Player or Computer play, if melded card is ace
     */
    public boolean meldedIsA(){
        if(skipTurn) {
            return false;
        }

        boolean meldedIsA;
        if(getActualCardType().equals(CardType.A) && !isSpecialEfect()){
            specialEfect = true;
            meldedIsA = true;
            skipTurn = true;
            if(player.isTurn()) {
                player.setTurn(false);
                computer.setTurn(true);
            }else{
                computer.setTurn(false);
                player.setTurn(true);
            }
            System.out.println("An Ace was played! Turn is skipped.");
        }else{
            meldedIsA = false;
        }
        return meldedIsA;
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

    public ArrayList<Card> getCardPack() {
        return cardPack;
    }

    public void setCardPack(ArrayList<Card> cardPack) {
        this.cardPack = cardPack;
    }

    public boolean isSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    public boolean isSpecialEfect() {
        return specialEfect;
    }

    public void setSpecialEfect(boolean specialEfect) {
        this.specialEfect = specialEfect;
    }
}
