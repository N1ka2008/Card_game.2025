import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Pack{

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
        return "player drawed a card";
    }

    public String playerPlayCard(String color, String type){
        if(!meldedIsSeven()) {
            Card card = new Card();
            card.setColor(CardColor.valueOf(color));
            card.setType(CardType.valueOf(type));
            Card cardToPlay = null;
            for (Card card1 : playerPack) {
                if (card1.getColor().equals(color) && card.getType().equals(type)) {
                    cardToPlay = card1;
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
            } else if (cardToPlay.getColor().equals(getActualCardColor()) || cardToPlay.getType().equals(getActualCardType())) {
                playerPack.remove(cardToPlay);
                cardPack.add(cardToPlay);
            } else if (cardToPlay.getColor().equals(CardColor.DIAMONDS) && cardToPlay.getType().equals(CardType.K)) {
                playerDiamondsK();
            }
            return "player played a card";
        }
        return null;
    }

    Scanner sc = new Scanner(System.in);
    public String playerChangeColor(){
        Card card = new Card();
        System.out.println("Type a color you want to change to: ");
        card.setColor(CardColor.valueOf(sc.next().toUpperCase()));
        setActualCardColor(card.getColor());
        return "player changed color";
    }

    public void playerDiamondsK(){
        Card card = cardPack.get(0);
        System.out.println("Do you want to draw one extra card?");
        String choice = sc.next();
            switch (choice) {
                case "yes":
                    playerPack.add(card);
                    cardPack.remove(card);
                    break;
                    case "no":
                        System.out.println("No cards added");
                        break;
                        default:
                            System.out.println("Invalid choice");
                            break;
            }

    }

    public void getplayerPack(){
        for(Card card : playerPack){
            System.out.println("[" + card.getColor() + " | " + card.getType() + "]");
        }
        System.out.println(getPlayerPack().size());
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
