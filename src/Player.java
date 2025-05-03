import java.util.TreeSet;

public class Player extends Pack{

    private boolean turn;

    public Player(TreeSet<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public TreeSet<Card> getPlayerPack() {
        return playerPack;
    }

    public TreeSet<Card> playerPack = new TreeSet<>();

    public void playerDrawCard(){
        Card card = cardPack.first();
        playerPack.add(card);
        cardPack.remove(card);
        System.out.println("player drawed a card");
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
