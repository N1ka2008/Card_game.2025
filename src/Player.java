import java.util.Scanner;
import java.util.TreeSet;

public class Player extends Pack{

    private boolean turn;

    public Player(TreeSet<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public Player(){
    }

    public TreeSet<Card> getPlayerPack() {
        return playerPack;
    }

    public TreeSet<Card> playerPack = new TreeSet<>();

    public String playerDrawCard(){
        Card card = cardPack.first();
        playerPack.add(card);
        cardPack.remove(card);
        return "player drawed a card";
    }

    public String playerPlayCard(String color, String type){
        Card card = new Card();
        card.setColor(CardColor.valueOf(color));
        card.setType(CardType.valueOf(type));
        if(card.getType().equals(CardType.J)){
            playerChangeColor();
            playerPack.remove(card);
            cardPack.add(card);
        }else if (card.getColor().equals(getActualCardColor()) || card.getType().equals(getActualCardType())) {
                playerPack.remove(card);
                cardPack.add(card);
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
            System.out.println("__________________");
            System.out.println("|" + card.getColor() + " | " + card.getType() + "|");
            System.out.println("");
            System.out.println(getPlayerPack().size());
        }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
