import java.util.TreeSet;

public class Player extends Pack{

    public Player(TreeSet<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public TreeSet<Card> getPlayerPack() {
        return playerPack;
    }

    public TreeSet<Card> playerPack = new TreeSet<>();

    /*public void startCards(){

    }

     */

}
