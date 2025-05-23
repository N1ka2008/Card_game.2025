import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Pack implements Serializable {

    private boolean turn;

    public Player(ArrayList<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public Player(){
    }

    public ArrayList<Card> getPlayerPack() {
        return playerPack;
    }

    public ArrayList<Card> playerPack = new ArrayList<>();

    public String playerDrawCard(){
        Card card = cardPack.get(0);
        playerPack.add(card);
        cardPack.remove(card);
        return "player drew a card" + card.toString();
    }

    public String playerPlayCard(String color, String type){
        if(!meldedIsSeven()) {
            CardColor cardColor = CardColor.valueOf(color);
            CardType cardType = CardType.valueOf(type);

            Card cardToPlay = null;
            for (Card card : playerPack) {
                if (card.getColor().equals(cardColor) && card.getType().equals(cardType)) {
                    cardToPlay = card;
                    break;
                }
            }
            if (cardToPlay == null) {
                return "You dont have this card";
            }
            if (cardToPlay.getType().equals(CardType.J)) {
                playerChangeColor();
                playerPack.remove(cardToPlay);
                cardPack.add(cardToPlay);
                return "Player played a J card and changed color";
            }
            else if (cardToPlay.getType().equals(CardType.K) && cardToPlay.getColor().equals(CardColor.DIAMONDS)) {
                playerPack.remove(cardToPlay);
                cardPack.add(cardToPlay);
                playerDiamondsK();
                return "Player played a K of Diamonds";
            }
            else if (cardToPlay.getColor().equals(getActualCardColor()) || cardToPlay.getType().equals(getActualCardType())) {
                playerPack.remove(cardToPlay);
                cardPack.add(cardToPlay);
                return "Player played a card: " + cardToPlay.toString();
            } else {
                return "You cannot play this card";
            }
        }
        return null;
    }

    transient Scanner sc = new Scanner(System.in);

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        sc = new Scanner(System.in);
    }

    public String playerChangeColor() {
        try {
            Card card = new Card();
            System.out.println("Type a color you want to change to: ");
            card.setColor(CardColor.valueOf(sc.next().toUpperCase()));
            setActualCardColor(card.getColor());
            return "player changed color";
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid color! Changing to HEARTS");
            setActualCardColor(CardColor.HEARTS);
            return "player changed color to HEARTS";
        }
    }

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
