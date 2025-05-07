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
            if (card.getColor().equals(getActualCardColor()) || card.getType().equals(getActualCardType())) {
                playerPack.remove(card);
                cardPack.add(card);
            }
        return "player played a card";
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
