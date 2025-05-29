import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Method for player and its methods, extends Pack
 */
public class Player extends Pack implements Serializable {

    private boolean turn;
    Computer computer;

    public Player(ArrayList<Card> cardPack, Card melded, Computer computer) {
        super(cardPack, melded);
        this.computer = computer;
    }

    public Player(){
    }

    public ArrayList<Card> getPlayerPack() {
        return playerPack;
    }

    public ArrayList<Card> playerPack = new ArrayList<>();

    /**
     * Method allows player to draw a card
     */
    public String playerDrawCard(){
        Card card = cardPack.get(0);
        playerPack.add(card);
        cardPack.remove(card);
        return "player drew a card" + card.toString();
    }

    /**
     * This method controls which cards can player play.
     * It also applies methods playerDiamondsK(), playerChangeColor() and playerDrawCard()
     * Optional<Card> optionalCard = playerPack.stream(), .filter() and .findFirst() is not mine. Source: Chat gpt
     * Rest of the method is mine
     */
    public String playerPlayCard(String color, String type){
        CardColor cardColor = CardColor.valueOf(color);
        CardType cardType = CardType.valueOf(type);

        Optional<Card> optionalCard = playerPack.stream()
                .filter(card -> card.getColor().equals(cardColor) && card.getType().equals(cardType))
                .findFirst();

        if (optionalCard.isEmpty()) {
            return "You don't have this card";
        }

        Card cardToPlay = optionalCard.get();

        if (cardType == CardType.J) {
            playerPack.remove(cardToPlay);
            cardPack.add(cardToPlay);
            playerChangeColor();
            setSpecialEfect(false);

            return "Player played a J card and changed color to: " + getActualCardColor();
        }

        if (cardColor == getActualCardColor() || cardType == getActualCardType()) {
            playerPack.remove(cardToPlay);
            cardPack.add(cardToPlay);
            setSpecialEfect(false);

            setActualCardColor(cardColor);
            setActualCardType(cardType);

            if (computer != null) {
                setActualCardColor(cardColor);
                setActualCardType(cardType);
            }

            if (cardType == CardType.K && cardColor == CardColor.DIAMONDS) {
                playerDiamondsK();
            }

            return "Player played a card: " + cardToPlay;
        }

        return "You cannot play this card";
    }

    transient Scanner sc = new Scanner(System.in);

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        sc = new Scanner(System.in);
    }

    /**
     * Method gives player the opinion to change the actual color
     * The actual color then sets to the value of players input
     */
    public String playerChangeColor() {
        try {
            System.out.println("Type a color you want to change to: ");
            String input = sc.next().toUpperCase();
            CardColor newColor = CardColor.valueOf(input);

            setActualCardColor(newColor);

            System.out.println("Color changed to: " + newColor);
            return "player changed color to " + newColor;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid color! Changing to HEARTS");
            CardColor defaultColor = CardColor.HEARTS;

            setActualCardColor(defaultColor);

            return "player changed color to HEARTS";
        }
    }

    /**
     * Method give player the opinion to draw one extra card
     */
    public void playerDiamondsK(){
        Card card = cardPack.get(0);
        System.out.println("Do you want to draw one extra card?");
        String choice = sc.next();
            switch (choice) {
                case "yes":
                    playerPack.add(card);
                    cardPack.remove(card);
                    System.out.println("one card drew");
                    break;
                    case "no":
                        System.out.println("No cards drew");
                        break;
                        default:
                            System.out.println("Invalid choice");
                            break;
            }

    }

    /**
     * Method prints all cards player has in his pack
     */
    public void getplayersCards(){
        System.out.println("\nPlayers cards: ");
        for(Card card : playerPack){
            System.out.println("[" + card.getColor() + " | " + card.getType() + "]");
        }
        System.out.println("player has: " + getPlayerPack().size() + " cards");
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
