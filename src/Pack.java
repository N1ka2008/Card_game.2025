import java.util.TreeSet;

public class Pack {

    private Card melded;
    private Player player;
    private Computer computer;

    public TreeSet<Card> cardPack = new TreeSet<>();

    public Pack() {
        melded = cardPack.first();
    }

    public Pack(Computer computer, Player player) {
        this.computer = computer;
        this.player = player;
    }

    public void add(Card card) {
        if(cardPack.size() <= 32) {
            cardPack.add(card);
        } else{
            System.out.println("Cannot add card to the pack");
        }
    }
}
