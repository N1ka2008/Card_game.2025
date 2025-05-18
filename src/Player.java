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
            return "Card not found in your hand";
        }

        if(cardToPlay.getType().equals(CardType.J)){
            playerChangeColor();
            playerPack.remove(cardToPlay);
            cardPack.add(cardToPlay);
        }else if (cardToPlay.getColor().equals(getActualCardColor()) || cardToPlay.getType().equals(getActualCardType())) {
                playerPack.remove(cardToPlay);
                cardPack.add(cardToPlay);
            } else {
            meldedIsSeven();
        }
        return "player played a card";
    }

    Scanner sc = new Scanner(System.in);
    public String playerChangeColor(){
        Card card = new Card();
        System.out.println("Type a color you want to change to: ");
        card.setColor(CardColor.valueOf(sc.next()));
        setActualCardColor(card.getColor());
        return "player changed color";
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
